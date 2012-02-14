package org.espe.sigec.web.admGeneral.aula;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Aula;
import org.espe.sigec.model.entities.Edificio;
import org.espe.sigec.model.entities.LugarCurso;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.web.utils.FacesUtils;

/**
 * @author roberto
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name="aulaController")
@ViewScoped
public class AulaController implements Serializable{
	@Inject
	private AdmGeneralServicio admGeneralServicio;
	Collection<SelectItem> itemsLocalidades;
	private Collection<SelectItem> itemsEdificio;
	private Aula aula;
	private boolean editMode;
	
	public AulaController() {
		initEntities();
		setItemsEdificio(new ArrayList<SelectItem>());
		if((Aula) FacesUtils.getFlashObject("aulaToEdit") == null){
			initEntities();
			editMode = Boolean.FALSE;
		}else{
			setAula((Aula) FacesUtils.getFlashObject("aulaToEdit"));
			editMode = Boolean.TRUE;
		}
		
	}
	
	private void initEntities(){
		setAula(new Aula());
		getAula().setEdificio(new Edificio());
	}
	
	@PostConstruct
	public void loadLocalidad(){
		setItemsLocalidades(new ArrayList<SelectItem>());
		setItemsEdificio(new ArrayList<SelectItem>());
		
		for(LugarCurso lugarCursoTMP: admGeneralServicio.findLugar()){
			getItemsLocalidades().add(new SelectItem(lugarCursoTMP.getIdLugar(), lugarCursoTMP.getNombre()));
		}
		
		if(getAula().getEdificio()!=null && getAula().getEdificio().getLugarCurso()!=null){
			for(Edificio edificioTMP: admGeneralServicio.findEdificioByLugar(getAula().getEdificio().getLugarCurso().getIdLugar())){
				getItemsEdificio().add(new SelectItem(edificioTMP.getIdEdificio(), edificioTMP.getNombre()));
			}
		}else{
			for(Edificio edificioTMP: admGeneralServicio.findEdificioByLugar(getItemsLocalidades().iterator().next().getValue().toString())){
				getItemsEdificio().add(new SelectItem(edificioTMP.getIdEdificio(), edificioTMP.getNombre()));
			}
		}
	}
	
	public void somChangeLocalidad(ValueChangeEvent e){
		getItemsEdificio().clear();
		for(Edificio edificioTMP: admGeneralServicio.findEdificioByLugar(e.getNewValue().toString())){
			getItemsEdificio().add(new SelectItem(edificioTMP.getIdEdificio(), edificioTMP.getNombre()));
		}
	}
	
	public void btnSaveAula(ActionEvent e){
		try {
			if(editMode){
				admGeneralServicio.editAula(getAula());
				FacesUtils.addInfoMessage("El aula se actualizo correctamante");
			}else{
				admGeneralServicio.createAula(getAula());
				FacesUtils.addInfoMessage("El aula se cre&oacute correctamante");
				editMode = Boolean.TRUE;
			}
			
		} catch (Exception e1) {
			FacesUtils.addErrorMessage("Ha ocurrido un error al gurdadr el aula");
		}
	}
	
	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public Collection<SelectItem> getItemsEdificio() {
		return itemsEdificio;
	}

	public void setItemsEdificio(Collection<SelectItem> itemsEdificio) {
		this.itemsEdificio = itemsEdificio;
	}

	public Collection<SelectItem> getItemsLocalidades() {
		return itemsLocalidades;
	}

	public void setItemsLocalidades(Collection<SelectItem> itemsLocalidades) {
		this.itemsLocalidades = itemsLocalidades;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	
}
