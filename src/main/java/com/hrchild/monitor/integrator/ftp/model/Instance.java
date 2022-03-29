package com.hrchild.monitor.integrator.ftp.model;

public class Instance {

    private String host;
    private String name;
    private int port;
    private String remoteAccnt;

    private String logPath;
    private String logFileName;

    private String keyName;
    private String keyPath;
    private String authKeyPath;
    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getRemoteAccnt() {
        return remoteAccnt;
    }

    public void setRemoteAccnt(String remoteAccnt) {
        this.remoteAccnt = remoteAccnt;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public String getLogFileName() {
        return logFileName;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getKeyPath() {
        return keyPath;
    }

    public void setKeyPath(String keyPath) {
        this.keyPath = keyPath;
    }

    public String getAuthKeyPath() {
        return authKeyPath;
    }

    public void setAuthKeyPath(String authKeyPath) {
        this.authKeyPath = authKeyPath;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

