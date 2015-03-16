package org.espe.sigec.web.coordinacion.profesor;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.espe.sigec.model.entities.Persona;
import org.espe.sigec.model.entities.Profesor;
import org.espe.sigec.model.sessionBeans.ProfesorFacadeLocal;
import org.espe.sigec.web.utils.FacesUtils;

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
	public void btnSaveSeleccionProfesor(ActionEvent e){
		for(Profesor profesorTMP: getLstProfesors()){
			try {
				profesorFacadeLocal.edit(profesorTMP);
				FacesUtils.addInfoMessage("Los registros se actualizaron correctamente");
			} catch (Exception e1) {
				FacesUtils.addErrorMessage("Ocurrio un error al grabar los registros");
			}
		}
	}
	
	public void btnShowProfesorDetail(){
		try {
			FacesUtils.putFlashObject("profesor", getProfesor());
			FacesUtils.redirectPage("coor_registro_nuevo_docente.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
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
