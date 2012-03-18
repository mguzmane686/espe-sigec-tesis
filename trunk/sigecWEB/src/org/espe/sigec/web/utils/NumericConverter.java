package org.espe.sigec.web.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author Roberto
 *
 */
@FacesConverter("org.espe.sigec.numberConverter")
public class NumericConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return arg2;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		
		String valorRetorno = String.valueOf(arg2);
		
		BigDecimal valor = null;
		if(arg2!= null && !arg2.equals("")){
			valor = new BigDecimal(String.valueOf(valorRetorno));
			valorRetorno = String.valueOf(valor.setScale(2, RoundingMode.HALF_UP)).concat(" $");
		}
		
		return valorRetorno;
	}

}
