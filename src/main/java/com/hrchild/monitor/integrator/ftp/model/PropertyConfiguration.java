package com.hrchild.monitor.integrator.ftp.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

public class PropertyConfiguration {

  Logger logger = LoggerFactory.getLogger(PropertyConfiguration.class);
  public String confPath;

  public PropertyConfiguration(String confPath) {
    this.confPath = confPath;
  }

  /* 외부설정 프로퍼티 */
  //    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() throws
  // IOException {
  public MonitorConfig propertySourcesPlaceholderConfigurer() throws IOException {
    logger.info("confPath:{}", confPath);

    InputStream is = new FileInputStream(new File(confPath));
    Yaml yml = new Yaml();
    MonitorConfig monitorConfig = yml.loadAs(is, MonitorConfig.class);
    logger.debug("monitorConfig:[{}]", monitorConfig);

    return monitorConfig;
  }
}
