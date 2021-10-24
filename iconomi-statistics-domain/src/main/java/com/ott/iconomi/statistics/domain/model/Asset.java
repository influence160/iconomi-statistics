package com.ott.iconomi.statistics.domain.model;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.EqualsAndHashCode;
import org.springframework.core.style.ToStringCreator;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Asset {
	
	public static final String OTHER_ASSETS_CCY = "OTHERS";

	public static final String OTHER_ASSETS_NAME = "Other Assets";
	
	public static final Set<String> STABLE_CCYS = Stream.of("USDT", "USDC", "TUSD", "DAI", "UST").collect(Collectors.toSet());
	
	public static Asset of(String ccy) {
		return new AssetBuilder().ccy(ccy).build();
	}

	public Asset(String ccy, String name) {
		this.ccy = ccy;
		this.name = name;
	}

	@EqualsAndHashCode.Include
	private String ccy;
	
	private String name;
	
	private Boolean stable = false;
	
	private Boolean otherAssets = false;


	public boolean isStable() {
		if (stable != null) {
			return stable;
		}
		return STABLE_CCYS.contains(ccy);
	}

	public boolean isOtherAssets() {
		if (otherAssets != null) {
			return otherAssets;
		}
		return OTHER_ASSETS_CCY.equals(ccy);
	}
	
	public String toString() {
		return new ToStringCreator(this)
				.append("ccy", ccy)
				.toString();
	}
}
