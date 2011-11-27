package org.espe.sigec.web.coordinacion.profesor;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.espe.sigec.model.entities.Persona;
import org.espe.sigec.model.entities.Profesor;
import org.espe.sigec.model.sessionBeans.ProfesorFacadeLocal;

/**
 * @author roberto
 *
 */
@ManagedBean(name="seleccionPorfesorController")
@SessionScoped
public class SeleccionPorfesorController {
	@EJB
	private ProfesorFacadeLocal profesorFacadeLocal;
	private Collection<Profesor> lstProfesors;
	private Profesor profesor ;
	public SeleccionPorfesorController() {
		setProfesor(new Profesor());
		getProfesor().setPersona(new Persona());
	}
	@PostConstruct
	public void loadProfesores(){
		setLstProfesors(profesorFacadeLocal.findProfesores());
	}

	public Collection<Profesor> getLstProfesors() {
		return lstProfesors;
	}

	public void paint(OutputStream stream, Object object) throws IOException {
		if(getProfesor().getPersona().getFoto()!=null){
			stream.write(getProfesor().getPersona().getFoto());
		}
        stream.close();
    }
	
	public void setLstProfesors(Collection<Profesor> lstProfesors) {
		this.lstProfesors = lstProfesors;
	}
	public Profesor getProfesor() {
		return profesor;
	}
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	
	
}
