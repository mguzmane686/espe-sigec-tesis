package org.espe.sigec.web.coordinacion.curso;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.CursoEstudiante;
import org.espe.sigec.model.entities.Programa;
import org.espe.sigec.model.entities.ProgramaCurso;
import org.espe.sigec.servicio.coordinacion.CoordinacionServicio;
import org.espe.sigec.servicio.planificacion.PlanificacionServicio;

/**
 * @author roberto
 *
 */
@ManagedBean(name = "inscritosCursoController")
@ViewScoped
public class InscritosCursoController {
	@Inject
	private PlanificacionServicio planificacionServicio;
	@Inject
	private CoordinacionServicio coordinacionServicio;
	
	private Programa programa;
	private Collection<Programa> lstProgramas;
	private Collection<ProgramaCurso> lstProgramaCursos;
	private Collection<CursoEstudiante> lstInscritos;
	private CursoEstudiante estudianteInscrito;
	public InscritosCursoController() {
		super();
	}
	
	@PostConstruct
	private void init(){
		setPrograma(new Programa());
		setLstProgramas(planificacionServicio.buscarPrograma());
		setLstProgramaCursos(new ArrayList<ProgramaCurso>());
		setEstudianteInscrito(new CursoEstudiante());
	}
	
	public void rsCargarCursosPrograma(ValueChangeEvent valueChangeEvent){
		getPrograma().setIdPrograma(Integer.parseInt(String.valueOf(valueChangeEvent.getNewValue())));
		setLstProgramaCursos(planificacionServicio.buscarCursosAsignadosPrograma(getPrograma()));
	}
	
	public void btnCargarInscritos(ProgramaCurso programaCursoSelected){
		setLstInscritos(coordinacionServicio.estudiantesInscritosCurso(programaCursoSelected.getCursoPeriodo().getIdCursoPeriodo()));
		
	}
	
	public void btnCambiarEstadoPago(CursoEstudiante cursoEstudiante){
		setEstudianteInscrito(cursoEstudiante);
	}
	
	public void btnActualizarEstadoPago(){
		try {
			coordinacionServicio.actualizarCursoEstudiante(getEstudianteInscrito());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Collection<Programa> getLstProgramas() {
		return lstProgramas;
	}

	public void setLstProgramas(Collection<Programa> lstProgramas) {
		this.lstProgramas = lstProgramas;
	}

	public Collection<ProgramaCurso> getLstProgramaCursos() {
		return lstProgramaCursos;
	}

	public void setLstProgramaCursos(Collection<ProgramaCurso> lstProgramaCursos) {
		this.lstProgramaCursos = lstProgramaCursos;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public Collection<CursoEstudiante> getLstInscritos() {
		return lstInscritos;
	}

	public void setLstInscritos(Collection<CursoEstudiante> lstInscritos) {
		this.lstInscritos = lstInscritos;
	}

	public CursoEstudiante getEstudianteInscrito() {
		return estudianteInscrito;
	}

	public void setEstudianteInscrito(CursoEstudiante estudianteInscrito) {
		this.estudianteInscrito = estudianteInscrito;
	}
	

}
