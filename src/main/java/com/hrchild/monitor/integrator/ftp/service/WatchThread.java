package com.hrchild.monitor.integrator.ftp.service;

import com.hrchild.monitor.integrator.ftp.model.EmailUtil;
import com.hrchild.monitor.integrator.ftp.model.Instance;
import com.hrchild.monitor.integrator.ftp.model.Mail;
import com.jcraft.jsch.*;
import java.io.*;
import java.util.List;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// @Service
// public class WatchRunnable implements Runnable{
public class WatchThread extends Thread {

  JSch jsch = null;
  Session session = null;
  ChannelSftp channelSftp = null;
  ChannelExec channelExec = null;

  Logger logger = LoggerFactory.getLogger(WatchThread.class);

  private List<String> notiKeywords;
  private Mail mail;
  private Instance instance;

  public WatchThread(List<String> notiKeywords, Mail mail, Instance instance) {
    this.notiKeywords = notiKeywords;
    this.mail = mail;
    this.instance = instance;
  }

  @Override
  public void run() {
    connect();
    startMonitoring();
    disConnect();
  }

  public void startMonitoring() {
    try {

      long startPoint = 0;
      long endPoint = 0;

      while (true) {

        Thread.sleep(5000);
        // GET LINE CNT
        channelExec = (ChannelExec) session.openChannel("exec");
        channelExec.setCommand("cat " + instance.getLogPath() + " | wc -l");
        InputStream is = channelExec.getInputStream();
        channelExec.connect();

        byte[] buffer = new byte[8192];
        int decodedLengthLN;
        StringBuilder resLN = new StringBuilder();
        while ((decodedLengthLN = is.read(buffer, 0, buffer.length)) > 0) {
          resLN.append(new String(buffer, 0, decodedLengthLN));
        }
        //                logger.debug("lineCnt: [{}]", resLN);
        int lineCnt = Integer.parseInt(resLN.toString().replaceAll("[^0-9]", ""));
        //                logger.debug("lineCnt:[{}]", lineCnt);

        startPoint = startPoint == 0 ? lineCnt - 1 : startPoint;
        endPoint = lineCnt;

        // rolling append 된경우, startPoint를 재정의.
        if (startPoint > endPoint) {
          startPoint = endPoint - 1;
        }
        channelExec.disconnect();

        // 추가된 line 확인. (from ~ to 까지 확인)
        channelExec = (ChannelExec) session.openChannel("exec");
        //                channelExec.setCommand("sed -n \'" + startPoint + "," + endPoint + "p"
        // +"\' " + "/home/ec2-user/gruvoice/collector.log");
        logger.debug(
            "INSTANCE : [{}], CHECKING LINE BETWEEN :[{}] ~ [{}]",
            instance.getName(),
            startPoint,
            endPoint);
        channelExec.setCommand(
            "sed -n \'" + startPoint + "," + endPoint + "p" + "\' " + instance.getLogPath());
        is = channelExec.getInputStream();
        channelExec.connect();

        buffer = new byte[8192];
        int decodedLength;
        StringBuilder response = new StringBuilder();
        while ((decodedLength = is.read(buffer, 0, buffer.length)) > 0) {
          response.append(new String(buffer, 0, decodedLength));
        }

        for (String notiKeyword : notiKeywords) {
          if (response.toString().contains(notiKeyword)) {
            logger.debug(
                "[{}] AN {} HAS OCCURRED PLEASE CHECK EMAIL", instance.getName(), notiKeyword);
            //                        mail = new Mail(mail.getFrom(), mail.getTo(), mail.getTo(),
            // mail.getHost(), mail.getPort(), mail.getAccount(), mail.getPassword());
            mail.setSubject(instance.getName() + "AN " + notiKeyword + " HAS OCCURRED");
            mail.setText(response.toString());

            EmailUtil.send(mail);
          }
        }

        /* set TITLE */
        // 메일전송 (호스트이름 / 에러내용)
        startPoint = endPoint + 1; // 중복방지
        channelExec.disconnect();
      }

    } catch (JSchException e) {
      logger.error("FAIL TO START MONITORING: ", e);
    } catch (IOException e) {
      logger.error("FAIL TO GET VALUE(InputStream)", e);
    } catch (InterruptedException e) {
      logger.error("ERROR", e);
    }
  }

  public void connect() {

    try {
      jsch = new JSch();

      /* Auth */
      //            if (instance.getAuthKeyPath() != null &&
      // !(instance.getAuthKeyPath().isEmpty())){
      //                jsch.addIdentity(instance.getAuthKeyPath());
      //            }
      session = jsch.getSession(instance.getRemoteAccnt(), instance.getHost(), instance.getPort());
      if (instance.getPassword() != null && !(instance.getPassword().isEmpty())) {
        logger.debug("password:[{}]", instance.getPassword());
        session.setPassword(instance.getPassword());
      } else {
        jsch.addIdentity(instance.getAuthKeyPath());
      }
      //            if (instance.getPassword() != null && !instance.getPassword().isEmpty()) {
      //                session.setPassword(instance.getPassword());
      //            } else {
      //                jsch.addIdentity(instance.getAuthKeyPath());
      //            }

      Properties prop = new Properties();
      prop.put("StrictHostKeyChecking", "no"); // ??
      session.setConfig(prop);
      session.connect();

    } catch (JSchException e) {
      logger.error(
          "FAIL TO CONNECT TO INSTANCE([{}]), HOST:[{}] ", instance.getName(), instance.getHost());
    }
  }

  private void disConnect() {
    logger.debug("THE SYSTEM HAS BEEN SHUTDOWN");
    channelExec.disconnect();
  }
  //

}
