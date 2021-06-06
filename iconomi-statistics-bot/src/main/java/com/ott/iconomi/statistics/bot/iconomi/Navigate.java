package com.ott.iconomi.statistics.bot.iconomi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.ott.iconomi.statistics.bot.iconomi.IconomiPageHelper.*;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import static com.ott.iconomi.statistics.bot.iconomi.IconomiUriHelper.*;

@Component
public class Navigate {
	

	private WebDriver driver;
	private WebDriverWait wait;

	public Navigate(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}
	
	public static void main(String... args) {
		UriComponents uriComponents = UriComponentsBuilder.fromUriString(getAssetUrl())
				.build();
		String url = uriComponents.expand("aaaa").toUriString();
		System.out.println(url);
//		ImonociUriHelper.setSiteTest();
	}

	public void toStrategies() {
		toStrategies(1);
	}
	
	public void toStrategies(int page) {
		UriComponents uriComponents = UriComponentsBuilder.fromUriString(getStrategiesUrl())
				.queryParam("page", "{page}")
				.encode().build();
		String url = uriComponents.expand(page).toUriString();
		to(url);
	}

	public void to(String url) {
		driver.navigate().to(url);
		waitALittle();
	}

	public void to(String url, By waitUntilPresenceOf) {
		driver.navigate().to(url);
		wait.until(ExpectedConditions.presenceOfElementLocated(waitUntilPresenceOf));
		waitALittle();
	}

	public void to(String url, ExpectedCondition<?> waitUntil) {
		driver.navigate().to(url);
		wait.until(waitUntil);
		waitALittle();
	}
	
	public void toStrategy(String shortName, ExpectedCondition<?> expectedCondition) {
		toAsset(shortName, expectedCondition);
	}
	
	public void toAsset(String assetName, ExpectedCondition<?> expectedCondition) {
		UriComponents uriComponents = UriComponentsBuilder.fromUriString(getAssetUrl())
				.build();
		String url = uriComponents.expand(assetName).toUriString();
		to(url, expectedCondition);
	}

}
