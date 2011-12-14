package org.espe.sigec.web.admGeneral.estudiante;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.exception.UserValidateException;
import org.espe.sigec.model.entities.Estudiante;
import org.espe.sigec.model.entities.Persona;
import org.espe.sigec.model.entities.Usuario;
import org.espe.sigec.servicio.inscripcion.InscripcionServicio;
import org.espe.sigec.web.utils.CommonController;
import org.espe.sigec.web.utils.FacesUtils;
import org.richfaces.component.UIPanelMenu;

/**
 * @author Roberto Chasipanta, Diego Ramos
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name="inscripcion")
@ViewScoped
public class RegistroEstudianteController extends CommonController{
	
	@Inject
	private InscripcionServicio inscripcionServicio;
	
	private Estudiante estudiante;
	
	
	public RegistroEstudianteController() {
		setUiPanelMenu((UIPanelMenu) FacesUtils.getFlashObject("menuSigec"));
		instanciarEntidades();
	}
	
	private void instanciarEntidades(){
		setEstudiante(new Estudiante());
		getEstudiante().setPersona(new Persona()); 
		getEstudiante().getPersona().setUsuario(new Usuario());
	}

	public void btnGuardarEstudiante(ActionEvent e){
		try {
			inscripcionServicio.registrarEstudiante(getEstudiante().getPersona().getUsuario(), getEstudiante().getPersona(), getEstudiante());
			FacesUtils.redirectPage("");
//			FacesUtils.putFlashObject("estudiante", SerializationUtils.clone(getEstudiante()));
//				FacesContext.getCurrentInstance().getExternalContext().redirect("detalle_inscripcion.jsf");
			
			instanciarEntidades();
		} catch (UserValidateException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			if(e1.getMessage().contains("El identificador ya existe")){
				FacesUtils.addErrorMessage("El identificador ya existe");
			}
			// TODO Auto-generated catch block
			FacesUtils.addErrorMessage("No se pudo crear el usuario");
		}
			
	}
	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}	
}
