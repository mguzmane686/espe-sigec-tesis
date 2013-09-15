package org.espe.sigec.web.coordinacion.logistica;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.MaterialDidactico;
import org.espe.sigec.model.entities.MaterialDidacticoCatalogo;
import org.espe.sigec.model.entities.Programa;
import org.espe.sigec.model.entities.ProgramaCurso;
import org.espe.sigec.servicio.coordinacion.CoordinacionServicio;
import org.espe.sigec.servicio.planificacion.PlanificacionServicio;

@SuppressWarnings("serial")
@ManagedBean(name="asignacionMaterialController")
@ViewScoped
public class AsignacionMaterialController implements Serializable{
	@Inject
	private PlanificacionServicio planificacionServicio;
	@Inject
	private CoordinacionServicio coordinacionServicio;
	
	private Programa programa;
	
	private Collection<Programa> lstProgramas;
	private Collection<ProgramaCurso> lstProgramaCursos;
	private ProgramaCurso programaCursoSeleccionado;
	
	private Collection<MaterialDidactico> lstMaterialDidacticos;
	private Collection<MaterialDidacticoCatalogo> lstMaterialDidacticoCatalogos;
	
	
	public AsignacionMaterialController() {
		setPrograma(new Programa());
	}
	
	@PostConstruct
	public void initController(){
		setLstProgramas(planificacionServicio.buscarPrograma());
	}


	public void rsCargarCursosPrograma(ValueChangeEvent valueChangeEvent){		
		getPrograma().setIdPrograma(Integer.parseInt(String.valueOf(valueChangeEvent.getNewValue())));
		setLstProgramaCursos(planificacionServicio.buscarCursosAsignadosPrograma(getPrograma()));
//		setProgramaCursoSeleccionado(null);
	}

	public Collection<ProgramaCurso> getLstProgramaCursos() {
		return lstProgramaCursos;
	}

	public void setLstProgramaCursos(Collection<ProgramaCurso> lstProgramaCursos) {
		this.lstProgramaCursos = lstProgramaCursos;
	}

	public Collection<Programa> getLstProgramas() {
		return lstProgramas;
	}
	
	public void setLstProgramas(Collection<Programa> lstProgramas) {
		this.lstProgramas = lstProgramas;
	}


	public Collection<MaterialDidactico> getLstMaterialDidacticos() {
		return lstMaterialDidacticos;
	}


	public void setLstMaterialDidacticos(
			Collection<MaterialDidactico> lstMaterialDidacticos) {
		this.lstMaterialDidacticos = lstMaterialDidacticos;
	}


	public Programa getPrograma() {
		return programa;
	}


	public void setPrograma(Programa programa) {
		this.programa = programa;
	}
	
	
}
