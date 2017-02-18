package com.edu.chapter07;

/**
 * @author jshernandez
 *
 */
public interface MembershipDAO {
	/**
	 * Obtiene el estatus de la membresia del desarrolador.
	 * @param developerId Clave del desarrollador
	 * @return Objeto con la información de la membresia.
	 */
	MembershipStatusDto getStatusFor(String developerId);
}
