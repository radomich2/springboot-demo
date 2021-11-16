package com.opuscapita.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ConfigurationProperties("app")
public class AppSettings {

    static final Logger log = LoggerFactory.getLogger(AppSettings.class);

    private String appName;
    private String helloPrefix;

    @PostConstruct
    void onLoad() {
        log.info("--- App name = {}", appName);
        log.info("--- Hello prefix = {}", helloPrefix);
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getHelloPrefix() {
        return helloPrefix;
    }

    public void setHelloPrefix(String helloPrefix) {
        this.helloPrefix = helloPrefix;
    }
}
