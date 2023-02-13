package com.autoconfiguration.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{
                "com.autoconfiguration.config.autoconfig.DispatcherServletConfig",
                "com.autoconfiguration.config.autoconfig.TomcatWebServerConfig"
        };
    }
}
