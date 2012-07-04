package org.espe.sigec.web.utils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.espe.sigec.validacion.CedulaValidacion;
@FacesValidator("cedulaValidator")
public class CedulaValidator implements Validator{

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		try {
			if(!CedulaValidacion.getInstancia().validarCedula(String.valueOf(arg2))){
				throw new Exception("no pasa la validacion de la cedula");
			}
			System.out.println("Pasa la validacion");
		} catch (Exception e) {
			String mensaje = "La cédula "+ arg2+ " no es válida";
			throw new ValidatorException(new FacesMessage(mensaje));
		} 
	}

}
