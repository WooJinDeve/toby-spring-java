package com.config.autoconfig;

import com.config.ConditionalMyOnClass;
import com.config.EnableMyConfigurationProperties;
import com.config.MyAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
@EnableMyConfigurationProperties(ServerProperties.class)
public class TomcatWebServerConfig {
    @Bean("tomcatWebServerFactory")
    @ConditionalOnMissingBean
    public ServletWebServerFactory servletWebServerFactory(ServerProperties serverProperties) {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.setContextPath(serverProperties.getContextPath());
        factory.setPort(serverProperties.getPort());
        return factory;
    }
}
