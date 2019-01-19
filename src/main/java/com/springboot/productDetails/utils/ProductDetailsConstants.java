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
@PropertySource("ProductDetails.properties")
public final class ProductDetailsConstants {

	private static Map<String, String> colorMap = new HashMap<String, String>();

	@Value("${COLORS}")
	private String colorData;

	public ProductDetailsConstants() {
		// String colorDetails []= colorData.split("~");
		// for(String colorDetail: colorDetails)
		// {
		// String[] colorWithRGB = colorDetail.split(":");
		// colorMap.put(colorWithRGB[0], colorWithRGB[1]);
		// }

		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream stream = loader
				.getResourceAsStream("ProductDetails.properties");
		try {
			prop.load(stream);
			String colorDetails[] = ((String) prop.get("COLORS")).split("~");
			for (String colorDetail : colorDetails) {
				String[] colorWithRGB = colorDetail.split(":");
				colorMap.put(colorWithRGB[0], colorWithRGB[1]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
