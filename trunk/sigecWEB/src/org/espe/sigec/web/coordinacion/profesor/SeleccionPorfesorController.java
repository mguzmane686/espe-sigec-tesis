package org.espe.sigec.web.coordinacion.profesor;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.espe.sigec.model.entities.Profesor;
import org.espe.sigec.model.sessionBeans.ProfesorFacadeLocal;

/**
 * @author roberto
 *
 */
@ManagedBean(name="seleccionPorfesorController")
@ViewScoped
public class SeleccionPorfesorController {
	@EJB
	private ProfesorFacadeLocal profesorFacadeLocal;
	private Collection<Profesor> lstProfesors;
	
	public SeleccionPorfesorController() {
		
	}
	@PostConstruct
	public void loadProfesores(){
		setLstProfesors(profesorFacadeLocal.findAll());
	}

	public Collection<Profesor> getLstProfesors() {
		return lstProfesors;
	}

	public void setLstProfesors(Collection<Profesor> lstProfesors) {
		this.lstProfesors = lstProfesors;
	}
	
	
}
