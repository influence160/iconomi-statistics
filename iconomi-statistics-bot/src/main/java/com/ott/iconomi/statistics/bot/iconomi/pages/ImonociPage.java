package com.ott.iconomi.statistics.bot.iconomi.pages;

import com.ott.iconomi.statistics.bot.iconomi.IconomiPageHelper;

public interface ImonociPage {

	default void  waitAMoment() {
		IconomiPageHelper.waitAMoment();
	}
	
	default void waitALittle() {
		IconomiPageHelper.waitALittle();
	}
	
//	void goTo(String ... params);
	
}
