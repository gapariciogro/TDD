package com.edu.chapter04.outside.in;

import java.math.BigDecimal;

public class TaxConsultantImpl implements TaxConsultant {
	private TaxableIncomeCalculator taxableIncomeCalculator;
	private TaxCalculator taxCalculator;
	
	@Override
	public BigDecimal calculateTaxableIncome(BigDecimal totalIncome, BigDecimal homeLoanInterest,
			BigDecimal homeLoanPrincipal, BigDecimal providentFundSavings, BigDecimal lifeInsurancePremium) {
		return null;
	}

	@Override
	public BigDecimal calculatePayableTax() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void notifyClient() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTaxableIncomeCalculator(TaxableIncomeCalculator taxIncomeCalculator) {
		this.taxableIncomeCalculator = taxIncomeCalculator;
		
	}

	@Override
	public void setTaxCalculator(TaxCalculator taxCalculator) {
		this.taxCalculator = taxCalculator;
	}

}
