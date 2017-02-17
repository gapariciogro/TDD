package com.edu.chapter02;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class OnceYouBuyYouStartCryingTelephoneTest {
	private OnceYouBuyYouStartCryingTelephone tel = new OnceYouBuyYouStartCryingTelephone();
	
	
	@Test(expected=RuntimeException.class)
	public void when_input_firstName_is_null_then_throws_exception() {
		PersonName pName = new PersonName();
		tel.addConnection(pName, null, null);
		fail("Se permitieron parametros invalidos");
	}
	
	@Test(expected=RuntimeException.class)
	public void when_input_lastName_is_null_then_throws_exception() {
		PersonName pName = new PersonName();
		pName.setFirstName("Juan");
		tel.addConnection(pName, null, null);
		fail("Se permitieron parametros invalidos");
	}

	@Test(expected=RuntimeException.class)
	public void when_input_z_is_null_then_throws_exception() {
		PersonName pName = new PersonName();
		pName.setFirstName("Juan");
		pName.setLastName("Lopez");
		
		tel.addConnection(pName, null, null);
		fail("Se permitieron parametros invalidos");
	}


	@Test(expected=RuntimeException.class)
	public void when_input_gen_is_invalid_then_throws_exception() {
		PersonName pName = new PersonName();
		pName.setFirstName("Juan");
		pName.setLastName("Lopez");
		tel.addConnection(pName, new Date(), null);
		fail("Se permitieron parametros invalidos");
	}
	
	@Test
	public void when_valid_input_then_adds_input() {
		PersonName pName = new PersonName();
		pName.setFirstName("Juan");
		pName.setLastName("Lopez");
		assertNotNull(tel.addConnection(pName, new Date(), ConnectionType.TWO_G));
		assertNotNull(tel.addConnection(pName, new Date(), ConnectionType.THREE_G));
		//assertNotNull(tel.addConnection("Juan", null, null, "Lopez", new Date(), ConnectionType.FOUR_G));
	}
	
	@Test
	public void when_all_name_attributes_are_passed_then_forms_the_name() {
		PersonName pName = new PersonName();
		pName.setFirstName("Juan");
		pName.setMiddleName("Pablo");
		pName.setLastName("Lopez");
		pName.setPrefix("Lic.");

		String telNumber = tel.addConnection(pName, new Date(), ConnectionType.THREE_G);
		
		assertNotNull(telNumber);
		System.out.println("telNumber=" + telNumber);
		String billDetails = tel.bill(telNumber);
		System.out.println("billDetails=" + billDetails);
		assertTrue(billDetails.contains(pName.getPrefix()));
		assertTrue(billDetails.contains(pName.getLastName()));
		assertTrue(billDetails.contains(pName.getMiddleName()));
		assertTrue(billDetails.contains(pName.getFirstName()));
	}
}
