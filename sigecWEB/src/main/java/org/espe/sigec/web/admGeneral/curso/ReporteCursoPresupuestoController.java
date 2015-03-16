package org.espe.sigec.web.admGeneral.curso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.PresupuestoCurso;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.web.reportes.ReporteGenerico;

@SuppressWarnings("serial")
@ManagedBean(name = "reporteCursoPresupuestoController")
@ViewScoped
public class ReporteCursoPresupuestoController implements Serializable{

	Integer anio;
	
	@Inject
	AdmGeneralServicio admGeneralServicio;
	
	Collection<PresupuestoCurso> lstCursos;
	
	public ReporteCursoPresupuestoController(){	
		setLstCursos(new ArrayList<PresupuestoCurso>());
		setAnio(new Integer(0));
	}
	
	public Collection<PresupuestoCurso> getLstCursos() {
		return lstCursos;
	}

	public void setLstCursos(Collection<PresupuestoCurso> lstCursos) {
		this.lstCursos = lstCursos;
	}
	
	public void btnCargarInforme(ActionEvent e){
		
		Collection<PresupuestoCurso> lstAux;
		
		lstAux = admGeneralServicio.cargarCursoPresupuesto(String.valueOf(getAnio())); 
		
		if (lstAux.size()>0) {
			setLstCursos(lstAux);
		}
		else{
			setLstCursos(new ArrayList<PresupuestoCurso>());
		}
			
	}

	public void btnPDF(ActionEvent e){
		ReporteGenerico reporteGenerico = new ReporteGenerico();
		reporteGenerico.generarReporteSimple("cursos_presupuestos", getLstCursos());
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	@SuppressWarnings("deprecation")
	@PostConstruct
	public void iniciarAnio(){
		Date fecha = new Date();
		setAnio(fecha.getYear()+1900);
	}	
	
}
