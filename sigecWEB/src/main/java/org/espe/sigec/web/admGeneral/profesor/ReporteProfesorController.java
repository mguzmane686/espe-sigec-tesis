package org.espe.sigec.web.admGeneral.profesor;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Profesor;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.web.reportes.ReporteGenerico;

@SuppressWarnings("serial")
@ManagedBean(name="reporteProfesoresController")
@ViewScoped
public class ReporteProfesorController implements Serializable {

	@Inject
	private AdmGeneralServicio admGeneralServicio;
	
	private Collection<Profesor> lstProfesores;
	
	public ReporteProfesorController(){
		
	}
	
	@PostConstruct
	public void cargarContactos(){
		setLstProfesores(admGeneralServicio.cargarProfesores());
	}

	public Collection<Profesor> getLstProfesores() {
		return lstProfesores;
	}
	public void setLstProfesores(Collection<Profesor> lstProfesores) {
		this.lstProfesores = lstProfesores;
	}
	
	public void btnPDF(ActionEvent e){
		ReporteGenerico reporteGenerico = new ReporteGenerico();
		reporteGenerico.generarReporteSimple("profesores",getLstProfesores());
	}

}
