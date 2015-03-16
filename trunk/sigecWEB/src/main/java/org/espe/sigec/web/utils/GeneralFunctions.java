package org.espe.sigec.web.utils;

public class GeneralFunctions {
	
	public String completarCeros(int nextRegistro,int cantidadNumeros){
			
		String sufijo = new String();
			
		if (cantidadNumeros == 1) 
			sufijo = "000" + Integer.toString(nextRegistro);
			else if (cantidadNumeros == 2)
				sufijo = "00" + Integer.toString(nextRegistro);
			else if (cantidadNumeros == 3)
				sufijo = "0" + Integer.toString(nextRegistro);
			else if (cantidadNumeros == 4)
				sufijo = Integer.toString(nextRegistro);
		
		return sufijo;
	}
		
}
