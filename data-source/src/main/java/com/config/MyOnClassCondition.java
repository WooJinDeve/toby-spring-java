package com.config;

import java.util.Map;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

public class MyOnClassCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> attributes = metadata.getAnnotationAttributes(
                ConditionalMyOnClass.class.getName());
        Assert.notNull(attributes, "ConditionMyOnClass.annotation is null");;
        String value = (String)attributes.get("value");
        return ClassUtils.isPresent(value, context.getClassLoader());
    }
}
