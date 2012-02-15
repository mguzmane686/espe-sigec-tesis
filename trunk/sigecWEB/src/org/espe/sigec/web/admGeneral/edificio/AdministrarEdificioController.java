package org.espe.sigec.web.admGeneral.edificio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Edificio;
import org.espe.sigec.model.entities.LugarCurso;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.web.utils.FacesUtils;

/**
 * @author diego
 *
 */
@ManagedBean(name = "administrarEdificioController")
@ViewScoped
public class AdministrarEdificioController {

	@Inject
	private AdmGeneralServicio admGeneralServicio;
	
	private Edificio edificio;
	private Collection<SelectItem> itemsLugares; 
	
	private boolean editMode;
	
	public AdministrarEdificioController(){
		setEdificio((Edificio) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("edificioToEdit"));
		//getEdificio().setLugarCurso(getEdificio().getLugarCurso());
	}
	
	@PostConstruct
	public void loadLugares(){
		setItemsLugares(new ArrayList<SelectItem>());
		for (LugarCurso lugarCurso: admGeneralServicio.findLugar()){
			getItemsLugares().add(new SelectItem(lugarCurso.getIdLugar(),lugarCurso.getNombre()));
		}
	}
	
	public Edificio getEdificio() {
		return edificio;
	}
	
	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}
	
	public Collection<SelectItem> getItemsLugares() {
		return itemsLugares;
	}

	public void setItemsLugares(Collection<SelectItem> itemsLugares) {
		this.itemsLugares = itemsLugares;
	}
	
	public boolean isEditMode() {
		return editMode;
	}
	
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
		
	public void btnEdit(ActionEvent e){
		setEditMode(Boolean.TRUE);
	}
	
	public void btnCancelEdit(ActionEvent e){
		setEditMode(Boolean.FALSE);
	}
	
	public void btnSave(ActionEvent e){
		try {
			setEditMode(Boolean.FALSE);
			admGeneralServicio.editEdificio(getEdificio());
			FacesUtils.addInfoMessage("Edificio actualizado exitosamente");
		} catch (Exception e1) {
			FacesUtils.addInfoMessage("Ha ocurrido un error al editar el edificio");
			e1.printStackTrace();
		}
	}
	
	public void btnReturnReporteEdificios(ActionEvent e){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("adm_reporte_edificio.jsf");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}
