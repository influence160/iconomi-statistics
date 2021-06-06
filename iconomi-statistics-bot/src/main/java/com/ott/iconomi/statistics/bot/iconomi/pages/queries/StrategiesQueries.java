package com.ott.iconomi.statistics.bot.iconomi.pages.queries;

import java.text.MessageFormat;

public abstract class StrategiesQueries {
	
	public static String PAGES_COUNT = ".page:nth-last-child(2)";
	public static String PAGE_STRATEGIES_COUNT = "app-product-table>div>a";
	
	private static String NTH_STRATEGY_BLOCK_PATTERN = "app-product-table>div>a:nth-of-type({0})";
	private static MessageFormat NTH_STRATEGY_BLOCK_FORMAT = new MessageFormat(NTH_STRATEGY_BLOCK_PATTERN);
	public static String getStrategyBlock(Integer nth) {
		return NTH_STRATEGY_BLOCK_FORMAT.format(new Object[] {nth});
	}

	private static String NTH_STRATEGY_NAME_PATTERN = NTH_STRATEGY_BLOCK_PATTERN + " .paragraph-black-bold";
	private static MessageFormat NTH_STRATEGY_NAME_FORMAT = new MessageFormat(NTH_STRATEGY_NAME_PATTERN);
	public static String getStrategyName(int nth) {
		return NTH_STRATEGY_NAME_FORMAT.format(new Object[] {String.valueOf(nth)});
	}


	 
}
