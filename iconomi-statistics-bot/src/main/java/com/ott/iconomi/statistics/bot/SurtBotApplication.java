package com.ott.iconomi.statistics.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = {"com.ott.iconomi.statistics", "com.ott.iconomi.statistics.domain.config"})
@EnableJpaRepositories(basePackages = "com.ott.iconomi.statistics.data.repository")
@EntityScan("com.ott.iconomi.statistics.data.entity")
public class SurtBotApplication {


	public static void main(String[] args) {
		SpringApplication.run(SurtBotApplication.class, args);
	}
	
	
}