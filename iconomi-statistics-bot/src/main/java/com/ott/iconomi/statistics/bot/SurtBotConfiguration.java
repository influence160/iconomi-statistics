package com.ott.iconomi.statistics.bot;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SurtBotProperties.class)
public class SurtBotConfiguration {
	
	
	@Bean 
	public WebDriver webDriver(SurtBotProperties properties) {
		WebDriver driver = null;
		switch (properties.getWebdriver().getImpl()) {
			case "gecko" : 		
				System.setProperty("webdriver.gecko.driver", properties.getWebdriver().getGecoLocation());
				driver = new FirefoxDriver();
				break;
			case "chrome" : 
				System.setProperty("webdriver.chrome.driver", properties.getWebdriver().getChromeLocation());
				driver = new ChromeDriver();
				break;
			default : throw new IllegalArgumentException("valeur invalide " + properties.getWebdriver().getImpl());
		}
	    driver.manage().timeouts().pageLoadTimeout(Duration.of(30, ChronoUnit.SECONDS));
	    driver.manage().timeouts().implicitlyWait(Duration.of(30, ChronoUnit.SECONDS));
	    return driver;
	}
	
	@Bean 
	public WebDriverWait webDriverWait(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30), Duration.of(1000, ChronoUnit.MILLIS));
		return wait;
	}
    
}
