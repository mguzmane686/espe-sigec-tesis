package org.espe.sigec.web.admGeneral.aula;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.espe.sigec.model.entities.Aula;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.web.reportes.ReporteGenerico;
import org.espe.sigec.web.utils.FacesUtils;

/**
 * @author diego
 *
 */

@SuppressWarnings("serial")
@ManagedBean(name="reporteAulaController")
@ViewScoped
public class ReporteAulaController implements Serializable {

	@Inject
	private AdmGeneralServicio admGeneralServicio;
	
	private Collection<Aula> lstAulas;
	
	public ReporteAulaController() {
		setLstAulas(new ArrayList<Aula>());
	}

	@PostConstruct
	public void loadAulas(){
		setLstAulas(admGeneralServicio.findAula());
	}
	
	public Collection<Aula> getLstAulas() {
		return lstAulas;
	}

	public void setLstAulas(Collection<Aula> lstAulas) {
		this.lstAulas = lstAulas;
	}
	
	public void btnShowAulaDetail(Aula aula){
		FacesUtils.putFlashObject("aulaToEdit",aula);
		try {
			FacesUtils.redirectPage("adm_administrar_aula.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void btnPDF(ActionEvent e){
		ReporteGenerico reporteGenerico = new ReporteGenerico();
		reporteGenerico.generarReporteSimple("aulas", getLstAulas());
	}
	
}
