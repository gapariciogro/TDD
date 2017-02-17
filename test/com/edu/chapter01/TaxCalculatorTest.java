package com.edu.chapter01;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class TaxCalculatorTest {

	@Test
	public void when_income_less_or_equal_than_500k_then_deduct_10pct_tax() {
		TaxCalculator taxCal = new TaxCalculator();
		BigDecimal payableTax = taxCal.calculate(new BigDecimal("400000"));
		assertTrue(new BigDecimal("40000").compareTo(payableTax) == 0);

	}
	
	@Test
	public void when_income_greater_than_500k_and_less_or_equal_than_1M_then_deducts_50k_plus_20pct_above_500k() {
		TaxCalculator taxCal = new TaxCalculator();
		BigDecimal payableTax = taxCal.calculate(new BigDecimal("800000.00"));
		BigDecimal expectedTaxForFirst500k = new BigDecimal("50000");
		BigDecimal expectedTaxForRemainder = new BigDecimal("60000");
		BigDecimal expectedTotalTax = expectedTaxForFirst500k.add(expectedTaxForRemainder);
		assertTrue(expectedTotalTax.compareTo(payableTax) == 0);
	}
	
	@Test
	public void when_income_greater_than_1M_then_deducts_150k_plus_30pct_above_1M() {
		TaxCalculator taxCal = new TaxCalculator();
		BigDecimal payableTax = taxCal.calculate(new BigDecimal("2000000.00"));
		BigDecimal expectedTaxForFirst500k = new BigDecimal("50000");
		BigDecimal expectedTaxForNext500k = new BigDecimal("100000");
		BigDecimal expectedTaxForRemainder = new BigDecimal("300000");
		BigDecimal expectedTotalTax = expectedTaxForFirst500k.add(expectedTaxForNext500k).add(expectedTaxForRemainder);

		assertTrue(expectedTotalTax.compareTo(payableTax) == 0);
		
	}
	
	@Test
	public void validate_tax_calculation_for_limits() {
		TaxCalculator taxCal = new TaxCalculator();
		BigDecimal payableTax = null;
		
		payableTax = taxCal.calculate(new BigDecimal("0"));
		assertTrue(new BigDecimal("0").compareTo(payableTax) == 0);
		
		payableTax = taxCal.calculate(new BigDecimal("500000"));
		assertTrue(new BigDecimal("50000").compareTo(payableTax) == 0);

		payableTax = taxCal.calculate(new BigDecimal("500000.00001"));
		assertTrue(new BigDecimal("50000.000002").compareTo(payableTax) == 0);

		payableTax = taxCal.calculate(new BigDecimal("1000000"));
		assertTrue(new BigDecimal("150000").compareTo(payableTax) == 0);

		payableTax = taxCal.calculate(new BigDecimal("1000000.01"));
		assertTrue(new BigDecimal("150000.003").compareTo(payableTax) == 0);
		
	}
	
	@Test(expected=RuntimeException.class)
	public void when_invalid_income_then_throw_Exception() {
		TaxCalculator taxCal = new TaxCalculator();
		taxCal.calculate(new BigDecimal("-100"));
		fail("El calculo permitió un monto inválido");
	}


}
