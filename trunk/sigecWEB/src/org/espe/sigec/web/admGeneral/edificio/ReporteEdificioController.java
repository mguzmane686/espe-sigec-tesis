package org.espe.sigec.web.admGeneral.edificio;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Edificio;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.web.utils.FacesUtils;

/**
 * @author Diego
 *
 */

@SuppressWarnings("serial")
@ManagedBean(name="reporteEdificioController")
@ViewScoped
public class ReporteEdificioController implements Serializable {

	@Inject
	private AdmGeneralServicio admGeneralServicio;
	
	private Collection<Edificio> lstEdificios;
	
	public ReporteEdificioController(){
		setLstEdificios(new ArrayList<Edificio>());
	}
	
	@PostConstruct
	public void loadEdificios(){
		setLstEdificios(admGeneralServicio.findEdificio());
	}

	public Collection<Edificio> getLstEdificios() {
		return lstEdificios;
	}

	public void setLstEdificios(Collection<Edificio> lstEdificios) {
		this.lstEdificios = lstEdificios;
	}
	
	public void btnShowEdificioDetail(Edificio edificio){
		FacesUtils.putFlashObject("edificioToEdit",edificio);
		try {
			FacesUtils.redirectPage("adm_administrar_edificio.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
