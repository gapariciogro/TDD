package com.edu.chapter02;

/**
 * Nombre de persona
 * @author gaparicio
 *
 */
public class PersonName {
	private String prefix;
	private String firstName;
	private String middleName;
	private String lastName;

	/**
	 * Arma el nombre de la persona
	 * @param firstName Primer nombre
	 * @param middleName Segun Nombre
	 * @param lastName Apellido
	 * @return Nombre concatenado
	 */
	public String getFormattedName() {
		StringBuilder personName = new StringBuilder();
		
		if (this.prefix != null) {
			personName.append(this.prefix + " ");
		}
		
		if (this.firstName != null) {
			personName.append(this.firstName);
		}
		
		if (this.middleName != null) {
			personName.append(" " + this.middleName);
		}
		
		if (this.lastName != null) {
			personName.append(" " + this.lastName);
		}
		return personName.toString();
	}

	
	
	/**
	 * Obtiene el valor de prefix.
	 * @return Valor de prefix
	 */
	public String getPrefix() {
		return prefix;
	}
	/**
	 * Establece el valor de prefix
	 * @param prefix Valor a establecer de prefix
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	/**
	 * Obtiene el valor de firstName.
	 * @return Valor de firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Establece el valor de firstName
	 * @param firstName Valor a establecer de firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * Obtiene el valor de middleName.
	 * @return Valor de middleName
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * Establece el valor de middleName
	 * @param middleName Valor a establecer de middleName
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	/**
	 * Obtiene el valor de lastName.
	 * @return Valor de lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * Establece el valor de lastName
	 * @param lastName Valor a establecer de lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
