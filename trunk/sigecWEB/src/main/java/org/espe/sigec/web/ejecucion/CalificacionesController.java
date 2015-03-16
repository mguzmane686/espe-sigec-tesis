package org.espe.sigec.web.ejecucion;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.SerializationUtils;
import org.espe.sigec.model.entities.CursoEstudiante;
import org.espe.sigec.model.entities.Programa;
import org.espe.sigec.model.entities.ProgramaCurso;
import org.espe.sigec.servicio.coordinacion.CoordinacionServicio;
import org.espe.sigec.servicio.ejecucion.EjecucionServicio;
import org.espe.sigec.servicio.planificacion.PlanificacionServicio;
import org.espe.sigec.web.utils.FacesUtils;

/**
 * @author roberto
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name = "calificacionesController")
@ViewScoped
public class CalificacionesController implements Serializable{

	@Inject
	private EjecucionServicio ejecucionServicio;
	@Inject
	private PlanificacionServicio planificacionServicio;
	
	@Inject
	private CoordinacionServicio coordinacionServicio;
	
	private Collection<Programa> lstProgramas;
	private Programa programa;
	private Collection<ProgramaCurso> lstProgramaCursos;
	private ProgramaCurso programaCursoSeleccionado;
	private Collection<CursoEstudiante> lstInscritos;
	
	public CalificacionesController() {
		super();
		
	}

	@PostConstruct
	private void init(){
		setPrograma(new Programa());
		setLstProgramas(planificacionServicio.buscarPrograma());
	}

	public void rsCargarCursosPrograma(ValueChangeEvent valueChangeEvent){
		getPrograma().setIdPrograma(Integer.parseInt(String.valueOf(valueChangeEvent.getNewValue())));
		setLstProgramaCursos(planificacionServicio.buscarCursosAsignadosPrograma(getPrograma()));
		setProgramaCursoSeleccionado(null);
	}
	
	public void btnCargarInscritos(ProgramaCurso programaCursoSelected){
		
		for(ProgramaCurso programaCurso: lstProgramaCursos){
			programaCurso.setSelected(Boolean.FALSE);
		}
		
		programaCursoSelected.setSelected(Boolean.TRUE);
		
		setLstInscritos(coordinacionServicio.estudiantesInscritosCurso(programaCursoSelected.getCursoPeriodo().getIdCursoPeriodo(), "CUPO-VIGENTE", "PAGADO"));
		setProgramaCursoSeleccionado(SerializationUtils.clone(programaCursoSelected));
		
	}
	
	public void btnGuardarAsistencias(){
		try {
			Predicate predicate = new Predicate() {
				
				@Override
				public boolean evaluate(Object arg0) {
					ProgramaCurso programaCurso = (ProgramaCurso) arg0;
					if(programaCurso.equals(getProgramaCursoSeleccionado())){
						return true;
					}
					return false;
				}
			};
			ProgramaCurso programaCurso = (ProgramaCurso) CollectionUtils.find(getLstProgramaCursos(), predicate);
			ejecucionServicio.actualizarAsistenciasEstudianteCurso(programaCurso.getCursoPeriodo(), getLstInscritos());
			setProgramaCursoSeleccionado(null);
			FacesUtils.addInfoMessage("Se actualizaron las calificaciones");
		} catch (Exception e) {
			FacesUtils.addErrorMessage("Ocurrio un error al actualizar las asistencias al curso");
			e.printStackTrace();
		}
	}
	
	public Collection<Programa> getLstProgramas() {
		return lstProgramas;
	}

	public void setLstProgramas(Collection<Programa> lstProgramas) {
		this.lstProgramas = lstProgramas;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public Collection<ProgramaCurso> getLstProgramaCursos() {
		return lstProgramaCursos;
	}

	public void setLstProgramaCursos(Collection<ProgramaCurso> lstProgramaCursos) {
		this.lstProgramaCursos = lstProgramaCursos;
	}

	public ProgramaCurso getProgramaCursoSeleccionado() {
		return programaCursoSeleccionado;
	}

	public void setProgramaCursoSeleccionado(ProgramaCurso programaCursoSeleccionado) {
		this.programaCursoSeleccionado = programaCursoSeleccionado;
	}

	public Collection<CursoEstudiante> getLstInscritos() {
		return lstInscritos;
	}

	public void setLstInscritos(Collection<CursoEstudiante> lstInscritos) {
		this.lstInscritos = lstInscritos;
	}
}
