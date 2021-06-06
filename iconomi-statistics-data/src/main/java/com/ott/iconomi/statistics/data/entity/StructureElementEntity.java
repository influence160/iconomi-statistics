package com.ott.iconomi.statistics.data.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "structure_element")
public class StructureElementEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "structureelementsec")
	private int id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asset_id")
	private AssetEntity asset;
	
	private float percent;

	
	@PreUpdate
	private void preUpdate() {
		throw new RuntimeException("You cant update a StructureElement");
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AssetEntity getAsset() {
		return asset;
	}

	public void setAsset(AssetEntity asset) {
		this.asset = asset;
	}

	public float getPercent() {
		return percent;
	}

	public void setPercent(float percent) {
		this.percent = percent;
	}
	

	public String toString() {
		return new ToStringCreator(this)
				.append("id", id)
				.append("asset", asset)
				.append("percent", percent) 
				.toString();
	}
}
