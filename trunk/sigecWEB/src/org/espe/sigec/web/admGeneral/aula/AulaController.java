package org.espe.sigec.web.admGeneral.aula;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Aula;
import org.espe.sigec.model.entities.Edificio;
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
	private Collection<SelectItem> itemsEdificio;
	private Aula aula;
	
	
	public AulaController() {
		initEntities();
		setItemsEdificio(new ArrayList<SelectItem>());
	}
	
	private void initEntities(){
		setAula(new Aula());
		getAula().setEdificio(new Edificio());
	}
	
	@PostConstruct
	public void loadEdificio(){
		for(Edificio edificioTMP: admGeneralServicio.findEdificio()){
			getItemsEdificio().add(new SelectItem(edificioTMP.getIdEdificio(), edificioTMP.getNombre()));
		}
	}
	
	public void btnSaveAula(ActionEvent e){
		try {
			admGeneralServicio.createAula(getAula());
			initEntities();
			FacesUtils.addInfoMessage("El aula se cre&oacute correctamante");
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
	
}
