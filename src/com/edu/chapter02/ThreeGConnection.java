/**
 * 
 */
package com.edu.chapter02;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jshernandez
 *
 */
public class ThreeGConnection implements PhoneConnection {

	private Map<String, String> numberAndNameMap = new HashMap<String, String>();
	
	/* (non-Javadoc)
	 * @see com.edu.chapter02.PhoneConnection#activate(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean activate(String connectionForUserName, String number) {
		System.out.println("ThreeGConnection::activate()");
		numberAndNameMap.put(number, connectionForUserName);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.edu.chapter02.PhoneConnection#generateBillFor(java.lang.String)
	 */
	@Override
	public String generateBillFor(String number) {
		return "3G bill for " + this.numberAndNameMap.get(number);
	}

}
