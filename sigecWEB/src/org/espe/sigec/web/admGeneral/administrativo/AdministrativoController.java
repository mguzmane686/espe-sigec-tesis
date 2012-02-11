package org.espe.sigec.web.admGeneral.administrativo;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.espe.sigec.exception.UserValidateException;
import org.espe.sigec.model.entities.Persona;
import org.espe.sigec.model.entities.Usuario;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.web.utils.FacesUtils;
import org.espe.sigec.web.utils.SigecResourceBundle;
@SuppressWarnings("serial")
@ManagedBean(name="administrativoController")
@ViewScoped
public class AdministrativoController implements Serializable{
	@Inject
	private AdmGeneralServicio admGeneralServicio;
	
	private Persona persona;
	
	public AdministrativoController() {
		initEntities();
	}
	private void initEntities(){
		setPersona(new Persona());
		getPersona().setUsuario(new Usuario());
	}
	
	public void btnSaveAdministrativo(){
		try {
			admGeneralServicio.createAdministrativo(getPersona().getUsuario(), getPersona());
			FacesUtils.addInfoMessage(SigecResourceBundle.getString("funcionario_creacion_exito", new String[]{getPersona().getPrimerNombre()+" "+getPersona().getPrimerApellido()}));
			initEntities();
		} catch (UserValidateException e2){
			FacesUtils.addInfoMessage(e2.getMessage());
		} catch (Exception e) {
			FacesUtils.addErrorMessage("Administrativo no creado");
		}
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
}
