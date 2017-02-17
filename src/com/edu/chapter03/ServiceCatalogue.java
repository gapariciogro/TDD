package com.edu.chapter03;

import java.math.BigDecimal;

public interface ServiceCatalogue {
	public void add(Procedure proc);
	
	public Procedure findById(String id);

	public BigDecimal findPriceOf(String id);
}
