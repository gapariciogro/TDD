package com.edu.chapter04.outside.in;

import java.math.BigDecimal;

public interface TaxCalculator {
	/**
	 * Calculate the tax to pay, based on the taxable income.
	 * @param taxableIncome Taxable income
	 * @return tax to pay amount.
	 */
	BigDecimal calculate(BigDecimal taxableIncome);
}
