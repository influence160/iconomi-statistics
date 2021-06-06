package com.ott.iconomi.statistics.bot.imonoci.pages;

import com.ott.iconomi.statistics.bot.imonoci.pages.queries.StrategiesQueries;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import com.ott.iconomi.statistics.bot.imonoci.Navigate;
import com.ott.iconomi.statistics.domain.model.Snapshot;
import com.ott.iconomi.statistics.domain.model.Strategy;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class StrategiesPage extends AbstractImonociPage {

	private static final int STRATEGIES_PER_PAGE = 20;
	private StrategyPage strategyPage;
	
	private int currentPage;

	protected StrategiesPage(WebDriver driver, WebDriverWait wait, Navigate navigate, StrategyPage strategyPage) {
		super(driver, wait, navigate);
		this.strategyPage = strategyPage;
	}
	
	public void goTo(int pageNumer) {
        navigate.toStrategies(currentPage);
	}
	
	public void takeASnapshot(Snapshot.SnapshotBuilder snapshot) {
        navigate.toStrategies();
        
        WebElement pagesCountElement = driver.findElement(By.cssSelector(StrategiesQueries.PAGES_COUNT));
        int pagesCount = Integer.parseInt(pagesCountElement.getText());
        log.info("strategies pagesCount = " + pagesCount);
        //TODO Assert that there is 20 per page
//        currentStructures = new ArrayList<>(pagesCount * STRATEGIES_PER_PAGE);
        currentPage = 1;
        
        int expectedStrategiesCount = 0;
        Set<Strategy> strategies = new HashSet<>(STRATEGIES_PER_PAGE * pagesCount);
        while (currentPage <= pagesCount) {
        	int currentPageStrategiescount = driver.findElements(By.cssSelector(StrategiesQueries.PAGE_STRATEGIES_COUNT)).size();
        	extractStrategiesData(currentPage, currentPageStrategiescount, strategies);
        	expectedStrategiesCount += currentPageStrategiescount;
        	currentPage++;
        	if (currentPage <= pagesCount) {
				goTo(currentPage);
			}
        }
        
        if (expectedStrategiesCount != strategies.size()) {
			log.warn("strategies.size() is not like expected :O. expected = " + expectedStrategiesCount
					+ " strategies.size() = " + strategies.size() + " strategies = " + strategies);
        }
        log.info("Successefuly extracted " + strategies.size() + " strategies.");
        snapshot.strategies(strategies);
        snapshot.currentStructures(strategies.stream().map(Strategy::getCurrentStructure).collect(Collectors.toList()));
	}

	private void extractStrategiesData(int page, int count, Set<Strategy> strategies) {
		List<String> urls = extractStrategiesUrls(page, count);
		for (String url : urls) {
			strategies.add(extractStrategyData(url));
		}
	}

	private List<String> extractStrategiesUrls(int page, int count) {
		List<String> list = new ArrayList<>();
		for (int positionInThePage = 1; positionInThePage <= count; positionInThePage++) {
			WebElement block = driver.findElement(By.cssSelector(StrategiesQueries.getStrategyBlock(positionInThePage)));
			String strategyUrl = block.getAttribute("href");
			log.trace("strategyUrl =" + strategyUrl);
			list.add(strategyUrl);
//			if (positionInThePage == 3) {
//				break;
//			}
		}
		return list;
	}

	private Strategy extractStrategyData(String url) {
		Strategy strategy = strategyPage.extractStrategy(url);
		log.debug("strategy extracted : " + strategy + " with currentStructure = " + strategy.getCurrentStructure());
		return strategy;
	}

}
