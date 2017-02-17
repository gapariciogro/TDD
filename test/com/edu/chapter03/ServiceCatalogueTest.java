package com.edu.chapter03;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class ServiceCatalogueTest {
	ServiceCatalogue servCat;
	
	@Before
	public void setup() {
		servCat = new ServiceCatalogueImpl();
	}
	
	@Test
	public void user_can_add_a_service_to_catalogue() {
		Procedure proc = this.getProcedure1();
		
		this.servCat.add(proc);
		
		assertNotNull(servCat.findById(proc.getId()));
		assertEquals(servCat.findById(proc.getId()), proc);
	}
	
	@Test
	public void catalogue_returns_null_for_an_unconfigured_procedure_id() {
		Procedure proc = this.getProcedure1();
		
		this.servCat.add(proc);
		
		assertNull(servCat.findById("5678"));
		
	}
	
	@Test
	public void catalogue_returns_procedure_and_price() {
		Procedure proc1 = this.getProcedure1();
		Procedure proc2 = this.getProcedure2();
				
		this.servCat.add(proc1);
		this.servCat.add(proc2);
		
		assertEquals(servCat.findPriceOf(proc1.getId()), BigDecimal.TEN);
		assertEquals(servCat.findPriceOf(proc2.getId()), BigDecimal.ONE);
		
	}
	

	private Procedure getProcedure1() {
		Procedure proc = new Procedure();
		proc.setId("1234");
		proc.setDescription("Basic Oxygen Setup");
		proc.setPrice(BigDecimal.TEN);
		return proc;
	}
	
	private Procedure getProcedure2() {
		Procedure proc = new Procedure();
		proc.setId("4567");
		proc.setDescription("Injection");
		proc.setPrice(BigDecimal.ONE);
		return proc;
	}
	
	
}
