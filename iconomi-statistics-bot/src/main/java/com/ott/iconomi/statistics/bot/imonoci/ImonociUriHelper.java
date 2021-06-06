package com.ott.iconomi.statistics.bot.imonoci;

import java.util.Base64;

import org.openqa.selenium.remote.http.UrlTemplate;

public class ImonociUriHelper {
	
	private static String SITE = decode("aHR0cHM6Ly93d3cuaWNvbm9taS5jb20=");


	public static void main(String[] args) {
		System.out.println(Base64.getUrlEncoder().encodeToString("https://www.iconomi.com".getBytes()));
		System.out.println(new String(Base64.getUrlDecoder().decode("aHR0cHM6Ly93d3cuaWNvbm9taS5jb20=".getBytes())));
	}
	
	private static String decode(String src) {
		return new String(Base64.getUrlDecoder().decode(src));
	}
	
	public static void setSiteTest() {
		SITE = "http://www.test.com";
	}
	

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
