package com.ott.iconomi.statistics.bot.iconomi.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import com.ott.iconomi.statistics.bot.iconomi.IconomiUriHelper;
import com.ott.iconomi.statistics.bot.iconomi.Navigate;

@Component
public class LoginPage extends AbstractImonociPage {

	public LoginPage(WebDriver driver, WebDriverWait wait, Navigate navigate) {
		super(driver, wait, navigate);
	}
	

	public void login(String login, String pws) {
		navigate.to(IconomiUriHelper.getLoginUrl());
//            driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER);
        WebElement firstResult = wait.until(presenceOfElementLocated(By.className("icon-cancel")));
        driver.findElements(By.className("icon-cancel")).forEach(WebElement::click);
        driver.findElement(By.id("e_email")).sendKeys(login);
        waitALittle();
        driver.findElement(By.id("e_password")).sendKeys(pws + Keys.ENTER);
    	waitAMoment();
	}

}
