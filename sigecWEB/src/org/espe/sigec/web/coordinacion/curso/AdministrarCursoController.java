package org.espe.sigec.web.coordinacion.curso;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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

	public CursoPeriodo getCursoPeriodo() {
		return cursoPeriodo;
	}

	public void setCursoPeriodo(CursoPeriodo cursoPeriodo) {
		this.cursoPeriodo = cursoPeriodo;
	}
	
}
