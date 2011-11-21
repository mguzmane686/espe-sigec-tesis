package org.espe.sigec.servicio.curso;

import java.util.Collection;

import javax.ejb.EJB;

import org.espe.sigec.model.entities.Curso;
import org.espe.sigec.model.entities.PensumAcademico;
import org.espe.sigec.model.sessionBeans.CursoFacadeLocal;
import org.espe.sigec.model.sessionBeans.PensumAcademicoFacadeLocal;

/**
 * @author Roberto
 *
 */
public class CursoServicioImpl implements CursoServicio{

	@EJB
	private CursoFacadeLocal cursoFacadeLocal;
	
	@EJB
	private PensumAcademicoFacadeLocal pensumAcademicoFacadeLocal;
	
	@Override
	public Collection<Curso> findCursos() {
		return cursoFacadeLocal.findAll();
	}

	@Override
	public Collection<PensumAcademico> findTemasCurso(Integer idCurso) {
		return pensumAcademicoFacadeLocal.findTemasCurso(idCurso);
	}

}
