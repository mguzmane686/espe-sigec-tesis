package org.espe.sigec.web.programa.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.Programa;
import org.espe.sigec.model.entities.ProgramaCurso;
import org.espe.sigec.servicio.portal.PortalServicio;
import org.espe.sigec.web.inscripcion.InscripcionController;
import org.espe.sigec.web.utils.FacesUtils;

/**
 * @author Roberto
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name="programasController")
@ViewScoped
public class ProgramasController implements Serializable{
	@Inject 
	private PortalServicio portalServicio;
	private Programa programaCursoSelected;
	private CursoPeriodo cursoPeriodoSeleccionado;
	private ProgramaCurso programaCursoSeleccionado;
	
	private Collection<Programa> lstProgramaCursos;
	
	public ProgramasController() {
		setLstProgramaCursos(new ArrayList<Programa>());
		programaCursoSelected = new Programa();
	}
	
	@PostConstruct
	public void cargarPrograma(){
		setLstProgramaCursos(portalServicio.buscarProgramaActivo());
	}
	
	public void btnAceptar(){
//		System.out.println(getCursoPeriodoSeleccionado().getModuloCursoPeriodoCollection());
		((InscripcionController)FacesUtils.getManagedBean("inscripcionController")).btnNewEstudent();
		((InscripcionController)FacesUtils.getManagedBean("inscripcionController")).setFindMode(Boolean.TRUE);
		((InscripcionController)FacesUtils.getManagedBean("inscripcionController")).setCedulaUsr(StringUtils.EMPTY);
	}
	public void btnShowProgramaDetalle(){
		System.out.println("Uno");
	}
	
	public Collection<Programa> getLstProgramaCursos() {
		return lstProgramaCursos;
	}

	public void setLstProgramaCursos(Collection<Programa> lstProgramaCursos) {
		this.lstProgramaCursos = lstProgramaCursos;
	}

	public Programa getProgramaCursoSelected() {
		return programaCursoSelected;
	}

	public void setProgramaCursoSelected(Programa programaCursoSelected) {
		this.programaCursoSelected = programaCursoSelected;
	}

	public CursoPeriodo getCursoPeriodoSeleccionado() {
		return cursoPeriodoSeleccionado;
	}

	public void setCursoPeriodoSeleccionado(CursoPeriodo cursoPeriodoSeleccionado) {
		this.cursoPeriodoSeleccionado = cursoPeriodoSeleccionado;
	}

	public ProgramaCurso getProgramaCursoSeleccionado() {
		return programaCursoSeleccionado;
	}

	public void setProgramaCursoSeleccionado(ProgramaCurso programaCursoSeleccionado) {
		this.programaCursoSeleccionado = programaCursoSeleccionado;
	}
	
	
}
