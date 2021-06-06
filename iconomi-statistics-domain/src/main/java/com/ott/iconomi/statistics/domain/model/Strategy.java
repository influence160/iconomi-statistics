package com.ott.iconomi.statistics.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@FieldDefaults(makeFinal=true, level=AccessLevel.PRIVATE)
public class Strategy {
	
	private String shortName;
	
	private String name;
	
	private String manager;
	
	private boolean favorite;
	
	private LocalDateTime lastAction;
	
	private LocalDateTime dateCreated;
	
	private LocalDateTime dateUpdated;
	
	protected CurrentStructure currentStructure;

	public Strategy(String shortName, String name, String manager, boolean favorite, LocalDateTime lastAction, LocalDateTime dateCreated, LocalDateTime dateUpdated, CurrentStructure currentStructure) {
		this.shortName = shortName;
		this.name = name;
		this.manager = manager;
		this.favorite = favorite;
		this.lastAction = lastAction;
		this.dateCreated = dateCreated;
		this.dateUpdated = dateUpdated;
		this.currentStructure = currentStructure;
	}


	public int hashCode() {
		Objects.requireNonNull(shortName);
		return Objects.hash(shortName);
	}
	
	public boolean equals(Object o)	{
		Objects.requireNonNull(shortName);
		return o == this || (o != null && o instanceof Strategy && ((Strategy)o).shortName.equals(this.shortName));
	}


}
