package com.orbisfn.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fund_performance")
public class FundPerformance {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne
	@JoinColumn(name = "fundsId", referencedColumnName = "id")
	private Funds funds;

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

	private String _1Month;
	private String qtd;
	private String ytd;
	private String _1Year;
	private String _3Year;
	private String _5Year;
	private String _10year;
	private String inception;

	public String get_1Month() {
		return _1Month;
	}

	public void set_1Month(String _1Month) {
		this._1Month = _1Month;
	}

	public String getQtd() {
		return qtd;
	}

	public void setQtd(String qtd) {
		this.qtd = qtd;
	}

	public String getYtd() {
		return ytd;
	}

	public void setYtd(String ytd) {
		this.ytd = ytd;
	}

	public String get_1Year() {
		return _1Year;
	}

	public void set_1Year(String _1Year) {
		this._1Year = _1Year;
	}

	public String get_3Year() {
		return _3Year;
	}

	public void set_3Year(String _3Year) {
		this._3Year = _3Year;
	}

	public String get_5Year() {
		return _5Year;
	}

	public void set_5Year(String _5Year) {
		this._5Year = _5Year;
	}

	public String get_10year() {
		return _10year;
	}

	public void set_10year(String _10year) {
		this._10year = _10year;
	}

	public String getInception() {
		return inception;
	}

	public void setInception(String inception) {
		this.inception = inception;
	}

}
