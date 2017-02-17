package com.edu.chapter04.outside.in;

import java.math.BigDecimal;

/**
 * @author gaparicio
 *
 */
public interface TaxConsultant {
	
	/**
	 * Calcule the amount of taxable income. 
	 * @return Amount of taxable income
	 */
	public BigDecimal calculateTaxableIncome(BigDecimal totalIncome, BigDecimal homeLoanInterest, 
			BigDecimal homeLoanPrincipal, BigDecimal providentFundSavings, BigDecimal lifeInsurancePremium);
	
	/**
	 * Calculate the amount of tax to pay.
	 * @return Amount to pay
	 */
	public BigDecimal calculatePayableTax();
	
	/**
	 * Notify the client via email the tax calculations made.
	 */
	public void notifyClient();
	
	/**
	 * Set the tax income calculator to use
	 */
	public void setTaxableIncomeCalculator(TaxableIncomeCalculator taxIncomeCalculator);

	/**
	 * Set the tax income calculator to use
	 */
	public void setTaxCalculator(TaxCalculator taxCalculator);

}
