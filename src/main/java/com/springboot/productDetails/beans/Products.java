package com.springboot.productDetails.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Products implements Comparable{
	private String productId;
	private String title;
	private ColorSwatches[] colorSwatches;
	private String nowPrice="";
	private String priceLabel="";
	@JsonProperty(access = Access.WRITE_ONLY)
	private Price price;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNowPrice() {
		return nowPrice;
	}

	public void setNowPrice(String nowPrice) {
		this.nowPrice = nowPrice;
	}

	public String getPriceLabel() {
		return priceLabel;
	}

	public void setPriceLabel(String priceLabel) {
		this.priceLabel = priceLabel;
	}

	//@JsonIgnore
	public ColorSwatches[] getColorSwatches() {
		return colorSwatches;
	}

	public void setColorSwatches(ColorSwatches[] colorSwatches) {
		this.colorSwatches = colorSwatches;
	}

	//@JsonIgnore
	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	@Override
	public int compareTo(Object o) {
		if(o !=null && o instanceof Products)
		{
			Products p = (Products)o;
			Double pnow = new Double(p.getPrice().getNow());
			Double now = new Double(price.getNow());
			double compare = now - pnow; 
			if(compare == 0)
			{
				return 0;
			}
			else if (compare > 0)
			{
				return 1;
			}
			else 
			{
				return -1;
			}
		}
		return 0;
	}

}
