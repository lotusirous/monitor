package com.hrchild.monitor.integrator.ftp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class MonitorApp {
    private static ApplicationContext applicationContext;
    Logger logger = LoggerFactory.getLogger(MonitorApp.class);
    public static void main(String[] args) {
        SpringApplication.run(MonitorApp.class, args);
    }
}
