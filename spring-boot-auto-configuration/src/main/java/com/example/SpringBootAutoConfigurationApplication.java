package com.example;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport;
import org.springframework.context.annotation.Bean;

//vm-option : -Ddebug
@SpringBootApplication
public class SpringBootAutoConfigurationApplication {

    @Bean
    public ApplicationRunner run(ConditionEvaluationReport report) {
        return args -> {
            long count = report.getConditionAndOutcomesBySource().entrySet().stream()
                    .filter(co -> co.getValue().isFullMatch())
                    .filter(co -> !co.getKey().contains("Jmx"))
                    .peek(co -> {
                        System.out.println(co.getKey());
                        co.getValue().forEach(c -> System.out.println("\t" + c.getOutcome()));
                        System.out.println();
                    }).count();
            System.out.println("total : " + count);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAutoConfigurationApplication.class, args);
    }

}
