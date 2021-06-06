package com.ott.iconomi.statistics.bot.iconomi.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ott.iconomi.statistics.bot.iconomi.Navigate;
public class AssetPage extends AbstractImonociPage {

	
	public AssetPage(WebDriver driver, WebDriverWait wait, Navigate navigate) {
		super(driver, wait, navigate);
	}

	public void goTo(String assetName, ExpectedCondition<?> expectedCondition) {
        navigate.toAsset(assetName, expectedCondition);
	}
	
	public static String getAssetShortNameFromHref(String assetUrl) {
		return assetUrl.substring(assetUrl.lastIndexOf('/') + 1);
	}
	
}
