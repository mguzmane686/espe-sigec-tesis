package org.espe.sigec.web.admGeneral.contactos;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Persona;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.web.utils.FacesUtils;

/**
 * @author roberto
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name="admContactoController")
@ViewScoped
public class AdmContactoController implements Serializable{
	@Inject
	private AdmGeneralServicio admGeneralServicio;
	
	private Persona persona;

	public AdmContactoController() {
		setPersona(new Persona());
	}
	
	public void btnSaveContact(ActionEvent event){
		try {
			getPersona().setEsContacto("1");
			admGeneralServicio.crearContacto(getPersona());
			setPersona(new Persona());
			FacesUtils.addInfoMessage("Contacto creado correctamente");
			
		} catch (Exception e) {
			FacesUtils.addErrorMessage("Ocurio un error al crear el contacto");
			e.printStackTrace();
		}
	}
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
}
