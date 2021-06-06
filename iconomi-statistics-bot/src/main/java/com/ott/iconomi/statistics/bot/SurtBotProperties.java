package com.ott.iconomi.statistics.bot;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "surt.bot")
public class SurtBotProperties {
	
	private Webdriver webdriver;
	
	
	public static class Webdriver {
		
		private String impl;
		
		private String gecoLocation;

		private String chromeLocation;

		public String getImpl() {
			return impl;
		}

		public void setImpl(String impl) {
			this.impl = impl;
		}

		public String getGecoLocation() {
			return gecoLocation;
		}

		public void setGecoLocation(String gecoLocation) {
			this.gecoLocation = gecoLocation;
		}

		public String getChromeLocation() {
			return chromeLocation;
		}

		public void setChromeLocation(String chromeLocation) {
			this.chromeLocation = chromeLocation;
		}
		
		public boolean isChrome() {
			return impl.equals("chrome");
		}
		
		public boolean isGeco() {
			return impl.equals("geco");
		}
		
	}


	public Webdriver getWebdriver() {
		return webdriver;
	}


	public void setWebdriver(Webdriver webdriver) {
		this.webdriver = webdriver;
	}
	
	

}
