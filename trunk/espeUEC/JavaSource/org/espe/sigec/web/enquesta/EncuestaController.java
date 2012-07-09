package org.espe.sigec.web.enquesta;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.servicio.curso.CursoServicio;

/**
 * @author Roberto
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name="encuestaController")
@ViewScoped
public class EncuestaController implements Serializable{
	@Inject
	private CursoServicio cursoServicio;
	Collection<CursoPeriodo> lstCursosAbiertos;
	public EncuestaController() {
		
	}
	
	@PostConstruct
	public void initController(){
		setLstCursosAbiertos(cursoServicio.findCursoAbierto());
	}

	public Collection<CursoPeriodo> getLstCursosAbiertos() {
		return lstCursosAbiertos;
	}

	public void setLstCursosAbiertos(Collection<CursoPeriodo> lstCursosAbiertos) {
		this.lstCursosAbiertos = lstCursosAbiertos;
	}
	
	
}
