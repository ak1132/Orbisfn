package com.orbisfn.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fund_top_holdings")
public class FundTopHoldings {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "fundsId", referencedColumnName = "id")
	private Funds funds;

	private String name;
	private double weight;
	private long sharesHeld;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Funds getFunds() {
		return funds;
	}

	public void setFunds(Funds funds) {
		this.funds = funds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public long getSharesHeld() {
		return sharesHeld;
	}

	public void setSharesHeld(long sharesHeld) {
		this.sharesHeld = sharesHeld;
	}

}
