package org.espe.sigec.web.admGeneral.programa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Programa;
import org.espe.sigec.model.entities.ProgramaCurso;
import org.espe.sigec.servicio.planificacion.PlanificacionServicio;
import org.espe.sigec.web.utils.FacesUtils;

@SuppressWarnings("serial")
@ManagedBean(name="administrarProgramaController")
@ViewScoped
public class AdministrarProgramaController implements Serializable{
	@Inject
	private PlanificacionServicio planificacionServicio;
	private Programa programa;
	Collection<ProgramaCurso> lstProgramaCursos;
	private boolean editMode;
	
	public AdministrarProgramaController() {
		setPrograma((Programa) FacesUtils.getFlashObject("programa"));
		if(getPrograma() ==null){
			setPrograma(new Programa());
		}
		setLstProgramaCursos(new ArrayList<ProgramaCurso>());
	}
	
	public void btnSavePrograma(){
		try {
			if(isEditMode()){
				
			}else{
				planificacionServicio.crearPrograma(getPrograma());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public Collection<ProgramaCurso> getLstProgramaCursos() {
		return lstProgramaCursos;
	}

	public void setLstProgramaCursos(Collection<ProgramaCurso> lstProgramaCursos) {
		this.lstProgramaCursos = lstProgramaCursos;
	}	
	
}
