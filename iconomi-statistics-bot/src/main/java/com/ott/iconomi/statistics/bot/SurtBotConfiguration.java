package com.ott.iconomi.statistics.bot;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SurtBotProperties.class)
public class SurtBotConfiguration {

	public static final int IMPLICIT_WAIT = 300;

	public static void main(String[] args) {
		System.out.println("test");

//		System.setProperty("webdriver.chrome.driver", "D:\\Dev\\selenium\\webdriver\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();

		System.setProperty("webdriver.gecko.driver", "D:\\Dev\\selenium\\webdriver\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();

		driver.navigate().to("http://www.google.com");

		//SwitchTo new window
		Set<String> openedWindows = driver.getWindowHandles();
		System.out.println("openWindows = " + openedWindows);
		driver.switchTo().newWindow(WindowType.TAB);

		driver.navigate().to("http://www.google.com");

		//Close
		driver.close();

//		String mainWindow = driver.getWindowHandle();
//		driver.switchTo().window(mainWindow);
		driver.switchTo().window(driver.getWindowHandles().iterator().next());


		driver.switchTo().newWindow(WindowType.TAB);
		driver.navigate().to("http://www.google.fr");

	}


	
	
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
	    driver.manage().timeouts().pageLoadTimeout(Duration.of(IMPLICIT_WAIT, ChronoUnit.SECONDS));
	    driver.manage().timeouts().implicitlyWait(Duration.of(IMPLICIT_WAIT, ChronoUnit.SECONDS));
	    return driver;
	}
	
	@Bean 
	public WebDriverWait webDriverWait(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30), Duration.of(1000, ChronoUnit.MILLIS));
		return wait;
	}

	@Bean
	public BotDataImporter botDataImporter() {
		return new BotDataImporter();
	}
    
}
