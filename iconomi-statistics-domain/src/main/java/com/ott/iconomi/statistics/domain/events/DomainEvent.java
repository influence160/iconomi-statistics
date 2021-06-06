package com.ott.iconomi.statistics.domain.events;

import org.springframework.context.ApplicationEvent;

public class DomainEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	public DomainEvent(Object source) {
		super(source);
	}

}
