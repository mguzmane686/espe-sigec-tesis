package org.espe.sigec.web.admGeneral.edificio;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import org.espe.sigec.model.entities.Edificio;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.web.utils.FacesUtils;

/**
 * @author Diego
 *
 */

@ManagedBean(name ="edicionEdificioController")
@ViewScoped
public class EdicionEdificioController {

	private Edificio edificio;
	
	@Inject
	private AdmGeneralServicio admGeneralServicio;
	
	public EdicionEdificioController(){
		setEdificio((Edificio) FacesUtils.getFlashObject("edificioToEdit"));
		initEntities();
	}
	
	public void btnSaveEditEdificio(ActionEvent e){
		try {
			admGeneralServicio.editEdificio(getEdificio());
			FacesUtils.addInfoMessage("El edificio se edito con &eacutexito");
		} catch (Exception e1) {
			FacesUtils.addErrorMessage("El edificio no pudo editarse");
		}
	}
	
	public void btnAtras(ActionEvent e){
		try {
			FacesUtils.redirectPage("reporte_edificios.jsf");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void initEntities(){
		setEdificio(new Edificio());
	}
	
	public Edificio getEdificio() {
		return edificio;
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}
}
