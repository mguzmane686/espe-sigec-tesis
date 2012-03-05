package org.espe.sigec.web.admGeneral.programa;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Programa;
import org.espe.sigec.servicio.planificacion.PlanificacionServicio;

@SuppressWarnings("serial")
@ManagedBean(name="administrarProgramaController")
@ViewScoped
public class AdministrarProgramaController implements Serializable{
	@Inject
	private PlanificacionServicio planificacionServicio;
	private Programa programa;
	private boolean editMode;
	
	public AdministrarProgramaController() {
		setPrograma(new Programa());
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
	
}
