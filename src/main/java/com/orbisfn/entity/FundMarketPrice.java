package com.orbisfn.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fund_market_price")
public class FundMarketPrice {

	private double bid_ask;
	private double closingPrice;
	private double dayHigh;
	private double dayLow;
	private double exchangeVolume;
	private double discount_premium;

	public double getBid_ask() {
		return bid_ask;
	}

	public void setBid_ask(double bid_ask) {
		this.bid_ask = bid_ask;
	}

	public double getClosingPrice() {
		return closingPrice;
	}

	public void setClosingPrice(double closingPrice) {
		this.closingPrice = closingPrice;
	}

	public double getDayHigh() {
		return dayHigh;
	}

	public void setDayHigh(double dayHigh) {
		this.dayHigh = dayHigh;
	}

	public double getDayLow() {
		return dayLow;
	}

	public void setDayLow(double dayLow) {
		this.dayLow = dayLow;
	}

	public double getExchangeVolume() {
		return exchangeVolume;
	}

	public void setExchangeVolume(double exchangeVolume) {
		this.exchangeVolume = exchangeVolume;
	}

	public double getDiscount_premium() {
		return discount_premium;
	}

	public void setDiscount_premium(double discount_premium) {
		this.discount_premium = discount_premium;
	}

}
