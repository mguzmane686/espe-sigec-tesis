package org.espe.sigec.web.inscripcion;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.Persona;
import org.espe.sigec.model.entities.Usuario;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.servicio.inscripcion.InscripcionServicio;

/**
 * @author Roberto
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name="inscripcionController")
@SessionScoped
public class InscripcionController implements Serializable{
	@Inject
	private InscripcionServicio inscripcionServicio;
	@Inject
	private AdmGeneralServicio admGeneralServicio;
	
	private CursoPeriodo cursoPeriodo;
	private String cedulaUsr;
//	private CursoEstudiante cursoEstudiante;
	private Persona persona;
	private boolean findMode;
	
	
	public InscripcionController() {
		initInscripcion();
	}
	private void initInscripcion(){
		setPersona(new Persona());
		setFindMode(Boolean.TRUE);
	}
	public void btnBuscarPorCedula(){
		Persona persona = null;
		try {
			persona = admGeneralServicio.findPersonByCriteria("ced", getCedulaUsr()).iterator().next();
		} catch (Exception e) {
			System.out.println("la persona no existe");
		}
		
		setPersona(persona);
		
//		getCursoEstudiante().setEstudiante(inscripcionServicio.buscarEstudinateByCedula(getCedulaUsr()));
		if(getPersona()==null){
			initInscripcion();
			getPersona().setUsuario(new Usuario());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La persona no existe ingrese los campos para crearla", null));
			getPersona().setCedula(cedulaUsr);
			setFindMode(Boolean.FALSE);
		}else{
			setFindMode(Boolean.TRUE);
		}
	}
	
	public void btnFindStudent(){
		setFindMode(Boolean.TRUE);
	}
	public void btnNewEstudent(){
		setPersona(new Persona());
		getPersona().setUsuario(new Usuario());
		setFindMode(Boolean.FALSE);
	}
	//getter & setters
	public void btnGuardarInscripcion(){
		System.out.println(getCursoPeriodo());
		try {
			inscripcionServicio.inscripcionEstudianteCurso(getPersona(), getCursoPeriodo());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha inscrito al curso", null));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error", null));
			e.printStackTrace();
		}
		
	}
	public CursoPeriodo getCursoPeriodo() {
		return cursoPeriodo;
	}

	public void setCursoPeriodo(CursoPeriodo cursoPeriodo) {
		this.cursoPeriodo = cursoPeriodo;
	}

	public String getCedulaUsr() {
		return cedulaUsr;
	}

	public void setCedulaUsr(String cedulaUsr) {
		this.cedulaUsr = cedulaUsr;
	}

	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public boolean isFindMode() {
		return findMode;
	}
	public void setFindMode(boolean findMode) {
		this.findMode = findMode;
	}
	
}
