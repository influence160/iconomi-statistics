package com.ott.iconomi.statistics.bot.iconomi;

import java.util.Base64;

public class IconomiUriHelper {
	
	private static String SITE = "https://www.iconomi.com";


	public static String getLoginUrl() {
		return SITE + "/login";
		
	}
	
	public static String getStrategiesUrl() {
		return SITE + "/strategies";
	}

	public static String getAssetUrl() {
		return SITE + "/asset/{asset}";
	}
	
	
	//voir https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-uri-building
	//UriComponentsBuilder
}
