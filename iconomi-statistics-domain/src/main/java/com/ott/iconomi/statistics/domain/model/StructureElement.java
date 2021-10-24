package com.ott.iconomi.statistics.domain.model;

import lombok.experimental.NonFinal;
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

	@NonFinal
	private double usdValue;

	@NonFinal
	private double quantity;

	/**
	 *
	 * @param aum total usd value of all assets of the structure containing this StructureElement
	 * @param usdPrice usd price of {@link #asset}
	 */
	public void calculateQuantityAndUsdValue(double aum, double usdPrice) {
		usdValue = (aum * percent / 100);
		quantity = usdValue / usdPrice;
	}

//	private PriceHistory price;

	public String toString() {
		return new ToStringCreator(this)
				.append("id", id)
				.append("asset", asset)
				.append("percent", percent)
//				.append("price", price)
				.toString();
	}
}
