package com.ott.iconomi.statistics.domain.model;

import org.springframework.core.style.ToStringCreator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE, makeFinal = true)
public class StructureElement {
	
	private int id;
	
	private Asset asset;
	
	private float percent;
	

	public String toString() {
		return new ToStringCreator(this)
				.append("id", id)
				.append("asset", asset)
				.append("percent", percent) 
				.toString();
	}
}
