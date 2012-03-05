package org.espe.sigec.web.programa.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.espe.sigec.model.entities.ProgramaCurso;
import org.espe.sigec.servicio.portal.PortalServicio;

@SuppressWarnings("serial")
@ManagedBean(name="programasController")
@ViewScoped
public class ProgramasController implements Serializable{
	@Inject 
	private PortalServicio portalServicio;
	private ProgramaCurso programaCursoSelected;
	
	private Collection<ProgramaCurso> lstProgramaCursos;
	
	public ProgramasController() {
		setLstProgramaCursos(new ArrayList<ProgramaCurso>());
		programaCursoSelected = new ProgramaCurso();
	}

	@PostConstruct
	public void cargarPrograma(){
		setLstProgramaCursos(portalServicio.buscarPrograma());
	}
	
	public void btnShowProgramaDetalle(){
		System.out.println("Uno");
	}
	
	public Collection<ProgramaCurso> getLstProgramaCursos() {
		return lstProgramaCursos;
	}

	public void setLstProgramaCursos(Collection<ProgramaCurso> lstProgramaCursos) {
		this.lstProgramaCursos = lstProgramaCursos;
	}

	public ProgramaCurso getProgramaCursoSelected() {
		return programaCursoSelected;
	}

	public void setProgramaCursoSelected(ProgramaCurso programaCursoSelected) {
		this.programaCursoSelected = programaCursoSelected;
	}
	
}