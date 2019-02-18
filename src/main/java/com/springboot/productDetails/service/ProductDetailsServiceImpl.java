package com.springboot.productDetails.service;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.springboot.productDetails.beans.ColorSwatches;
import com.springboot.productDetails.beans.Price;
import com.springboot.productDetails.beans.Products;
import com.springboot.productDetails.beans.Response;
import com.springboot.productDetails.utils.ProductDetailsConstants;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {

	@Value("${com.springboot.productDetails.url}")
	private String url;

	@Autowired
	ProductDetailsConstants productUtils;

	@Override
	public Response getProducts(String labelType) throws Exception {

		RestTemplate restTemplate = new RestTemplate();
		Response obj = restTemplate.getForObject(url, Response.class);
		return transformResponse(obj, labelType);
	}

	private Response transformResponse(Response response, String labelType) {
		Response transformedResponse = new Response();
		transformedResponse.setProducts(getProcessableProducts(
				response.getProducts(), labelType));
		return transformedResponse;
	}

	private Products[] getProcessableProducts(Products[] products,
			String labelType) {
		List<Products> productsList = new LinkedList<Products>();
		for (Products product : products) {

			Price price = product.getPrice();
			if (price != null) {
				if ((price.getWas() == null || StringUtils.isEmpty(price
						.getWas()))
						|| (price.getNow() == null || StringUtils.isEmpty(price
								.getWas()))) {
					continue;
				}
			}

			// populate RGP color code for each basic color.
			if (product.getColorSwatches() != null) {
				transformColorSwatches(product.getColorSwatches());
			}

			// populate nowPrice
			populateNowPrice(product);

			// populate PriceLabel
			populatePriceLabel(product, (labelType == null ? "ShowWasNow"
					: labelType));

			productsList.add(product);
		}

		// sort by reduction
		Collections.sort(productsList, Collections.reverseOrder());

		Products[] productArray = productsList
				.toArray(new Products[productsList.size()]);

		return productArray;
		// return productsList;
	}

	/*
	 * This method populates RGB color to colorSwatches
	 */
	private void transformColorSwatches(ColorSwatches[] colorSwatchesArray) {
		Arrays.stream(colorSwatchesArray).forEach(
				colorSwatches -> colorSwatches.setRgbColor((productUtils
						.getRGB(colorSwatches.getBasicColor()))));
	}

	/*
	 * This function populates the PriceLabel.
	 */
	public void populatePriceLabel(Products product, String labelType) {

		StringBuilder builder = new StringBuilder();
		switch (labelType) {
		case "ShowWasThenNow":
			builder.append("Was ")
					.append(getFormattedPrice(product.getPrice().getWas()))
					.append(", then ")
					.append(((product.getPrice().getThen2() != null && product
							.getPrice().getThen2().length() != 0) ? getFormattedPrice(product
							.getPrice().getThen2()) : getFormattedPrice(product
							.getPrice().getThen1()))).append(", now ")
					.append(getFormattedPrice(product.getPrice().getNow()));
			break;
		case "ShowPercDscount":
			String was = product.getPrice().getWas();
			String now = product.getPrice().getNow();
			Double wasDouble = new Double(was);
			Double nowDouble = new Double(now);
			double diff = wasDouble - nowDouble;
			double percentage = (diff / wasDouble * 100.00d);
			builder.append(Math.round(percentage)).append("% off - now ")
					.append(getFormattedPrice(now));
			break;
		// handles all including ShowWasNow
		default:
			// ShowWasNow
			builder.append("Was ")
					.append(getFormattedPrice(product.getPrice().getWas()))
					.append(", now ")
					.append(getFormattedPrice(product.getPrice().getNow()));
			break;

		}
		product.setPriceLabel(builder.toString());
	}

	/*
	 * This function populates the nowPrice.
	 */
	public void populateNowPrice(Products product) {
		Price price = product.getPrice();
		if (price != null) {
			product.setNowPrice(getFormattedPrice(price.getNow()));
		}
	}

	/*
	 * This method returns formatted price
	 */
	private String getFormattedPrice(String price) {
		DecimalFormat format = new DecimalFormat("##.##");
		if (price != null && price.length() != 0) {
			// to display in decimal because price < 10
			double nowDouble;

			// to display in integer because price is > 10
			long nowLong;
			String nowPrice = "";

			// to display fractionPoints.
			int maxFractionDigits = 0;
			nowDouble = Double.parseDouble(price.trim());

			if (nowDouble < 10) {
				nowLong = Long.parseLong(price.trim());
				if (nowDouble != nowLong) {
					nowPrice = format.format(nowDouble);
				}
				maxFractionDigits = 2;
			} else {
				nowPrice = format
						.format((long) Double.parseDouble(price.trim()));
				maxFractionDigits = 0;
			}

			String formattedPrice = ProductDetailsConstants.getNumberFormat(
					"GDP", maxFractionDigits).format(new Double(nowPrice));
			return formattedPrice;
		}
		return "";
	}
}
