package com.edu.chapter03;

import java.math.BigDecimal;

public class Procedure {
	private String id;
	private String description;
	private BigDecimal price;
	
	public Procedure() {}

	/**
	 * Obtiene el valor de id.
	 * @return Valor de id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Establece el valor de id
	 * @param id Valor a establecer de id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Obtiene el valor de description.
	 * @return Valor de description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Establece el valor de description
	 * @param description Valor a establecer de description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Obtiene el valor de price.
	 * @return Valor de price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * Establece el valor de price
	 * @param price Valor a establecer de price
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
