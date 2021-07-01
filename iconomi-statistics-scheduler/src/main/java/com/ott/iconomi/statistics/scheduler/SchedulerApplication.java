package com.ott.iconomi.statistics.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.ott.iconomi.statistics.scheduler", "com.ott.iconomi.statistics.domain"})
@EnableJpaRepositories(basePackages = "com.ott.iconomi.statistics.data.repository")
@EntityScan("com.ott.iconomi.statistics.data.entity")
public class SchedulerApplication {

    public static void main(String[] args) {
        //argumens : --login=${login} --pwd=${pwd}
        SpringApplication.run(SchedulerApplication.class, args);
    }
}
