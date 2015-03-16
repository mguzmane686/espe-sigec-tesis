package org.espe.sigec.web.utils;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author roberto
 *
 */
public class SigecResourceBundle {
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("org.espe.sigec.web.utils.sigec_msg");
	
	/**
	 * Permite la obtencion del valor de la clave del archivo de propiedades general
	 * @param  key							- Clave del archivo de propiedades que se desea obtener
	 * @return								- Valor de la clave ingresadaS
	 * @throws MissingResourceException
	 */
	public static String getString(String key) throws MissingResourceException{
		return RESOURCE_BUNDLE.getString(key);
	}
	
	/**
	 * Permite la obtención del valor de la clave del archivo de propiedades general con la asignacion de los paraametros
	 * @param  key							- Clave del archivo de propiedades que se desea obtener
	 * @param  parameters					- Parametros del mensaje
	 * @return								- Valor de la clave ingresada
	 * @throws MissingResourceException
	 */
	public static String getString(String key, Object[] parameters) throws MissingResourceException{
		return MessageFormat.format(RESOURCE_BUNDLE.getString(key), parameters);
	}
	
	/**
	 * Permite la obtencion del valor de la clave del archivo de propiedades general en formato <code>Integer</code>
	 * @param  key							- Clave del archivo de propiedades que se desea obtener
	 * @param  parameters					- Parametros del mensaje
	 * @return								- Valor de la clave ingresada
	 * @throws MissingResourceException
	 */
	public static Integer getInteger(String key) throws MissingResourceException{
		return Integer.valueOf(RESOURCE_BUNDLE.getString(key));
	}
}
