package com.springboot.productDetails.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:ProductDetails.properties")
public final class ProductDetailsConstants {

	private static Map<String, String> colorMap = new HashMap<String, String>();

	@Value("${COLORS}")
	private String colorData;

	public ProductDetailsConstants() {
	}

	public String getRGB(String colorName) {
		return colorMap.get(colorName.toLowerCase());
	}

	public static NumberFormat getNumberFormat(String currencyCode,
			int maxFractionDigits) {
		NumberFormat n;
		switch (currencyCode) {
			case "GBP":
				n = NumberFormat.getCurrencyInstance(Locale.UK);
				break;
			default:
				n = NumberFormat.getCurrencyInstance(Locale.UK);
				break;

		}

		n.setMinimumFractionDigits(maxFractionDigits);
		return n;
	}

}
