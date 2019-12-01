package com.orbisfn.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "funds")
public class Funds {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String ticker;
	private String primaryBenchmark;
	private String CUSIP;
	private boolean optionsAvailable;
	private double grossExpenseRatio;

	@Temporal(TemporalType.DATE)
	private Date inceptionDate;

	private String investmentManager;
	private String managementTeam;
	private String distributor;
	private String distributionFrequency;
	private String exchange;

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public String getPrimaryBenchmark() {
		return primaryBenchmark;
	}

	public void setPrimaryBenchmark(String primaryBenchmark) {
		this.primaryBenchmark = primaryBenchmark;
	}

	public String getCUSIP() {
		return CUSIP;
	}

	public void setCUSIP(String cUSIP) {
		CUSIP = cUSIP;
	}

	public boolean isOptionsAvailable() {
		return optionsAvailable;
	}

	public void setOptionsAvailable(boolean optionsAvailable) {
		this.optionsAvailable = optionsAvailable;
	}

	public double getGrossExpenseRatio() {
		return grossExpenseRatio;
	}

	public void setGrossExpenseRatio(double grossExpenseRatio) {
		this.grossExpenseRatio = grossExpenseRatio;
	}

	public Date getInceptionDate() {
		return inceptionDate;
	}

	public void setInceptionDate(Date inceptionDate) {
		this.inceptionDate = inceptionDate;
	}

	public String getInvestmentManager() {
		return investmentManager;
	}

	public void setInvestmentManager(String investmentManager) {
		this.investmentManager = investmentManager;
	}

	public String getManagementTeam() {
		return managementTeam;
	}

	public void setManagementTeam(String managementTeam) {
		this.managementTeam = managementTeam;
	}

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	public String getDistributionFrequency() {
		return distributionFrequency;
	}

	public void setDistributionFrequency(String distributionFrequency) {
		this.distributionFrequency = distributionFrequency;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

}
