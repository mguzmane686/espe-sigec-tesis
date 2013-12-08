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
import org.espe.sigec.model.entities.HorarioCursoPeriodo;
import org.espe.sigec.model.entities.PensumAcademico;
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
	
	private Collection<PensumAcademico> lstPensumAcademicos;
	
	private Collection<Programa> lstProgramaCursos;
	
	private HorarioCursoPeriodo horarioCursoPeriodo;
	
	public ProgramasController() {
		setLstProgramaCursos(new ArrayList<Programa>());
		programaCursoSelected = new Programa();
		horarioCursoPeriodo = new HorarioCursoPeriodo();
	}
	
	@PostConstruct
	public void cargarPrograma(){
		setLstProgramaCursos(portalServicio.buscarProgramaActivo());
	}
	
	public void btnAceptar(){
		((InscripcionController)FacesUtils.getManagedBean("inscripcionController")).btnNewEstudent();
		((InscripcionController)FacesUtils.getManagedBean("inscripcionController")).setFindMode(Boolean.TRUE);
		((InscripcionController)FacesUtils.getManagedBean("inscripcionController")).setCedulaUsr(StringUtils.EMPTY);
		try {
			setLstPensumAcademicos(portalServicio.buscarPensumCurso(cursoPeriodoSeleccionado.getCurso().getIdCurso()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			setHorarioCursoPeriodo(portalServicio.buscarHorarioCurso(cursoPeriodoSeleccionado.getIdCursoPeriodo()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public Collection<PensumAcademico> getLstPensumAcademicos() {
		return lstPensumAcademicos;
	}

	public void setLstPensumAcademicos(
			Collection<PensumAcademico> lstPensumAcademicos) {
		this.lstPensumAcademicos = lstPensumAcademicos;
	}

	public HorarioCursoPeriodo getHorarioCursoPeriodo() {
		return horarioCursoPeriodo;
	}

	public void setHorarioCursoPeriodo(HorarioCursoPeriodo horarioCursoPeriodo) {
		this.horarioCursoPeriodo = horarioCursoPeriodo;
	}	
	
}
