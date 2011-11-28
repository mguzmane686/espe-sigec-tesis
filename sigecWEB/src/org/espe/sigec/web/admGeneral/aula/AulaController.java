package org.espe.sigec.web.admGeneral.aula;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Aula;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.web.utils.FacesUtils;

/**
 * @author roberto
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name="aulaController")
@ViewScoped
public class AulaController implements Serializable{
	@Inject
	private AdmGeneralServicio admGeneralServicio;
	
	private Aula aula;
	
	
	public AulaController() {
		setAula(new Aula());
	}
	
	public void btnSaveAula(ActionEvent e){
		try {
			admGeneralServicio.createAula(getAula());
			setAula(new Aula());
			FacesUtils.addInfoMessage("El aula se cre&oacute correctamante");
		} catch (Exception e1) {
			FacesUtils.addErrorMessage("Ha ocurrido un error al gurdadr el aula");
		}
	}
	
	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}
}
