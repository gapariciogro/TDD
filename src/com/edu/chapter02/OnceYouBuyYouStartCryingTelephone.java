package com.edu.chapter02;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OnceYouBuyYouStartCryingTelephone {
	public static final int TWO_G = 2;
	public static final int THREE_G = 3;
	public static final int FOUR_G = 4;
//	private Map<String, String> names = new HashMap<String, String>();
//	private Map<String, Integer> types = new HashMap<String, Integer>();
//	private Map<String, Date> cd = new HashMap<String, Date>();
	
	
	private Map<ConnectionType, PhoneConnection> connectionForATypeMap = new HashMap<>();
	
	private Map<String, ConnectionType> connectionTypeForANumber= new HashMap<>();
	
	public OnceYouBuyYouStartCryingTelephone() {
		initialize();
	}
	
	protected void initialize() {
		connectionForATypeMap.put(ConnectionType.TWO_G, new TwoGConnection());
		connectionForATypeMap.put(ConnectionType.THREE_G, new ThreeGConnection());
	}

	/**
	 * This method activates a connection for a customer and stores different
	 * information in following maps for future use names, types and cd. if the
	 * connection type is 2G then requests TRY for a valid 2G number. if
	 * portability is not an issue then TRY provides a valid number, that number
	 * is stored for the customer. Then we activate the connection. For 3G -
	 * user needs data plan , we don't ask TRY for 3G...we don't have permission
	 * for 3G data in many cities, so we will hack TRY database and assign an
	 * id. If TRY catches us then we will disconnect the data plan and
	 * deactivate the customer. Is there any legal consumer forum issue? For 4G-
	 * we don't have 4th generation spectrum. we will provide 3G with a wrapper
	 * of 4G
	 * 
	 * @param firstName
	 * @param prefix
	 * @param middleName
	 * @param lastName
	 * @param fechaAlta
	 * @param connType
	 * @return
	 **/
	public String addConnection(PersonName pName, Date fechaAlta, ConnectionType connType) {
		if (pName.getFirstName() == null || pName.getLastName() == null || fechaAlta == null) {
			throw new RuntimeException();
		}
		
		String personName = pName.getFormattedName();
		String assignedTelNum = "5534814581"; //Should be dynamic
		//names.put(assignedTelNum, personName);
		//cd.put(assignedTelNum, z);
		
		connectionTypeForANumber.put(assignedTelNum, connType);
		
		PhoneConnection conn = connectionForATypeMap.get(connType);

		
		if (conn == null) {
			throw new IllegalStateException();
		}
		conn.activate(personName, assignedTelNum);

/*
		if (connType == TWO_G) {
			activate2GCon(n);
			types.put(n, TWO_G);
		} else if (connType == THREE_G) {
			activate3GCon(n);
			types.put(n, THREE_G);
		} else if (connType == FOUR_G) {
			activate4GCon(n);
			types.put(n, FOUR_G);
		} else {
			throw new IllegalStateException();
		}
*/
		return assignedTelNum;
	}


/*	private void activate4GCon(String n) {
		// TODO Auto-generated method stub
		
	}

	private void activate3GCon(String n) {
		// TODO Auto-generated method stub
		
	}

	private void activate2GCon(String n) {
		// TODO Auto-generated method stub
		
	}
*/
	
	/**
	 * This method takes number as input and generates post paid bills
	 * @param number
	 * @return
	 **/
	public String bill(String number) {
		ConnectionType connType = this.connectionTypeForANumber.get(number);
		if (connType == null) {
			throw new RuntimeException("No se pudo obtener el tipo de conexion");
		}
		PhoneConnection phoneConn = this.connectionForATypeMap.get(connType);
		return phoneConn.generateBillFor(number);		
		
/*
		Integer gen = types.get(number);
		if (gen == null) {
			throw new RuntimeException();
		}
		switch (gen.intValue()) {
			case TWO_G:
				return gen2GBill(number);
			case THREE_G:
				return gen3GBill(number);
			case FOUR_G:
				return gen4GBill(number);
			default:
				break;
		}
		return "";
*/
	}

/*
	private String gen4GBill(String n) {
		return this.names.get(n);
	}

	private String gen3GBill(String n) {
		return this.names.get(n);	}

	private String gen2GBill(String n) {
		return this.names.get(n);	}
*/
	public void chargeIncomingSms(String num) {
		// code....
		// ....
	}
}
