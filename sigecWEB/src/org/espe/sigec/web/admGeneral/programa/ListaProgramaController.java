package org.espe.sigec.web.admGeneral.programa;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Programa;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;

@SuppressWarnings("serial")
@ManagedBean(name="listaProgramaController")
@ViewScoped
public class ListaProgramaController implements Serializable{
	@Inject
	private AdmGeneralServicio admGeneralServicio;
	private Collection<Programa> lstProgramas;
	
	public ListaProgramaController() {
//		admGeneralServicio
	}
	@PostConstruct
	public void cargarProgramas(){
		
	}
	public Collection<Programa> getLstProgramas() {
		return lstProgramas;
	}

	public void setLstProgramas(Collection<Programa> lstProgramas) {
		this.lstProgramas = lstProgramas;
	}
	
	
}
