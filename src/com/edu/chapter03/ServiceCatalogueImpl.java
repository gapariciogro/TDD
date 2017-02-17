package com.edu.chapter03;

import java.math.BigDecimal;
import java.util.Map;
import java.util.HashMap;

public class ServiceCatalogueImpl implements ServiceCatalogue {
	
	private Map<String, Procedure> mProcedures = new HashMap<>();
	
	@Override
	public void add(Procedure proc) {
		this.mProcedures.put(proc.getId(), proc);
	}

	@Override
	public Procedure findById(String id) {
		return this.mProcedures.get(id);
	}

	@Override
	public BigDecimal findPriceOf(String id) {
		return this.mProcedures.get(id).getPrice();
	}
	

}
