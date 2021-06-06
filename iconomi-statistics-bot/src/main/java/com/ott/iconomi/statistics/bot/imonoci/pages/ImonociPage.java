package com.ott.iconomi.statistics.bot.imonoci.pages;

import com.ott.iconomi.statistics.bot.imonoci.ImonociPageHelper;

public interface ImonociPage {

	default void  waitAMoment() {
		ImonociPageHelper.waitAMoment();
	}
	
	default void waitALittle() {
		ImonociPageHelper.waitALittle();
	}
	
//	void goTo(String ... params);
	
}
