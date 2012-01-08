package org.espe.sigec.web.admGeneral.localidad;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.espe.sigec.model.entities.LugarCurso;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.web.utils.FacesUtils;

/**
 * @author Diego
 *
 */

@SuppressWarnings("serial")
@ManagedBean(name="reporteLocalidadController")
@ViewScoped
public class ReporteLocalidadController implements Serializable {

	@Inject
	private AdmGeneralServicio admGeneralServicio;
	
	private Collection<LugarCurso> lstLocalidades;
	
	public ReporteLocalidadController(){
			setLstLocalidades(new ArrayList<LugarCurso>());
	}
	
	@PostConstruct
	public void loadLocalidades(){
		setLstLocalidades(admGeneralServicio.findLugar());
		
	}

	public Collection<LugarCurso> getLstLocalidades() {
		return lstLocalidades;
	}

	public void setLstLocalidades(Collection<LugarCurso> lstLocalidades) {
		this.lstLocalidades = lstLocalidades;
	}
	
	public void btnShowLocalidadDetail(LugarCurso localidad){
		FacesUtils.putFlashObject("edificioToEdit",localidad);
		try {
			FacesUtils.redirectPage("adm_edicion_localidad.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}	
}
