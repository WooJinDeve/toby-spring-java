package com.config.autoconfig;

import com.config.MyAutoConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

//@MyAutoConfiguration
@Deprecated
public class ServerPropertiesConfig {

    @Bean
    public ServerProperties serverProperties(Environment environment){
        return Binder.get(environment).bind("", ServerProperties.class).get();
    }
}
