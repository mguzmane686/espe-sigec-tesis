package org.espe.sigec.web.admGeneral.programa;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;

@SuppressWarnings("serial")
@ManagedBean(name="listaProgramaController")
@ViewScoped
public class ListaProgramaController implements Serializable{
	private AdmGeneralServicio admGeneralServicio;
	
	public ListaProgramaController() {
		
	}	
}
