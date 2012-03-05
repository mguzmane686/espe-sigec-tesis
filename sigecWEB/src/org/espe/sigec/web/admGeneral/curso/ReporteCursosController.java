package org.espe.sigec.web.admGeneral.curso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Curso;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.web.reportes.ReporteGenerico;

@SuppressWarnings("serial")
@ManagedBean(name = "reporteCursosController")
@ViewScoped
public class ReporteCursosController implements Serializable{

	@Inject
	private AdmGeneralServicio admGeneralServicio;
	
	private Collection<Curso> lstCursos;
	
	public ReporteCursosController(){
		setLstCursos(new ArrayList<Curso>());
	}
	
	@PostConstruct
	public void cargarCursosExistentes(){
		setLstCursos(admGeneralServicio.cargarCursos());
	}

	public Collection<Curso> getLstCursos() {
		return lstCursos;
	}

	public void setLstCursos(Collection<Curso> lstCursos) {
		this.lstCursos = lstCursos;
	}
	
	public void btnPDF(ActionEvent e){
		ReporteGenerico reporteGenerico = new ReporteGenerico();
		reporteGenerico .generarReporteSimple("cursos_existentes", getLstCursos());
	}
	
}
