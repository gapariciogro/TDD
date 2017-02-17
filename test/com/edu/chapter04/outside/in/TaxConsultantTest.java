package com.edu.chapter04.outside.in;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * @author gaparicio
 *
 */
@Ignore
public class TaxConsultantTest {
	
	@Mock
	TaxableIncomeCalculator taxableIncomeCalculator;
	@Mock
	TaxCalculator taxCalculator;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void when_deductable_present_then_taxable_income_is_less_than_the_total_income() {
		
		TaxConsultant taxC = new TaxConsultantImpl();
		taxC.setTaxableIncomeCalculator(this.taxableIncomeCalculator);
		taxC.setTaxCalculator(this.taxCalculator);
		
		
		BigDecimal totalIncome = new BigDecimal("1200000");
		BigDecimal homeLoanInterest = new BigDecimal("150000");
		BigDecimal homeLoanPrincipal = new BigDecimal("20000");
		BigDecimal providentFundSavings = new BigDecimal("50000");
		BigDecimal lifeInsurancePremium = new BigDecimal("30000");
		
		taxC.calculateTaxableIncome(totalIncome, homeLoanInterest, 
				homeLoanPrincipal, providentFundSavings, lifeInsurancePremium);
	}
}
