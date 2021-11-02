package com.opuscapita.demo;

import com.opuscapita.demo.config.AppSettings;
import com.opuscapita.demo.hello.HelloController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private HelloController helloController;

    @Autowired
    private AppSettings appSettings;

    @Test
    public void contextLoaded() {
        assertThat(helloController).isNotNull();
        assertThat(appSettings).isNotNull();
    }

    @Test
    public void appSettingsInitialized() {
        assertThat(appSettings.getHelloPrefix()).isEqualTo("Hi, ");
        assertThat(appSettings.getAppName()).isEqualTo("Test App");
    }

}
