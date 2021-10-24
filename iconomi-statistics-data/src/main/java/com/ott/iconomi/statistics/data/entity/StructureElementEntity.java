package com.ott.iconomi.statistics.data.entity;

import javax.persistence.*;

import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "structure_element")
public class StructureElementEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "structureelementsec")
	@SequenceGenerator(name = "structureelementsec", sequenceName = "structureelementsec", allocationSize = 1)
	private int id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asset_id")
	private AssetEntity asset;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "price_id")
	private PriceHistoryEntity price;
	
	private float percent;

	private Double usdValue;

	private Double quantity;

	
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

	public PriceHistoryEntity getPrice() {
		return price;
	}

	public void setPrice(PriceHistoryEntity price) {
		this.price = price;
	}

	public float getPercent() {
		return percent;
	}

	public void setPercent(float percent) {
		this.percent = percent;
	}

	public Double getUsdValue() {
		return usdValue;
	}

	public void setUsdValue(Double usdValue) {
		this.usdValue = usdValue;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String toString() {
		return new ToStringCreator(this)
				.append("id", id)
				.append("asset", asset)
				.append("percent", percent) 
				.toString();
	}
}
