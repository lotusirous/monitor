package com.hrchild.monitor.integrator.ftp;

import com.hrchild.monitor.integrator.ftp.model.Instance;
import com.hrchild.monitor.integrator.ftp.model.MonitorConfig;
import com.hrchild.monitor.integrator.ftp.model.PropertyConfiguration;
import com.hrchild.monitor.integrator.ftp.service.WatchThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {
  Logger logger = LoggerFactory.getLogger(Runner.class);

  private static final String confPath = System.getProperty("CONF_HOME");

  @Override
  public void run(String... args) throws Exception {
    //        logger.info("HI, I'm Runner App");
    logger.debug("confPath:[{}]", confPath);
    PropertyConfiguration propertyConfiguration = new PropertyConfiguration(confPath);
    logger.debug("propertyConfiguration:[{}", propertyConfiguration);
    MonitorConfig monitorConfig = propertyConfiguration.propertySourcesPlaceholderConfigurer();
    logger.debug("propertySourcesPlaceholderConfigurer:[{}]", monitorConfig);
    //        logger.debug("hi,:)");

    for (Instance instance : monitorConfig.getServers()) {
      WatchThread watchThread =
          new WatchThread(monitorConfig.getNotiKeywords(), monitorConfig.getMail(), instance);
      logger.debug("START WATCHING : [{}]", instance.getName());
      watchThread.start();
    }
  }
}
