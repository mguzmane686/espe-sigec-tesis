package org.espe.sigec.web.planificacion.curso.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Curso;
import org.espe.sigec.servicio.curso.CursoServicio;

/**
 * @author Roberto
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name="reporteCursoController")
@ViewScoped
public class ReporteCursoController implements Serializable{
	@Inject
	private CursoServicio cursoServicio;
	
	private Collection<Curso> lstCursos;

	public ReporteCursoController() {
		lstCursos = new ArrayList<Curso>();
	}

	@PostConstruct
	public void loadCursos(){
		setLstCursos(cursoServicio.findCursos());
	}
	public Collection<Curso> getLstCursos() {
		return lstCursos;
	}

	public void setLstCursos(Collection<Curso> lstCursos) {
		this.lstCursos = lstCursos;
	}
	
	
}
