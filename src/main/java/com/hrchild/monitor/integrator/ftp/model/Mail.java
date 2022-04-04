package com.hrchild.monitor.integrator.ftp.model;

import java.io.File;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public class Mail {

  private String mailTo;
  private String mailSubject;
  private File attachFile;

  private String from;
  private List<String> to;
  private String replyTo;
  private String cc;
  private String bcc;
  private String subject;
  private String text;

  private MultipartFile attach;
  //    private Multipart
  private String host;
  private int port;
  private String account;
  private String password;

  public Mail() {}

  public Mail(
      String from, List<String> to, String host, int port, String account, String password) {
    this.from = from;
    this.to = to;
    this.host = host;
    this.port = port;
    this.account = account;
    this.password = password;
  }
  //    public Mail(String mailFrom, String mailTo, String mailCc, String mailSubject, String
  // mailContent, String templateName) {
  //        this.mailFrom = mailFrom;
  //        this.mailTo = mailTo;
  //        this.mailCc = mailCc;
  //        this.mailSubject = mailSubject;
  //        this.mailContent = mailContent;
  //        this.templateName = templateName;
  //    }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public List<String> getTo() {
    return to;
  }

  public void setTo(List<String> to) {
    this.to = to;
  }

  public String getCc() {
    return cc;
  }

  public void setCc(String cc) {
    this.cc = cc;
  }

  public String getBcc() {
    return bcc;
  }

  public void setBcc(String bcc) {
    this.bcc = bcc;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void setAttachFile(File attachFile) {
    this.attachFile = attachFile;
  }

  public MultipartFile getAttach() {
    return attach;
  }

  public void setAttach(MultipartFile attach) {
    this.attach = attach;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getReplyTo() {
    return replyTo;
  }

  public void setReplyTo(String replyTo) {
    this.replyTo = replyTo;
  }

  public String getMailTo() {
    return mailTo;
  }

  public void setMailTo(String mailTo) {
    this.mailTo = mailTo;
  }

  public String getMailSubject() {
    return mailSubject;
  }

  public void setMailSubject(String mailSubject) {
    this.mailSubject = mailSubject;
  }

  public File getAttachFile() {
    return attachFile;
  }

  @Override
  public String toString() {
    return "Mail{"
        + "from='"
        + from
        + '\''
        + ", to="
        + to
        + ", cc='"
        + cc
        + '\''
        + ", bcc='"
        + bcc
        + '\''
        + ", subject='"
        + subject
        + '\''
        + ", text='"
        + text
        + '\''
        + ", attach="
        + attach
        + ", host='"
        + host
        + '\''
        + ", port="
        + port
        + ", account='"
        + account
        + '\''
        + ", password='"
        + password
        + '\''
        + '}';
  }
}
