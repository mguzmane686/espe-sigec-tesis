package org.espe.sigec.web.utils.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.espe.sigec.validacion.CedulaValidacion;
import org.espe.sigec.web.utils.PortalResourceBundle;

public class CedulaValidator implements Validator{

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		String mensaje;
		try {
			if(!CedulaValidacion.getInstancia().validarCedula(String.valueOf(arg2))){
				mensaje = PortalResourceBundle.getString("validador_mensaje_cedula",new String[]{String.valueOf(arg2)});
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, mensaje);
				throw new ValidatorException(facesMessage);
			}
			
		} catch (Exception e) {
			mensaje = PortalResourceBundle.getString("validador_mensaje_cedula",new String[]{String.valueOf(arg2)});
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, mensaje);
			throw new ValidatorException(facesMessage);
		} 
	}

}