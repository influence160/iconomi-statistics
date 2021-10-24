package com.ott.iconomi.statistics.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal=true, level=AccessLevel.PRIVATE)
public class StructureHistorical extends Structure{
	
	private CurrentStructure currentStructure;

	@Builder()

	public StructureHistorical(int id, Float dayReturn, Float weekReturn, Float monthReturn, Float yearReturn, Float allTimeReturn, double aum, LocalDateTime lastChange, int numberOfChangesInLast30Days, LocalDateTime dateCreated, LocalDate dateStarted, List<StructureElement> elements, Snapshot snapshot, CurrentStructure currentStructure) {
		super(id, dayReturn, weekReturn, monthReturn, yearReturn, allTimeReturn, aum, lastChange, numberOfChangesInLast30Days, dateCreated, dateStarted, elements, snapshot);
		this.currentStructure = currentStructure;
	}
}
