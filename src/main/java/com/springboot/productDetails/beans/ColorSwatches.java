package com.springboot.productDetails.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ColorSwatches {
	private String color;
	private String rgbColor;
	private String skuId;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String basicColor;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getRgbColor() {
		return rgbColor;
	}

	public void setRgbColor(String rgbColor) {
		this.rgbColor = rgbColor;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getBasicColor() {
		return basicColor;
	}

	public void setBasicColor(String basicColor) {
		this.basicColor = basicColor;
	}

}
