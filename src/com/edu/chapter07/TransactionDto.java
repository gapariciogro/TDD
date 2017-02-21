package com.edu.chapter07;

import java.math.BigDecimal;

public class TransactionDto {
	private String developerId;
	private String targetPayPalId;
	private BigDecimal amount;

	/**
	 * Obtiene el valor de developerId.
	 * @return Valor de developerId
	 */
	public String getDeveloperId() {
		return developerId;
	}

	/**
	 * Establece el valor de developerId
	 * @param developerId Valor a establecer de developerId
	 */
	public void setDeveloperId(String developerId) {
		this.developerId = developerId;
	}

	/**
	 * Obtiene el valor de targetPayPalId.
	 * @return Valor de targetPayPalId
	 */
	public String getTargetPayPalId() {
		return targetPayPalId;
	}

	/**
	 * Establece el valor de targetPayPalId
	 * @param targetPayPalId Valor a establecer de targetPayPalId
	 */
	public void setTargetPayPalId(String targetPayPalId) {
		this.targetPayPalId = targetPayPalId;
	}

	/**
	 * Obtiene el valor de amount.
	 * @return Valor de amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * Establece el valor de amount
	 * @param amount Valor a establecer de amount
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	} 
}
