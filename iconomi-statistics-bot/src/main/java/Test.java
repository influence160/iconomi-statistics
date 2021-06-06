import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;


import com.ott.iconomi.statistics.bot.iconomi.IconomiPageHelper;
import com.ott.iconomi.statistics.bot.iconomi.pages.queries.AssetQueries;
import com.ott.iconomi.statistics.bot.iconomi.pages.queries.StrategiesQueries;

public class Test {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		TestParseFloat();
	}

	private static void TestParseFloat() throws ParseException {
		System.out.println(new Float(12.26));
		System.out.println(new Float("12.26"));
		System.out.println(IconomiPageHelper.returnPercentTextToFloat("+12.26%"));
		System.out.println(new DecimalFormat("+#,##0.00;-#,##0.00").parse("+12.26%".substring(0, "+12.26%".length() - 1)).doubleValue());
		DecimalFormat df = new DecimalFormat("+#,##0.00;-#,##0.00");
		df.setParseBigDecimal(true);
			df.setMaximumFractionDigits(3);
		System.out.println(df.parse("+12.26").doubleValue());

		System.out.println(Float.valueOf("-1,112.26"));
	}

	private static void testParseStringJsont() throws ParseException {
		int planet = 7;
		MessageFormat messageFormat = new MessageFormat("'{'\"demande\":{0}'}'");
		String result = messageFormat.format(new Object[] {planet});
		System.out.println(result);

		System.out.println(Arrays.toString(messageFormat.parse("{\"demande\":7}")));
		System.out.println(messageFormat.parse("{\"demande\":7}")[0].getClass());
	}

	private static void parseNumber() throws ParseException {
		//		String s0 = "578.26";
				String s1 = "+578.26";
				String s2 = "+1,578.26";
				String s3 = "-578.26";
				String s4 = "-1,578.26";
		
				NumberFormat numberFormat = new DecimalFormat("+#,##0.00;-#,##0.00");
				
				try {
		//			System.out.println(numberFormat.parse(s0));
					System.out.println(numberFormat.parse(s1));
					System.out.println(numberFormat.parse(s2));
					System.out.println(numberFormat.parse(s3));
					System.out.println(numberFormat.parse(s4));
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(numberFormat.parse(s1));
				System.out.println(numberFormat.parse(s2));
				System.out.println(numberFormat.parse(s3));
				System.out.println(numberFormat.parse(s4));
	}

	private static void testParseString() {
		int planet = 7;
		 String event = "a disturbance in the Force";

		 String result = MessageFormat.format(
		     "At {1,time} on {1,date}, '' there was {2} on planet {0,number,integer}.",
		     planet, new Date(), event);
		 System.out.println(result);
		 result = new MessageFormat("At {1,time} on {1,date}, there was {2} on planet {0,number,integer}.").format(
			     new Object[] { planet, new Date(), event});
			 System.out.println(result);
		System.out.println(StrategiesQueries.getStrategyBlock(3));
		System.out.println(AssetQueries.getStructureElementAssetName(1));
	}

	private static void testParseDate() {
		LocalDateTime date = LocalDateTime.now();
		  
		  //TODO 
//		  d       day-of-month                number            10			prendre date courte
		  
//		   h       clock-hour-of-am-pm (1-12)  number            12		prendre 12
		  
//		   u       year                        year              2004; 04
//		   y       year-of-era                 year              2004; 04
		  
		  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, y, h:mm:ss a").withLocale(Locale.US);
		  String text = date.format(formatter);
		  System.out.println(text);
		  TemporalAccessor parsedDate = formatter.parse(text);
		  System.out.println(parsedDate);
		  System.out.println(LocalDateTime.from(parsedDate));
		
		  //jour, Heure sans zero, minute et seconde avec zero
		String dt = "May 14, 2021, 6:34:37 AM";
		
		System.out.println(LocalDateTime.parse(dt, formatter));
		System.out.println(formatter.withLocale(Locale.US).parse(dt));
//		DateTimeFormatter d = DateTimeFormatter.ofLocalizedDate(F);
	}

}
