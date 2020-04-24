package com.barclaycard.currency.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="CURRENCY_MAPPINGS")
public class CurrencyEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="currency", nullable=false)
    private String currency;
    
    @Column(name="denomination", nullable=false)
    private String denomination;


	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	@Override
	public String toString() {
		return "CurrencyEntity{" +
				"id=" + id +
				", currency='" + currency + '\'' +
				", denomination='" + denomination + '\'' +
				'}';
	}
}