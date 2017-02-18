package com.edu.chapter07;

import java.math.BigDecimal;

public class MembershipStatusDto {
	private BigDecimal deductable;

	/**
	 * Obtiene el valor de deductable.
	 * @return Valor de deductable
	 */
	public BigDecimal getDeductable() {
		return deductable;
	}

	/**
	 * Establece el valor de deductable
	 * @param deductable Valor a establecer de deductable
	 */
	public void setDeductable(BigDecimal deductable) {
		this.deductable = deductable;
	}
	


}
