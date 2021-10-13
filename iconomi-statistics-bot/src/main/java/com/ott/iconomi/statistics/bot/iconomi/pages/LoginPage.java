package com.ott.iconomi.statistics.bot.iconomi.pages;

import static com.ott.iconomi.statistics.bot.utils.DriverHelper.findElementsSafeBy;
import static com.ott.iconomi.statistics.bot.utils.ExpectedConditionsUtils.presenceOfAtLeastOneElementLocated;

import org.openqa.selenium.*;
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
		driver.switchTo().newWindow(WindowType.TAB);
		navigate.to(IconomiUriHelper.getLoginUrl());
		waitAMoment(); // wait until the page is fully loaded (for the cas of already logged in we need to wait the redirect
//            driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER);

		//WebElement firstResult = wait.until(presenceOfElementLocated(By.className("icon-cancel")));
		WebElement firstResult = wait.until(presenceOfAtLeastOneElementLocated(
				new By[]{
						By.tagName("app-user-avatar-placeholder"),
						By.id("e_email")
				} //
		));
		//check if already logged in
		if (findElementsSafeBy(driver, By.id("e_email")).isEmpty()) {
			//no need for login : already logged in
			if (findElementsSafeBy(driver, By.tagName("app-user-avatar-placeholder")).isEmpty()) {
				throw new RuntimeException("If already logged in we should find the element <app-user-avatar-placeholder>");
			}
		} else {
			findElementsSafeBy(driver,By.className("icon-cancel")).forEach(WebElement::click);
			driver.findElement(By.id("e_email")).sendKeys(login);
			waitALittle();
			driver.findElement(By.id("e_password")).sendKeys(pws + Keys.ENTER);
			waitAMoment();
		}
	}

}
