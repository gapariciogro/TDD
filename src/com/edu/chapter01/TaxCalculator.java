package com.edu.chapter01;

import java.math.BigDecimal;

/**
 * @author gaparicio
 *
 */
public class TaxCalculator {

	private final static BigDecimal TAX_PCT_RANGE_1 = new BigDecimal("0.10");
	private final static BigDecimal INCOME_RANGE_LIMIT_1 = new BigDecimal("500000");
	private final static BigDecimal TAX_PCT_RANGE_2 = new BigDecimal("0.20");
	private final static BigDecimal INCOME_RANGE_LIMIT_2 = new BigDecimal("1000000");
	private final static BigDecimal TAX_PCT_RANGE_3 = new BigDecimal("0.30");

	/**
	 * Calcula el monto de impuesto de acuerdo al ingreso.
	 * De $0 hasta $50,000.00 se aplica un porcentaje de 10%
	 * Sobre el excedente de $50,000 hasta $1,000,000.00 se aplica un porcentaje de 20%
	 * Sobre el excendente de $1,000,000 en adelante se aplica un porcentaje de 30%
	 * @param taxableIncome Ingreso considerado para el calculo de impuesto
	 * @return Monto del impuesto a pagar
	 */
	public BigDecimal calculate(BigDecimal taxableIncome) {
		
		if (!validParametersForCalculation(taxableIncome)) {
			throw new RuntimeException("Error en los parametros recibidos");
		}
		
		BigDecimal payableTax = null;
		
		if (isIncomeInFirstRange(taxableIncome)) {
			payableTax = taxableIncome.multiply(TAX_PCT_RANGE_1);
		}
		
		if (isIncomeInSecondRange(taxableIncome)) {
			BigDecimal basePayableTax = this.calculate(INCOME_RANGE_LIMIT_1);
			BigDecimal payableTaxOverExcedent = this.calculateExcedentOverFirstRange(taxableIncome).multiply(TAX_PCT_RANGE_2);
			payableTax = basePayableTax.add(payableTaxOverExcedent);
		}

		if (isIncomeInThirdRange(taxableIncome)) {
			BigDecimal basePayableTax = this.calculate(INCOME_RANGE_LIMIT_2);
			BigDecimal payableTaxOverExcedent = this.calculateExcedentOverSecondRange(taxableIncome).multiply(TAX_PCT_RANGE_3);
			payableTax = basePayableTax.add(payableTaxOverExcedent);
		}

		return payableTax;
	}
	
	/**
	 * Calcula el excedente sobre el primer rango.
	 * @param taxableIncome Ingreso sobre el que aplica el impuesto
	 * @return Monto del excedente
	 */
	private BigDecimal calculateExcedentOverFirstRange(BigDecimal taxableIncome) {
		return taxableIncome.subtract(INCOME_RANGE_LIMIT_1);
	}
	/**
	 * Calcula el excedente sobre el segundo rango.
	 * @param taxableIncome Ingreso sobre el que aplica el impuesto
	 * @return Monto del excedente
	 */
	private BigDecimal calculateExcedentOverSecondRange(BigDecimal taxableIncome) {
		return taxableIncome.subtract(INCOME_RANGE_LIMIT_2);
	}

	
	/**
	 * Rango $0-$500,000.00
	 * @param taxableIncome Ingreso sobre el que aplica el impuesto
	 * @return true si el ingreso esta dentro del primer rango o false de lo contrario
	 */
	private boolean isIncomeInFirstRange(BigDecimal taxableIncome) {
		return (taxableIncome.compareTo(INCOME_RANGE_LIMIT_1) <= 0); 
	}
	
	/**
	 * Rango $500,000.01-$1,000,000.00
	 * @param taxableIncome Ingreso sobre el que aplica el impuesto
	 * @return true si el ingreso esta dentro del primer rango o false de lo contrario
	 */
	private boolean isIncomeInSecondRange(BigDecimal taxableIncome) {
		return (taxableIncome.compareTo(INCOME_RANGE_LIMIT_1) > 0) && 
				(taxableIncome.compareTo(INCOME_RANGE_LIMIT_2) <= 0); 
	}
	
	/**
	 * Rango $1,000,000.01 en adelante
	 * @param taxableIncome Ingreso sobre el que aplica el impuesto
	 * @return true si el ingreso esta dentro del primer rango o false de lo contrario
	 */
	private boolean isIncomeInThirdRange(BigDecimal taxableIncome) {
		return (taxableIncome.compareTo(INCOME_RANGE_LIMIT_2) > 0); 
	}

	/**
	 * Valida que los parametros recibidos para el calculo del impuesto sean válidos
	 * @return true si son validos o false de lo contrario
	 */
	private boolean validParametersForCalculation(BigDecimal taxableIncome) {
		//Solo si es nulo o negativo es un parametro invalido
		return !(taxableIncome == null || taxableIncome.compareTo(new BigDecimal(0)) < 0); 
	}
}
