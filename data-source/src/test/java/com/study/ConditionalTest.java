package com.study;

import static org.assertj.core.api.Assertions.assertThat;

import com.study.ConditionalTest.Config1.TrueCondition;
import com.study.ConditionalTest.Config2.FalseCondition;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ConditionalTest {

    @Test
    void conditional(){
        //true
        new ApplicationContextRunner().withUserConfiguration(Config1.class)
                .run(context -> {
                    assertThat(context).hasSingleBean(MyBean.class);
                    assertThat(context).hasSingleBean(Config1.class);
                });

        //false
        new ApplicationContextRunner().withUserConfiguration(Config2.class)
                .run(context -> {
                    assertThat(context).doesNotHaveBean(MyBean.class);
                    assertThat(context).doesNotHaveBean(Config1.class);
                });
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(TrueCondition.class)
    @interface TrueConditional{}

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(FalseCondition.class)
    @interface FalseConditional{}

    @Configuration
    @BooleanConditional(true)
    static class Config1 {

        @Bean
        MyBean myBean(){
            return new MyBean();
        }

        static class TrueCondition implements Condition {
            @Override
            public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
                return true;
            }
        }
    }

    @Configuration
    @BooleanConditional(false)
    static class Config2 {

        @Bean
        MyBean myBean(){
            return new MyBean();
        }

        static class FalseCondition implements Condition {
            @Override
            public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
                return false;
            }
        }
    }

    static class MyBean { }


    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(BooleanCondition.class)
    @interface BooleanConditional{
        boolean value();
    }

    public static class BooleanCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            Map<String, Object> attributes = metadata.getAnnotationAttributes(BooleanConditional.class.getName());
            assert attributes != null;
            return (Boolean) attributes.get("value");
        }
    }
}
