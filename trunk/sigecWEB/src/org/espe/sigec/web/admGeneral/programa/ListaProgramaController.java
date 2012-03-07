package org.espe.sigec.web.admGeneral.programa;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Programa;
import org.espe.sigec.servicio.planificacion.PlanificacionServicio;
import org.espe.sigec.web.utils.FacesUtils;

@SuppressWarnings("serial")
@ManagedBean(name="listaProgramaController")
@ViewScoped
public class ListaProgramaController implements Serializable{
	@Inject
	private PlanificacionServicio planificacionServicio;
	private Programa programaSelected;
	
	private Collection<Programa> lstProgramas;
	
	public ListaProgramaController() {
		
	}
	@PostConstruct
	public void cargarProgramas(){
		setLstProgramas(planificacionServicio.buscarPrograma());
	}
	
	public void btnProgramaDetail(){
		try {
			FacesUtils.putFlashObject("programa", getProgramaSelected());
			FacesUtils.redirectPage("pla_admistrar_programa.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Collection<Programa> getLstProgramas() {
		return lstProgramas;
	}

	public void setLstProgramas(Collection<Programa> lstProgramas) {
		this.lstProgramas = lstProgramas;
	}
	public Programa getProgramaSelected() {
		return programaSelected;
	}
	public void setProgramaSelected(Programa programaSelected) {
		this.programaSelected = programaSelected;
	}
	
	
}
