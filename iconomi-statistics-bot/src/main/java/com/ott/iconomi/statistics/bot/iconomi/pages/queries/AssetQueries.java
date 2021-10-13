package com.ott.iconomi.statistics.bot.iconomi.pages.queries;

import java.text.MessageFormat;

public abstract class AssetQueries {

	public static final String STRATEGY_NAME = "//app-strategy-detail//app-asset-logo-placeholder[@class='w-100'][1]/following-sibling::p[1]";
	public static final String STRATEGY_MANAGER = "//app-strategy-detail//app-asset-logo-placeholder[@class='w-100'][1]/following-sibling::a[1]";
	public static final String STRATEGY_FAVORITES = "//app-strategy-detail//app-asset-logo-placeholder[@class='w-100'][1]/following-sibling::div[1]/i";
	
	public static enum ReturnType {
		DAY(1),
		WEEK(2),
		MONTH(3),
		YEAR(4),
		ALL_TIME(5);
		
		private int index;
		private ReturnType(int index) {
			this.index = index;
		}
		
	}
	private static final MessageFormat NTH_RETURN_FORMAT = new MessageFormat(".returns-grid>div:nth-of-type({0})>div:nth-of-type(2)");
	public static final String getReturn(ReturnType type) {
		return NTH_RETURN_FORMAT.format(new Object[] {type.index});
	}
	

	public static final String LAST_STRUCTURE_CHANGE_XPATH = "//div[.='Last structure change']/following-sibling::div[1]";

	public static final String NUMBER_STRUCTURE_CHANGE_XPATH = "//div[.='Number of structure change in last 30 days']/following-sibling::div[1]";

	public static final String STRUCTURE_BLOCK_ELEMENTS_XPATH = "//h5[.='Structure']/following-sibling::a";

	private static final MessageFormat STRUCTURE_ELEMENT_ASSET_CCY_FORMAT = new MessageFormat(STRUCTURE_BLOCK_ELEMENTS_XPATH.replace("'", "''") + "[{0}]");
	public static final String getStructureElementAssetCcy(int elementIndex) {
		return STRUCTURE_ELEMENT_ASSET_CCY_FORMAT.format(new Object[] {elementIndex});
	}

	private static final MessageFormat STRUCTURE_ELEMENT_ASSET_NAME_FORMAT = new MessageFormat(STRUCTURE_BLOCK_ELEMENTS_XPATH.replace("'", "''") + "[{0}]/app-asset-logo-placeholder/following-sibling::span[1]");
	public static final String getStructureElementAssetName(int elementIndex) {
		return STRUCTURE_ELEMENT_ASSET_NAME_FORMAT.format(new Object[] {elementIndex});
	}

	private static final MessageFormat STRUCTURE_ELEMENT_PERCENT_FORMAT = new MessageFormat(STRUCTURE_BLOCK_ELEMENTS_XPATH.replace("'", "''") + "[{0}]/app-asset-logo-placeholder/following-sibling::span[2]");
	public static final String getStructureElementPercent(int elementIndex) {
		return STRUCTURE_ELEMENT_PERCENT_FORMAT.format(new Object[] {elementIndex});
	}

}
