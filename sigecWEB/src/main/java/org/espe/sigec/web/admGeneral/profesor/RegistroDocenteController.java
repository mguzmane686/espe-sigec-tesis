package org.espe.sigec.web.admGeneral.profesor;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.espe.sigec.exception.UserValidateException;
import org.espe.sigec.model.entities.EducacionFormacion;
import org.espe.sigec.model.entities.Especialidad;
import org.espe.sigec.model.entities.EstudioComplementario;
import org.espe.sigec.model.entities.Persona;
import org.espe.sigec.model.entities.Profesor;
import org.espe.sigec.model.entities.Usuario;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.utils.SigecConstantes;
import org.espe.sigec.web.utils.FacesUtils;
import org.espe.sigec.web.utils.SessionBean;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

/**
 * @author Roberto
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name="registroDocenteController")
@ViewScoped
public class RegistroDocenteController implements Serializable{
	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;
	@Inject
	private AdmGeneralServicio admGeneralServicio;
	
	private Profesor profesor;
	private Usuario usuario;
	private ArrayList<Persona> files;
	private boolean editMode;
	private boolean renderEditionButtons;
	private Collection<SelectItem> especialidades;
	private Collection<EstudioComplementario> lstEstudiosComplementarios;
	private EstudioComplementario estudioComplementario;
	private boolean renderPopupEstComp;
	private int row;
	public RegistroDocenteController() {
		setProfesor((Profesor) FacesUtils.getFlashObject("profesor"));
		setEditMode(Boolean.TRUE);
		setRenderEditionButtons(Boolean.FALSE);
		if(getProfesor() ==null){
			initEntities();
			setEditMode(Boolean.FALSE);
			setRenderEditionButtons(Boolean.TRUE);
		}else{
			if(getProfesor().getPersona().getEducacionFormacion() == null){
				getProfesor().getPersona().setEducacionFormacion(new EducacionFormacion());
			}
			
			setLstEstudiosComplementarios(new ArrayList<EstudioComplementario>());
			setEstudioComplementario(new EstudioComplementario());
		}
	}
	
	private void initEntities(){
		setProfesor(new Profesor());
		getProfesor().setTituloNivelTres(SigecConstantes.ESTADO_ACTIVO_BOOLEANO);
		getProfesor().setExpeAnioArea(SigecConstantes.ESTADO_ACTIVO_BOOLEANO);
		getProfesor().setExpeAnioDocente(SigecConstantes.ESTADO_ACTIVO_BOOLEANO);
		getProfesor().setTiempoComp(SigecConstantes.ESTADO_ACTIVO_BOOLEANO);
		getProfesor().setEstadoSeleccion(SigecConstantes.ESTADO_INACTIVO_BOOLEANO);
		getProfesor().setPersona(new Persona());
		getProfesor().getPersona().setEducacionFormacion(new EducacionFormacion());
		//TODO Verfificar cambio
//		getProfesor().setEspecialidad(new Especialidad());
		setFiles(new ArrayList<Persona>());
		setUsuario(new Usuario());
		setLstEstudiosComplementarios(new ArrayList<EstudioComplementario>());
		setEstudioComplementario(new EstudioComplementario());
		
		
		
	}
	
	@PostConstruct
	public void cargarEspecialidades(){
		Collection<Especialidad> lstEspecialidades = admGeneralServicio.findEspecialidad();
		setEspecialidades(new ArrayList<SelectItem>());
		
		for(Especialidad especialidad: lstEspecialidades){
			getEspecialidades().add(new SelectItem(especialidad.getIdEspecialidad(), especialidad.getNombre()));
		}
		getSessionBean().setRenderimage(Boolean.FALSE);
		if(getProfesor().getPersona().getFoto()!=null){
			getSessionBean().setRenderimage(Boolean.TRUE);
			getSessionBean().setData(getProfesor().getPersona().getFoto());
		}
	}
	
	@PreDestroy
	public void borrarSessionBean(){
		FacesUtils.removeManagedBean("sessionBean");
	}
	public void uploadImage(FileUploadEvent event) throws Exception {
        UploadedFile item = event.getUploadedFile();
        getProfesor().getPersona().setFoto(item.getData());
        getSessionBean().setData(item.getData());
        getSessionBean().setRenderimage(Boolean.TRUE);
        
        this.row++;
//        files.add(getProfesor().getPersona());
//        FacesUtils.refresh();
    }
	
	public void btnSaveProfesor(ActionEvent e){
		try {
			if(isEditMode()){
				admGeneralServicio.editProfesor(getUsuario(), getProfesor().getPersona(), getProfesor());
				FacesUtils.addInfoMessage("El docente fue actualizado correctamente");
				setRenderEditionButtons(Boolean.FALSE);
			}else{
				admGeneralServicio.createProfesor(getUsuario(), getProfesor().getPersona(), getProfesor());
				setEditMode(Boolean.TRUE);
				initEntities();
				FacesUtils.addInfoMessage("El docente fue agregado correctamente");
			}
			
		}catch (UserValidateException e2){
			FacesUtils.addInfoMessage(e2.getMessage());
		}catch (Exception e1) {
			FacesUtils.addErrorMessage("Ocurrio un error al grabar el docente");
		}
	}
	
	public void paint(OutputStream stream, Object object) throws IOException {
		
        try {
        	stream.write(getProfesor().getPersona().getFoto());
            stream.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public void btnCancelSaveProfesor(ActionEvent e){
		setRenderEditionButtons(Boolean.FALSE);
	}
	
	public void btnEditProfesor(ActionEvent e){
		setRenderEditionButtons(Boolean.TRUE);
	}

	public void btnAtrasListaProfesor(ActionEvent e){
		try {
			FacesUtils.removeFlashObject("profesor");
			FacesUtils.redirectPage("coor_seleccion_docente.jsf");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public void btnShowPopupAgragarEstudioComplemetario(){
		setEstudioComplementario(new EstudioComplementario());
	}
	
	public void btnAgregarEstudioComplemetario(){
		getLstEstudiosComplementarios().add(getEstudioComplementario());
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

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public boolean isRenderEditionButtons() {
		return renderEditionButtons;
	}

	public void setRenderEditionButtons(boolean renderEditionButtons) {
		this.renderEditionButtons = renderEditionButtons;
	}

	public Collection<SelectItem> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(Collection<SelectItem> especialidades) {
		this.especialidades = especialidades;
	}

	public Collection<EstudioComplementario> getLstEstudiosComplementarios() {
		return lstEstudiosComplementarios;
	}

	public void setLstEstudiosComplementarios(
			Collection<EstudioComplementario> lstEstudiosComplementarios) {
		this.lstEstudiosComplementarios = lstEstudiosComplementarios;
	}

	public EstudioComplementario getEstudioComplementario() {
		return estudioComplementario;
	}

	public void setEstudioComplementario(EstudioComplementario estudioComplementario) {
		this.estudioComplementario = estudioComplementario;
	}

	public boolean isRenderPopupEstComp() {
		return renderPopupEstComp;
	}

	public void setRenderPopupEstComp(boolean renderPopupEstComp) {
		this.renderPopupEstComp = renderPopupEstComp;
	}

	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	
	
	
}