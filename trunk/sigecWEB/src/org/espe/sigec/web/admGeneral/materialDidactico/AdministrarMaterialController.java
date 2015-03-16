package org.espe.sigec.web.admGeneral.materialDidactico;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.espe.sigec.model.entities.MaterialDidacticoCatalogo;
import org.espe.sigec.model.sessionBeans.MaterialDidacticoCatalogoFacadeLocal;
import org.espe.sigec.web.utils.FacesUtils;

/**
 * @author roberto
 *
 */
@ManagedBean(name = "administrarMaterialController")
@ViewScoped
public class AdministrarMaterialController {
	private Collection<MaterialDidacticoCatalogo> lstMaterialDidacticoCatalogos;
	private MaterialDidacticoCatalogo materialDidacticoCatalogo;
	
	@EJB
	MaterialDidacticoCatalogoFacadeLocal materialDidacticoCatalogoFacadeLocal;
	
	
	public AdministrarMaterialController() {
		setLstMaterialDidacticoCatalogos(new ArrayList<MaterialDidacticoCatalogo>());
		setMaterialDidacticoCatalogo(new MaterialDidacticoCatalogo());
	}
	
	@PostConstruct
	public void caragarMateriales(){
		setLstMaterialDidacticoCatalogos(materialDidacticoCatalogoFacadeLocal.findAll());
	}
	
	public void btnCrearNuevoMaterial(){
		try {
			getMaterialDidacticoCatalogo().setEstado("ACT");
			materialDidacticoCatalogoFacadeLocal.create(getMaterialDidacticoCatalogo());
			lstMaterialDidacticoCatalogos.add(getMaterialDidacticoCatalogo());
			setMaterialDidacticoCatalogo(new MaterialDidacticoCatalogo());
			
			FacesUtils.addInfoMessage("Item creado correctamente");
		} catch (Exception e) {
			FacesUtils.addInfoMessage("Ocurrio un error al guardar el item");
			e.printStackTrace();
		}
	}

	public Collection<MaterialDidacticoCatalogo> getLstMaterialDidacticoCatalogos() {
		return lstMaterialDidacticoCatalogos;
	}

	public void setLstMaterialDidacticoCatalogos(
			Collection<MaterialDidacticoCatalogo> lstMaterialDidacticoCatalogos) {
		this.lstMaterialDidacticoCatalogos = lstMaterialDidacticoCatalogos;
	}

	public MaterialDidacticoCatalogo getMaterialDidacticoCatalogo() {
		return materialDidacticoCatalogo;
	}

	public void setMaterialDidacticoCatalogo(
			MaterialDidacticoCatalogo materialDidacticoCatalogo) {
		this.materialDidacticoCatalogo = materialDidacticoCatalogo;
	}
	
	
	
}