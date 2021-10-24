package com.ott.iconomi.statistics.data.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import org.springframework.core.style.ToStringCreator;

@MappedSuperclass
//@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class StructureEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "structuresec")
	@SequenceGenerator(name = "structuresec", sequenceName = "structuresec", allocationSize = 1)
	private int id;
	
	protected Float dayReturn;

	protected Float weekReturn;

	protected Float monthReturn;

	protected Float yearReturn;

	protected Float allTimeReturn;

	protected Double aum;

	protected LocalDateTime lastChange;

	protected int numberOfChangesInLast30Days;

	protected LocalDateTime dateCreated;

	protected LocalDate dateStarted;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	protected List<StructureElementEntity> elements;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "snapshot_id", nullable = false)
	protected SnapshotEntity snapshot;

	
	protected StructureEntity() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Float getDayReturn() {
		return dayReturn;
	}

	public void setDayReturn(Float dayReturn) {
		this.dayReturn = dayReturn;
	}

	public Float getWeekReturn() {
		return weekReturn;
	}

	public void setWeekReturn(Float weekReturn) {
		this.weekReturn = weekReturn;
	}

	public Float getMonthReturn() {
		return monthReturn;
	}

	public void setMonthReturn(Float monthReturn) {
		this.monthReturn = monthReturn;
	}

	public Float getYearReturn() {
		return yearReturn;
	}

	public void setYearReturn(Float yearReturn) {
		this.yearReturn = yearReturn;
	}

	public Float getAllTimeReturn() {
		return allTimeReturn;
	}

	public void setAllTimeReturn(Float allTimeReturn) {
		this.allTimeReturn = allTimeReturn;
	}

	public LocalDateTime getLastChange() {
		return lastChange;
	}

	public void setLastChange(LocalDateTime lastChange) {
		this.lastChange = lastChange;
	}

	public Double getAum() {
		return aum;
	}

	public void setAum(Double aum) {
		this.aum = aum;
	}

	public int getNumberOfChangesInLast30Days() {
		return numberOfChangesInLast30Days;
	}

	public void setNumberOfChangesInLast30Days(int numberOfChangesInLast30Days) {
		this.numberOfChangesInLast30Days = numberOfChangesInLast30Days;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDate getDateStarted() {
		return dateStarted;
	}

	public void setDateStarted(LocalDate dateStarted) {
		this.dateStarted = dateStarted;
	}

	public List<StructureElementEntity> getElements() {
		return elements;
	}

	public void setElements(List<StructureElementEntity> elements) {
		this.elements = elements;
	}
	
	public void addElement(StructureElementEntity element) {
		elements.add(element);
		//element.setCurrentStructure
	}

	public SnapshotEntity getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(SnapshotEntity snapshot) {
		this.snapshot = snapshot;
	}

	public String toString() {
		return new ToStringCreator(this)
				.append("id", id)
				.append("elements", elements)
				.toString();
	}

}
