package com.ott.iconomi.statistics.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.core.style.ToStringCreator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter 
@FieldDefaults(level=AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Structure {
	
	private int id;
	
	private Float dayReturn;
	
	private Float weekReturn;
	
	private Float monthReturn;
	
	private Float yearReturn;
	
	private Float allTimeReturn;
	
	private LocalDateTime lastChange;
	
	private int numberOfChangesInLast30Days;
	
	private LocalDateTime dateCreated;
	
	private LocalDate dateStarted;
	
	private List<StructureElement> elements;

	protected Snapshot snapshot;
	
	
	public String toString() {
		return new ToStringCreator(this)
				.append("id", id)
				.append("elements", elements)
				.toString();
	}

}
