package com.edu.chapter04.outside.in;

import java.math.BigDecimal;

public interface TaxableIncomeCalculator {
	BigDecimal calculateTaxableIncome(BigDecimal totalIncome, BigDecimal homeLoanInterest,
			BigDecimal homeLoanPrincipal, BigDecimal providentFundSavings, BigDecimal lifeInsurancePremium);
}
