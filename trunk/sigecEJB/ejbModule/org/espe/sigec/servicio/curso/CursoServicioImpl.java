package org.espe.sigec.servicio.curso;

import java.util.Collection;

import javax.ejb.EJB;

import org.espe.sigec.model.entities.Curso;
import org.espe.sigec.model.sessionBeans.CursoFacadeLocal;

/**
 * @author Roberto
 *
 */
public class CursoServicioImpl implements CursoServicio{

	@EJB
	private CursoFacadeLocal cursoFacadeLocal;
	
	@Override
	public Collection<Curso> findCursos() {
		return cursoFacadeLocal.findAll();
	}

}
