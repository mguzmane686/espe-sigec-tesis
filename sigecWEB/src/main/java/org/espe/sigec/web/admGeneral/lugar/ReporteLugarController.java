package org.espe.sigec.web.admGeneral.lugar;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Establecimiento;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.web.reportes.ReporteGenerico;
import org.espe.sigec.web.utils.FacesUtils;

@SuppressWarnings("serial")
@ManagedBean(name="reporteLugarController")
@ViewScoped
public class ReporteLugarController implements Serializable{
	
	@Inject
	private AdmGeneralServicio admGeneralServicio;
	
	private Collection<Establecimiento> lstLugares;

	public ReporteLugarController(){
		setLstLugares(new ArrayList<Establecimiento>());
	}
	
	@PostConstruct
	public void loadLugares(){
		setLstLugares(admGeneralServicio.findLugar());
	}
		
	public Collection<Establecimiento> getLstLugares() {
		return lstLugares;
	}

	public void setLstLugares(Collection<Establecimiento> lstLugares) {
		this.lstLugares = lstLugares;
	}
	
	public void btnShowLugarDetail(Establecimiento lugar){
		FacesUtils.putFlashObject("lugarToEdit",lugar);
		try {
			FacesUtils.redirectPage("adm_administrar_lugar.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void btnPDF(ActionEvent e){
		ReporteGenerico reporteGenerico = new ReporteGenerico();
		reporteGenerico.generarReporteSimple("lugares", getLstLugares());
	}
	
}