package com.ott.iconomi.statistics.bot.utils;

import java.util.function.Supplier;

import com.ott.iconomi.statistics.bot.imonoci.ImonociPageHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Functions {
	
	private static final int MAX_TRY_WITH_REFRESHABLE = 3;
	
	public static <T> T doWithRefreshableElement(Supplier<T> s) {
		int tries = 0;
		while (tries < MAX_TRY_WITH_REFRESHABLE) {
			try {
				return s.get();
			} catch (org.openqa.selenium.StaleElementReferenceException e) {
				ImonociPageHelper.waitAMoment();
				tries++;
				log.warn("org.openqa.selenium.StaleElementReferenceException e on Refreshable element. try " + tries + "/" + MAX_TRY_WITH_REFRESHABLE);
				if (tries == MAX_TRY_WITH_REFRESHABLE) {
					throw e;
				}
			}
		}
		throw new RuntimeException("On devrais pas en arriver laa :(");
	}

}
