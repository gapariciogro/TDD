package com.edu.chapter07;

import java.math.BigDecimal;

public class PaymentAdviceDto {
	private final BigDecimal amount;
	private final String targetPayPalId;
	private final String desc;

	public PaymentAdviceDto(BigDecimal amount, String targetPayPalId, String desc) {
		this.amount = amount;
		this.targetPayPalId = targetPayPalId;
		this.desc = desc;
	}

	/**
	 * Obtiene el valor de amount.
	 * @return Valor de amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * Obtiene el valor de targetPayPalId.
	 * @return Valor de targetPayPalId
	 */
	public String getTargetPayPalId() {
		return targetPayPalId;
	}

	/**
	 * Obtiene el valor de desc.
	 * @return Valor de desc
	 */
	public String getDesc() {
		return desc;
	}
}
