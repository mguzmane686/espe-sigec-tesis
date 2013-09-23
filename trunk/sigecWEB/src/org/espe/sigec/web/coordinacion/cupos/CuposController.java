package org.espe.sigec.web.coordinacion.cupos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.espe.sigec.model.entities.CursoEstudiante;
import org.espe.sigec.model.entities.Programa;
import org.espe.sigec.model.entities.ProgramaCurso;
import org.espe.sigec.servicio.coordinacion.CoordinacionServicio;
import org.espe.sigec.servicio.planificacion.PlanificacionServicio;

/**
 * @author roberto
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name ="cuposController")
@ViewScoped
public class CuposController implements Serializable{
	@Inject
	private PlanificacionServicio planificacionServicio;
	@Inject
	private CoordinacionServicio coordinacionServicio;
	
	private Programa programa;
	private Collection<Programa> lstProgramas;
	private Collection<ProgramaCurso> lstProgramaCursos;
	private ProgramaCurso programaCursoSeleccionado;
	
	private Collection<CursoEstudiante> lstInscritos;
	private CursoEstudiante estudianteInscrito;
	
	private Integer cuposRestantes;
	
	public CuposController() {
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
		setProgramaCursoSeleccionado(null);
	}
	
	public void btnCargarInscritos(ProgramaCurso programaCursoSelected){
		setLstInscritos(coordinacionServicio.estudiantesInscritosCurso(programaCursoSelected.getCursoPeriodo().getIdCursoPeriodo(), "CUPO-VIGENTE"));
		setProgramaCursoSeleccionado(SerializationUtils.clone(programaCursoSelected));
		
		try {
			
			setCuposRestantes(getProgramaCursoSeleccionado().getCursoPeriodo().getMaximoEstudiantes() - CollectionUtils.size(getLstInscritos()));
		} catch (Exception e) {
			setCuposRestantes(null);
		}
		
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

	public ProgramaCurso getProgramaCursoSeleccionado() {
		return programaCursoSeleccionado;
	}

	public void setProgramaCursoSeleccionado(ProgramaCurso programaCursoSeleccionado) {
		this.programaCursoSeleccionado = programaCursoSeleccionado;
	}

	public Integer getCuposRestantes() {
		return cuposRestantes;
	}

	public void setCuposRestantes(Integer cuposRestantes) {
		this.cuposRestantes = cuposRestantes;
	}
	
}
