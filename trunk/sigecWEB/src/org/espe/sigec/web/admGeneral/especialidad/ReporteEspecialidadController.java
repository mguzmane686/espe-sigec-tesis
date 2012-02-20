package org.espe.sigec.web.admGeneral.especialidad;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Especialidad;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.web.reportes.ReporteGenerico;
import org.espe.sigec.web.utils.FacesUtils;

@SuppressWarnings("serial")
@ManagedBean(name="reporteEspecialidadController")
@ViewScoped
public class ReporteEspecialidadController implements Serializable{

	@Inject
	private AdmGeneralServicio admGeneralServicio;
	
	private Collection<Especialidad> lstEspecialidad;
	
	public ReporteEspecialidadController(){
		setLstEspecialidad(new ArrayList<Especialidad>());
	}
	
	@PostConstruct
	public void loadEspecialidades(){
		setLstEspecialidad(admGeneralServicio.findEspecialidad());
	}

	public Collection<Especialidad> getLstEspecialidad() {
		return lstEspecialidad;
	}

	public void setLstEspecialidad(Collection<Especialidad> lstEspecialidad) {
		this.lstEspecialidad = lstEspecialidad;
	}
	
	public void btnShowEspecialidadDetail(Especialidad especialidad){
		FacesUtils.putFlashObject("especialidadToEdit",especialidad);
		try {
			FacesUtils.redirectPage("adm_administrar_especialidad.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}	
	
	public void btnPDF(ActionEvent e){
		ReporteGenerico reporteGenerico = new ReporteGenerico();
		reporteGenerico.generarReporteSimple("especialidades", getLstEspecialidad());
	}
	
}
