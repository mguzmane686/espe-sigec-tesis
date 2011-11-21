package org.espe.sigec.web.planificacion.curso.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.servicio.curso.CursoServicio;
@SuppressWarnings("serial")
@ManagedBean(name="reporteCursoAbiertoController")
public class ReporteCursoAbiertoController implements Serializable{

	@Inject
	private CursoServicio cursoServicio;
	
	private Collection<CursoPeriodo> lstCursoPeriodos;
	
	public ReporteCursoAbiertoController() {
		setLstCursoPeriodos(new ArrayList<CursoPeriodo>());
	}
	
	@PostConstruct
	public void loadCursoAbierto(){
		setLstCursoPeriodos(cursoServicio.findCursoAbierto());
	}
	
	public Collection<CursoPeriodo> getLstCursoPeriodos() {
		return lstCursoPeriodos;
	}
	public void setLstCursoPeriodos(Collection<CursoPeriodo> lstCursoPeriodos) {
		this.lstCursoPeriodos = lstCursoPeriodos;
	}
	
}
