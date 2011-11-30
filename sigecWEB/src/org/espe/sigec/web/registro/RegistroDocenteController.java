package org.espe.sigec.web.registro;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.espe.sigec.model.entities.Persona;
import org.espe.sigec.model.entities.Profesor;
import org.espe.sigec.model.entities.Usuario;
import org.espe.sigec.model.entities.UsuarioPerfil;
import org.espe.sigec.model.entities.UsuarioPerfilPK;
import org.espe.sigec.model.sessionBeans.PersonaFacadeLocal;
import org.espe.sigec.model.sessionBeans.ProfesorFacadeLocal;
import org.espe.sigec.model.sessionBeans.UsuarioFacadeLocal;
import org.espe.sigec.model.sessionBeans.UsuarioPerfilFacadeLocal;
import org.espe.sigec.web.utils.FacesUtils;
import org.espe.sigec.web.utils.SigecConstantes;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

/**
 * @author Roberto
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name="registroDocenteController")
@SessionScoped
public class RegistroDocenteController implements Serializable{
	@EJB
	private ProfesorFacadeLocal profesorFacadeLocal;
	@EJB
	private UsuarioFacadeLocal usuarioFacadeLocal;
	@EJB
	private PersonaFacadeLocal personaFacadeLocal;
	@EJB
	private UsuarioPerfilFacadeLocal usuarioPerfilFacadeLocal;
	
	private Profesor profesor;
	private Usuario usuario;
	private ArrayList<Persona> files;
	public RegistroDocenteController() {
		initEntities();
	}

	
	private void initEntities(){
		setProfesor(new Profesor());
		getProfesor().setTituloNivelTres(SigecConstantes.ESTADO_ACTIVO_BOOLEANO);
		getProfesor().setExpeAnioArea(SigecConstantes.ESTADO_ACTIVO_BOOLEANO);
		getProfesor().setExpeAnioDocente(SigecConstantes.ESTADO_ACTIVO_BOOLEANO);
		getProfesor().setTiempoComp(SigecConstantes.ESTADO_ACTIVO_BOOLEANO);
		getProfesor().setEstadoSeleccion(SigecConstantes.ESTADO_INACTIVO_BOOLEANO);
		getProfesor().setPersona(new Persona());
		setFiles(new ArrayList<Persona>());
		setUsuario(new Usuario());
	}
	
	public void uploadImage(FileUploadEvent event) throws Exception {
        UploadedFile item = event.getUploadedFile();
        getProfesor().getPersona().setFoto(item.getData());
        files.add(getProfesor().getPersona());
    }
	
	public void paint(OutputStream stream, Object object) throws IOException {
        stream.write(getFiles().get((Integer) object).getFoto());
        stream.close();
    }
	
	public void btnSaveProfesor(ActionEvent e){
		try {
			getUsuario().setIdentificador(getProfesor().getPersona().getCedula());
			getUsuario().setClave(getProfesor().getPersona().getCedula());
			usuarioFacadeLocal.create(getUsuario());
			UsuarioPerfil usuarioPerfil = new UsuarioPerfil(new UsuarioPerfilPK());
			usuarioPerfil.getUsuarioPerfilPK().setIdUsuario(usuario.getIdUsuario());
			usuarioPerfil.getUsuarioPerfilPK().setIdPerfil(SigecConstantes.PERFIL_PROFESOR);
			usuarioPerfil.setEstado(SigecConstantes.ESTADO_ACTIVO_BOOLEANO);
			
			usuarioPerfilFacadeLocal.create(usuarioPerfil);
			
			profesor.getPersona().setUsuario(getUsuario());
			personaFacadeLocal.create(profesor.getPersona());
			profesorFacadeLocal.create(profesor);
			initEntities();
			FacesUtils.addInfoMessage("El docente fue agregado correctamente");
		} catch (Exception e1) {
			FacesUtils.addErrorMessage("Ocurrio un error al agregar al docente");
		}
	}
	
	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ArrayList<Persona> getFiles() {
		return files;
	}
	
	public void setFiles(ArrayList<Persona> files) {
		this.files = files;
	}
}
