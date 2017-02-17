package com.edu.chapter02;

import java.util.HashMap;
import java.util.Map;

public class TwoGConnection implements PhoneConnection {
	
	private Map<String, String> numberAndNameMap = new HashMap<String, String>();
	
	@Override
	public boolean activate(String connectionForUserName, String number) {
		System.out.println("TwoGConnection::activate()");
		numberAndNameMap.put(number, connectionForUserName);
		return true;
	}

	@Override
	public String generateBillFor(String number) {
		return "2G bill for " + this.numberAndNameMap.get(number);
	}

}
