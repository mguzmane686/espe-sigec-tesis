package org.espe.sigec.web.coordinacion.curso;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.espe.sigec.model.entities.CursoPeriodo;

/**
 * @author roberto
 *
 */
@ManagedBean(name = "administrarCursoController")
@ViewScoped
public class AdministrarCursoController {
	private CursoPeriodo cursoPeriodo;
	
	public AdministrarCursoController() {
		setCursoPeriodo((CursoPeriodo) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("curso"));
	}

	public void btnReturnCursosAbiertos(ActionEvent e){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("coor_reporte_curso_abierto.jsf");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public CursoPeriodo getCursoPeriodo() {
		return cursoPeriodo;
	}

	public void setCursoPeriodo(CursoPeriodo cursoPeriodo) {
		this.cursoPeriodo = cursoPeriodo;
	}
	
}
