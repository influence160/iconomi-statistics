package com.ott.iconomi.statistics.data.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "strategy")
public class StrategyEntity {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "strategysec")
//	private int id;
	
	@Id
//	@Column(nullable = false, unique = true, updatable = false)
	private String shortName;
	
	private String name;
	
	private String manager;
	
	private boolean favorite;
	
	private LocalDateTime lastAction;
	
	private LocalDateTime dateCreated;
	
	private LocalDateTime dateUpdated;
	
	@OneToOne(mappedBy = "strategy", fetch = FetchType.LAZY)
	private CurrentStructureEntity currentStructure;

	@PrePersist
	public void prepersist() {
		dateCreated = dateUpdated = LocalDateTime.now();
		dateUpdated = LocalDateTime.now();
	}
	
	@PreUpdate
	public void preupdate() {
		dateUpdated = LocalDateTime.now();
	}


	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDateTime getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(LocalDateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public LocalDateTime getLastAction() {
		return lastAction;
	}

	public void setLastAction(LocalDateTime lastAction) {
		this.lastAction = lastAction;
	}

	public CurrentStructureEntity getCurrentStructure() {
		return currentStructure;
	}

	public void setCurrentStructure(CurrentStructureEntity currentStructure) {
		this.currentStructure = currentStructure;
		currentStructure.setStrategy(this);
	}
	
	public int hashCode() {
		Objects.requireNonNull(shortName);
		return Objects.hash(shortName);
	}
	
	public boolean equals(Object o)	{
		Objects.requireNonNull(shortName);
		return o == this || (o != null && o instanceof StrategyEntity && ((StrategyEntity)o).shortName.equals(this.shortName));
	}


	public String toString() {
		return new ToStringCreator(this)
				.append("shortName", this.shortName)
				.toString();
	}
}
