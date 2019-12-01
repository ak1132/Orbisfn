package com.orbisfn.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sector_allocation")
public class SectorAllocation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "fundsId", referencedColumnName = "id")
	private Funds funds;

	@Temporal(TemporalType.DATE)
	private Date asofDate;

	private String label;
	private double value;

	private int type;

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

	public Date getAsofDate() {
		return asofDate;
	}

	public void setAsofDate(Date asofDate) {
		this.asofDate = asofDate;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
