package com.springboot.productDetails.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Price {
	private String was;
	private String then1;
	private String then2;
	private String now;
	private String uom;
	private String currency;

	public String getWas() {
		return was;
	}

	public void setWas(String was) {
		this.was = was;
	}

	public String getThen1() {
		return then1;
	}

	public void setThen1(String then1) {
		this.then1 = then1;
	}

	public String getThen2() {
		return then2;
	}

	public void setThen2(String then2) {
		this.then2 = then2;
	}

	public String getNow() {
		return now;
	}

	public void setNow(Object now) {
		// System.out.println(now.getClass().getName());
		if (now != null && now instanceof String) {
			this.now = now.toString();
		} else {
			now = "";
		}
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	class Now {
		private String from;
		private String to;

		public String getFrom() {
			return from;
		}

		public void setFrom(String from) {
			this.from = from;
		}

		public String getTo() {
			return to;
		}

		public void setTo(String to) {
			this.to = to;
		}

	}

}
