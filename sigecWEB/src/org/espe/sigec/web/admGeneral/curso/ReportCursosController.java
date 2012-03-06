package org.espe.sigec.web.admGeneral.curso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.web.reportes.ReporteGenerico;

@SuppressWarnings("serial")
@ManagedBean(name = "reportCursosController")
@ViewScoped
public class ReportCursosController implements Serializable {

	Date fechaInicio;
	Date fechaFin;
	String estado;
	
	@Inject
	private AdmGeneralServicio admGeneralServicio;
	
	Collection<CursoPeriodo> lstCursos;
	public Collection<SelectItem> itemsEstado;
	
	public ReportCursosController(){
		setLstCursos(new ArrayList<CursoPeriodo>());
		setEstado(new String());
		setFechaInicio(new Date());
		setFechaFin(new Date());
	}

	@PostConstruct
	public void cargarEstados(){
		setItemsEstado(new ArrayList<SelectItem>());
		getItemsEstado().add(new SelectItem(null, "TODOS"));
		getItemsEstado().add(new SelectItem("A", "ABIERTO"));
		getItemsEstado().add(new SelectItem("E", "EJECUCION"));
		getItemsEstado().add(new SelectItem("F", "FINALIZADO"));
	}
	
	public Collection<CursoPeriodo> getLstCursos() {
		return lstCursos;
	}

	public void setLstCursos(Collection<CursoPeriodo> lstCursos) {
		this.lstCursos = lstCursos;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
		
	public Collection<SelectItem> getItemsEstado() {
		return itemsEstado;
	}

	public void setItemsEstado(Collection<SelectItem> itemsEstado) {
		this.itemsEstado = itemsEstado;
	}

	public void btnCargar(ActionEvent e){
		Collection<CursoPeriodo> lstAuxCursos;
		
		lstAuxCursos = admGeneralServicio.cargarCursosParametros(getFechaInicio(),getFechaFin(),getEstado()); 
		
		if (lstAuxCursos.size()>0){
			setLstCursos(lstAuxCursos);	
		}
		else{
			setLstCursos(new ArrayList<CursoPeriodo>());
		}
	}
	
	public void btnPDF(ActionEvent e){
		ReporteGenerico reporteGenerico = new ReporteGenerico();
		reporteGenerico .generarReporteSimple("cursos", getLstCursos());
	}
	
}
