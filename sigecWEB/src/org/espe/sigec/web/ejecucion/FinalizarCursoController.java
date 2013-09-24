package org.espe.sigec.web.ejecucion;

import java.io.Serializable;
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
import org.espe.sigec.servicio.ejecucion.EjecucionServicio;
import org.espe.sigec.servicio.planificacion.PlanificacionServicio;
import org.espe.sigec.web.utils.FacesUtils;

/**
 * @author roberto
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name ="finalizarCursoController")
@ViewScoped
public class FinalizarCursoController implements Serializable{
	@Inject
	private PlanificacionServicio planificacionServicio;
	@Inject
	private EjecucionServicio ejecucionServicio;
	
	private Programa programa;
	private Collection<Programa> lstProgramas;
	private Collection<ProgramaCurso> lstProgramaCursos;
	private ProgramaCurso programaCursoSeleccionado;
	
	private Collection<CursoEstudiante> lstInscritos;
	
	
	@PostConstruct
	public void init(){
		setPrograma(new Programa());
		setLstProgramas(planificacionServicio.buscarPrograma());
		setLstProgramaCursos(new ArrayList<ProgramaCurso>());
	}
	
	public void rsCargarCursosPrograma(ValueChangeEvent valueChangeEvent){
		getPrograma().setIdPrograma(Integer.parseInt(String.valueOf(valueChangeEvent.getNewValue())));
		setLstProgramaCursos(planificacionServicio.buscarCursosAsignadosProgramaParaFinalizar(getPrograma().getIdPrograma()));
		setProgramaCursoSeleccionado(null);
	}

	public void btnAceptarFinalizarCurso(){
		try {
			getProgramaCursoSeleccionado().getCursoPeriodo().getHistoricoCursoEstadoCollection().setEtapaFinalizado("1");
			ejecucionServicio.finalizarCurso(getProgramaCursoSeleccionado().getCursoPeriodo().getHistoricoCursoEstadoCollection());
			FacesUtils.addInfoMessage("El curso fue finalizado");
		} catch (Exception e) {
			FacesUtils.addErrorMessage("Ocurrio un error al finalizar el curso");
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

	public Collection<CursoEstudiante> getLstInscritos() {
		return lstInscritos;
	}

	public void setLstInscritos(Collection<CursoEstudiante> lstInscritos) {
		this.lstInscritos = lstInscritos;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public ProgramaCurso getProgramaCursoSeleccionado() {
		return programaCursoSeleccionado;
	}

	public void setProgramaCursoSeleccionado(ProgramaCurso programaCursoSeleccionado) {
		this.programaCursoSeleccionado = programaCursoSeleccionado;
	}
	
}