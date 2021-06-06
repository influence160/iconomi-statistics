package com.ott.iconomi.statistics.data.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.core.style.ToStringCreator;


@Entity
@Table(name = "asset")
@Cacheable(true)
public class AssetEntity {
	
	public static AssetEntity of(String ccy) {
		AssetEntity asset = new AssetEntity();
		asset.setCcy(ccy);
		return asset;
	}

	@Id
	@Column(length = 10)
	private String ccy;
	
	@Column(nullable = false, length = 50, unique = true, updatable = false)
	private String name;
	
	@Column(updatable = false)
	private boolean stable = false;
	
	@Column(updatable = false)
	private boolean otherAssets = false;
	
	@PrePersist
	public void prePersist() {
//		if (ccy.equals(OTHER_ASSETS_CCY)) {
//			otherAssets = true;
//		}
//		if (FIAT_CCYS.contains(ccy)) {
//			fiat = true;
//		}
	}
	
	@PreUpdate
	private void preUpdate() {
		throw new RuntimeException("You cant update a Asset");
	}
	

	public String getName() {
		return name;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isStable() {
		return stable;
	}

	public void setStable(boolean stable) {
		this.stable = stable;
	}

	public boolean isOtherAssets() {
		return otherAssets;
	}

	public void setOtherAssets(boolean otherAssets) {
		this.otherAssets = otherAssets;
	}

	public String toString() {
		return new ToStringCreator(this)
				.append("ccy", ccy)
				.toString();
	}
}
