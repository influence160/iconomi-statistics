package com.ott.iconomi.statistics.bot.iconomi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

import org.springframework.util.Assert;

public class IconomiPageHelper {
	
	public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM d, y, h:mm:ss a").withLocale(Locale.US);

	//public static final NumberFormat PERCENT_FORMATTER = new DecimalFormat("#,##0.00");
	//public static final NumberFormat RETURN_PERCENT_FORMATTER = new DecimalFormat("+#,###.##;-#,##0.00");
	
	private static Random random = new Random();
	
	public static void waitAMoment() {
		try {
			Thread.sleep(random.nextInt(2000) + 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void waitALittle() {
		try {
			Thread.sleep(random.nextInt(1000) + 500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
//	public static void waitMore(WaitMore more) {
//		
//	}

	public static float percentTextToFloat(String text) {
		Assert.notNull(text, "Percent text null");
		Assert.state(text.endsWith("%"), "Bad format for percent text " + text);
		text = text.substring(0, text.length() - 1).replace(",", "");
		return Float.valueOf(text);
	}
	
	public static Float returnPercentTextToFloat(String text) {
		Assert.notNull(text, "Return percent text null");
		if (text.equals("N/A")) {
			return null;
		}
		Assert.state(text.endsWith("%"), "Bad format for return percent text " + text);
		text = text.substring(0, text.length() - 1).replace(",", "");
		return Float.valueOf(text);
	}
	
	public static LocalDateTime parseDateTime(String text) {
		return LocalDateTime.parse(text, DATE_TIME_FORMATTER);
	}

}
