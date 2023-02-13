package com.autoconfiguration.config;

import com.autoconfiguration.config.autoconfig.DispatcherServletConfig;
import com.autoconfiguration.config.autoconfig.TomcatWebServerConfig;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

@Retention(RetentionPolicy.RUNTIME)
@Target( ElementType.TYPE)
@Import(MyAutoConfigImportSelector.class)
public @interface EnableMyAutoConfiguration {
}
