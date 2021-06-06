package com.ott.iconomi.statistics.bot.imonoci.pages;

import static com.ott.iconomi.statistics.bot.imonoci.ImonociPageHelper.parseDateTime;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.util.ArrayList;
import java.util.List;

import com.ott.iconomi.statistics.bot.imonoci.pages.queries.AssetQueries;
import com.ott.iconomi.statistics.bot.utils.Functions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import com.ott.iconomi.statistics.bot.imonoci.ImonociPageHelper;
import com.ott.iconomi.statistics.bot.imonoci.Navigate;
import com.ott.iconomi.statistics.domain.model.Asset;
import com.ott.iconomi.statistics.domain.model.CurrentStructure;
import com.ott.iconomi.statistics.domain.model.Strategy;
import com.ott.iconomi.statistics.domain.model.StructureElement;

@Component
public class StrategyPage extends AssetPage {

	public StrategyPage(WebDriver driver, WebDriverWait wait, Navigate navigate) {
		super(driver, wait, navigate);
	}
	

	public Strategy extractStrategy(String strategyUrl) {
//		goTo(strategy.getShortName(), and(presenceOfElementLocated(By.cssSelector(AssetQueries.getReturn(ReturnType.DAY))),
//				not(textToBe(By.cssSelector(AssetQueries.getReturn(ReturnType.DAY)), "N/A"))));
//		goTo(strategy.getShortName(), presenceOfElementLocated(By.xpath(NUMBER_STRUCTURE_CHANGE_XPATH)));
		goToUrl(strategyUrl, presenceOfElementLocated(By.xpath(AssetQueries.NUMBER_STRUCTURE_CHANGE_XPATH)));
		Strategy.StrategyBuilder strategyBuilder = Strategy.builder();
		String strategyShortName = AssetPage.getAssetShortNameFromHref(strategyUrl);
		strategyBuilder.shortName(strategyShortName);
		String strategyName = findElement(By.xpath(AssetQueries.STRATEGY_NAME)).getText();
		strategyBuilder.name(strategyName);
		String manager = findElement(By.xpath(AssetQueries.STRATEGY_MANAGER)).getText();
		strategyBuilder.manager(manager);
		boolean isFavorite = findElement(By.xpath(AssetQueries.STRUCTURE_BLOCK_ELEMENTS_XPATH)).getAttribute("class").contains("color-gold-500");
		strategyBuilder.favorite(isFavorite);
		
		CurrentStructure.CurrentStructureBuilder currentStructureBuilder = CurrentStructure.builder();
		currentStructureBuilder.strategyShortName(strategyShortName);
		currentStructureBuilder.dayReturn(getReturnValue(AssetQueries.ReturnType.DAY));
		currentStructureBuilder.weekReturn(getReturnValue(AssetQueries.ReturnType.WEEK));
		currentStructureBuilder.monthReturn(getReturnValue(AssetQueries.ReturnType.MONTH));
		currentStructureBuilder.yearReturn(getReturnValue(AssetQueries.ReturnType.YEAR));
		currentStructureBuilder.allTimeReturn(getReturnValue(AssetQueries.ReturnType.ALL_TIME));
		String lastStructureChange = getTextByXpath(AssetQueries.LAST_STRUCTURE_CHANGE_XPATH);
		currentStructureBuilder.lastChange(parseDateTime(lastStructureChange));
		currentStructureBuilder.numberOfChangesInLast30Days(Integer.parseInt(getTextByXpath(AssetQueries.NUMBER_STRUCTURE_CHANGE_XPATH)));
		List<WebElement> structureWebElements = findElements(By.xpath(AssetQueries.STRUCTURE_BLOCK_ELEMENTS_XPATH));
		List<StructureElement> currentStructureElements = new ArrayList<>(structureWebElements.size());
		for (int i = 0; i < structureWebElements.size(); i++) {
			StructureElement.StructureElementBuilder structureElementBuilder = StructureElement.builder();
			Asset asset = getAsset(i);
			structureElementBuilder.asset(asset);
			final int index = i;
			String percent = findElement(By.xpath(AssetQueries.getStructureElementPercent(index + 1))).getText();
			structureElementBuilder.percent(ImonociPageHelper.percentTextToFloat(percent));
			currentStructureElements.add(structureElementBuilder.build());
		}
		currentStructureBuilder.elements(currentStructureElements);
		strategyBuilder.currentStructure(currentStructureBuilder.build());
		return strategyBuilder.build();
	}

	private Asset getAsset(int i) {

		String assetCcy = getAssetShortNameFromHref(findElement(By.xpath(AssetQueries.getStructureElementAssetCcy(i + 1))).getAttribute("href"));
		String assetName = findElement(By.xpath(AssetQueries.getStructureElementAssetName(i + 1))).getText();
		Asset.AssetBuilder assetBuilder = Asset.builder()
				.ccy(assetCcy)
				.name(assetName);
		Asset asset = assetBuilder.build();
		return asset;
	}

	private Float getReturnValue(AssetQueries.ReturnType type) {
		String text = getTextByCss(AssetQueries.getReturn(type));
		return ImonociPageHelper.returnPercentTextToFloat(text);
	}
}
