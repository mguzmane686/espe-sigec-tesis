package org.espe.sigec.web.admGeneral.edificio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Edificio;
import org.espe.sigec.model.entities.LugarCurso;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.web.utils.FacesUtils;

@SuppressWarnings("serial")
@ManagedBean(name="edificioController")
@ViewScoped
public class EdificioController implements Serializable{
	@Inject
	private AdmGeneralServicio admGeneralServicio;
	
	private Edificio edificio;
	private Collection<SelectItem> itemsLugares; 
	
	public EdificioController() {
		initEntities();
	}
	
	private void initEntities(){
		setEdificio(new Edificio());
		getEdificio().setLugarCurso(new LugarCurso());
	}
	
	@PostConstruct
	public void loadLugares(){
		setItemsLugares(new ArrayList<SelectItem>());
		for (LugarCurso lugarCurso: admGeneralServicio.findLugar()){
			getItemsLugares().add(new SelectItem(lugarCurso.getIdLugar(),lugarCurso.getNombre()));
		}
	}
	
	public void btnSaveEdificio(ActionEvent e){
		try {
			admGeneralServicio.createEdificio(getEdificio());
			initEntities();
			loadLugares();
			FacesUtils.addInfoMessage("El edificio se cre&oacute exitosamente");
		} catch (Exception e1) {
			FacesUtils.addInfoMessage("No se pudo crear el edificio");
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
	
}