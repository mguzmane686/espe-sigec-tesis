package org.espe.sigec.web.inscripcion;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.espe.sigec.model.entities.CursoPeriodo;

/**
 * @author Roberto
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name="inscripcionController")
@SessionScoped
public class InscripcionController implements Serializable{
	private CursoPeriodo cursoPeriodo;

	
	public InscripcionController() {
		super();
	}

	public void btnGuardarInscripcion(){
		System.out.println(getCursoPeriodo());
	}
	public CursoPeriodo getCursoPeriodo() {
		return cursoPeriodo;
	}

	public void setCursoPeriodo(CursoPeriodo cursoPeriodo) {
		this.cursoPeriodo = cursoPeriodo;
	}
	
}
