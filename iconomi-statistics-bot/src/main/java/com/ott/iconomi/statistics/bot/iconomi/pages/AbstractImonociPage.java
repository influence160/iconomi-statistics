package com.ott.iconomi.statistics.bot.iconomi.pages;

import com.ott.iconomi.statistics.bot.utils.Functions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ott.iconomi.statistics.bot.iconomi.Navigate;

import java.util.List;

public abstract class AbstractImonociPage implements ImonociPage {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Navigate navigate;

	public AbstractImonociPage(WebDriver driver, WebDriverWait wait, Navigate navigate) {
		this.driver = driver;
		this.wait = wait;
		this.navigate = navigate;
	}

	public String getTextByCss(String cssSelector) {
		String text = driver.findElement(By.cssSelector(cssSelector)).getText();
		return text;
	}
	
	public String getTextByXpath(String xpathExpression) {
		String text = driver.findElement(By.xpath(xpathExpression)).getText();
		return text;
	}

	protected void goToUrl(String url, ExpectedCondition<?> expectedCondition) {
        navigate.to(url, expectedCondition);
	}

	public WebElement findElement(By by) {
		return Functions.doWithRefreshableElement(() -> driver.findElement(by));
	}

	public List<WebElement> findElements(By by) {
		return Functions.doWithRefreshableElement(() -> driver.findElements(by));
	}
}
