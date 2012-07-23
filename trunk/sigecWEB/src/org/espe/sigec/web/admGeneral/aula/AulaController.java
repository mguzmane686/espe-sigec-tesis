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
import org.espe.sigec.model.entities.Establecimiento;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.web.utils.FacesUtils;
import org.espe.sigec.web.utils.GeneralFunctions;

/**
 * @author diego
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name="aulaController")
@ViewScoped
public class AulaController implements Serializable{
	@Inject
	private AdmGeneralServicio admGeneralServicio;
	
	Collection<SelectItem> itemsLugares;
	Collection<SelectItem> itemsEdificios;
	
	Aula aula;
	
	public AulaController(){
		initEntities();
	}
	
	public void initEntities(){
		setAula(new Aula());
		getAula().setEdificio(new Edificio());
	}
	
	@PostConstruct
	public void loadCombos(){
		setItemsLugares(new ArrayList<SelectItem>());
		setItemsEdificios(new ArrayList<SelectItem>());
	
		for(Establecimiento lugarCursoTMP: admGeneralServicio.findLugar()){
			getItemsLugares().add(new SelectItem(lugarCursoTMP.getIdLugar(), lugarCursoTMP.getNombre()));
		}
		
		if(getAula().getEdificio()!=null && getAula().getEdificio().getLugarCurso()!=null){
			for(Edificio edificioTMP: admGeneralServicio.findEdificioByLugar(getAula().getEdificio().getLugarCurso().getIdLugar())){
				getItemsEdificios().add(new SelectItem(edificioTMP.getIdEdificio(), edificioTMP.getNombre()));
			}
		}else{
			for(Edificio edificioTMP: admGeneralServicio.findEdificioByLugar(getItemsLugares().iterator().next().getValue().toString())){
				getItemsEdificios().add(new SelectItem(edificioTMP.getIdEdificio(), edificioTMP.getNombre()));
			}
		}
	}
	
	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}
		
	public Collection<SelectItem> getItemsLugares() {
		return itemsLugares;
	}

	public void setItemsLugares(Collection<SelectItem> itemsLugares) {
		this.itemsLugares = itemsLugares;
	}

	public Collection<SelectItem> getItemsEdificios() {
		return itemsEdificios;
	}

	public void setItemsEdificios(Collection<SelectItem> itemsEdificios) {
		this.itemsEdificios = itemsEdificios;
	}
	
	public void someChangeLugar(ValueChangeEvent e){
		getItemsEdificios().clear();
		for(Edificio edificioTMP: admGeneralServicio.findEdificioByLugar(e.getNewValue().toString())){
			getItemsEdificios().add(new SelectItem(edificioTMP.getIdEdificio(), edificioTMP.getNombre()));
		}
	}
	
	public void btnSaveAula(ActionEvent e){
		try {
			generarCodigoAula();
			admGeneralServicio.createAula(getAula());
			initEntities();
			loadCombos();
			FacesUtils.addInfoMessage("El aula se cre&oacute exitosamente");
		} catch (Exception e1) {
			FacesUtils.addErrorMessage("Ha ocurrido un error al gurdar el aula");
		}
	}
	
	private void generarCodigoAula(){
		int nextRegistro;
		int cantidadNumeros;
		
		String prefijo;
		String sufijo;
		
		GeneralFunctions generalFunctions = new GeneralFunctions();
		
		nextRegistro = admGeneralServicio.findAula().size() + 1;
		cantidadNumeros = Integer.toString(nextRegistro).length();
		
		prefijo = "AUL";		
		sufijo = generalFunctions.completarCeros(nextRegistro, cantidadNumeros);
		
		aula.setIdAula(prefijo + sufijo);
	}
	
}
