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

	private double _1Month;
	private double qtd;
	private double ytd;
	private double _1Year;
	private double _3Year;
	private double _5Year;
	private double _10year;
	private double inception;
	private double _1Month_Q;
	private double qtd_Q;
	private double ytd_Q;
	private double _1Year_Q;
	private double _3Year_Q;
	private double _5Year_Q;
	private double _10year_Q;
	private double inception_Q;

	public double get_1Month() {
		return _1Month;
	}

	public void set_1Month(double _1Month) {
		this._1Month = _1Month;
	}

	public double getQtd() {
		return qtd;
	}

	public void setQtd(double qtd) {
		this.qtd = qtd;
	}

	public double getYtd() {
		return ytd;
	}

	public void setYtd(double ytd) {
		this.ytd = ytd;
	}

	public double get_1Year() {
		return _1Year;
	}

	public void set_1Year(double _1Year) {
		this._1Year = _1Year;
	}

	public double get_3Year() {
		return _3Year;
	}

	public void set_3Year(double _3Year) {
		this._3Year = _3Year;
	}

	public double get_5Year() {
		return _5Year;
	}

	public void set_5Year(double _5Year) {
		this._5Year = _5Year;
	}

	public double get_10year() {
		return _10year;
	}

	public void set_10year(double _10year) {
		this._10year = _10year;
	}

	public double getInception() {
		return inception;
	}

	public void setInception(double inception) {
		this.inception = inception;
	}

	public double get_1Month_Q() {
		return _1Month_Q;
	}

	public void set_1Month_Q(double _1Month_Q) {
		this._1Month_Q = _1Month_Q;
	}

	public double getQtd_Q() {
		return qtd_Q;
	}

	public void setQtd_Q(double qtd_Q) {
		this.qtd_Q = qtd_Q;
	}

	public double getYtd_Q() {
		return ytd_Q;
	}

	public void setYtd_Q(double ytd_Q) {
		this.ytd_Q = ytd_Q;
	}

	public double get_1Year_Q() {
		return _1Year_Q;
	}

	public void set_1Year_Q(double _1Year_Q) {
		this._1Year_Q = _1Year_Q;
	}

	public double get_3Year_Q() {
		return _3Year_Q;
	}

	public void set_3Year_Q(double _3Year_Q) {
		this._3Year_Q = _3Year_Q;
	}

	public double get_5Year_Q() {
		return _5Year_Q;
	}

	public void set_5Year_Q(double _5Year_Q) {
		this._5Year_Q = _5Year_Q;
	}

	public double get_10year_Q() {
		return _10year_Q;
	}

	public void set_10year_Q(double _10year_Q) {
		this._10year_Q = _10year_Q;
	}

	public double getInception_Q() {
		return inception_Q;
	}

	public void setInception_Q(double inception_Q) {
		this.inception_Q = inception_Q;
	}

}
