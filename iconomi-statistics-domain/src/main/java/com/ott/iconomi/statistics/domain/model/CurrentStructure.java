package com.ott.iconomi.statistics.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Getter
@FieldDefaults(makeFinal=true, level=AccessLevel.PRIVATE)
public class CurrentStructure extends Structure {
	
	private LocalDateTime dateUpdated;

	@NonFinal
	protected String strategyShortName;

	private List<StructureHistorical> structureHistoricals;

	@Builder
	public CurrentStructure(int id, Float dayReturn, Float weekReturn, Float monthReturn, Float yearReturn, Float allTimeReturn, double aum, LocalDateTime lastChange, int numberOfChangesInLast30Days, LocalDateTime dateCreated, LocalDate dateStarted, List<StructureElement> elements, Snapshot snapshot, LocalDateTime dateUpdated, String strategyShortName, List<StructureHistorical> structureHistoricals) {
		super(id, dayReturn, weekReturn, monthReturn, yearReturn, allTimeReturn, aum, lastChange, numberOfChangesInLast30Days, dateCreated, dateStarted, elements, snapshot);
		this.dateUpdated = dateUpdated;
		this.strategyShortName = strategyShortName;
		this.structureHistoricals = structureHistoricals;
	}
}
