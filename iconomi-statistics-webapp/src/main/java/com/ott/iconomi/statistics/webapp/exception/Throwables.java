package com.ott.iconomi.statistics.webapp.exception;

public class Throwables {
	
	public static Throwable getRootCause(Throwable throwable) {
		while (throwable.getCause() != null) {
			throwable = throwable.getCause();
		}
		return throwable;
	}

}
