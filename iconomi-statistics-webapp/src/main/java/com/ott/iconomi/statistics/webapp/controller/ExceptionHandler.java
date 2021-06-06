package com.ott.iconomi.statistics.webapp.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ott.iconomi.statistics.webapp.exception.Throwables;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice(basePackages = "com.ott.iconomi.statistics.webapp.controller")
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
	public ModelAndView exception(Exception exception, WebRequest request) {
		ModelAndView modelAndView = new ModelAndView("error/error");
		exception.printStackTrace();
        Throwable rootCause = Throwables.getRootCause(exception);
        modelAndView.addObject("errorMessage", rootCause);
        log.error("ExceptionHandled");
        log.error(rootCause.toString(), exception);
		return modelAndView;
	}
}

