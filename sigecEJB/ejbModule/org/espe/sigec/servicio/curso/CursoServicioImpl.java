package org.espe.sigec.servicio.curso;

import java.util.Collection;

import javax.ejb.EJB;

import org.espe.sigec.model.entities.Curso;
import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.PensumAcademico;
import org.espe.sigec.model.sessionBeans.CursoFacadeLocal;
import org.espe.sigec.model.sessionBeans.CursoPeriodoFacadeLocal;
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
	
	@EJB
	private CursoPeriodoFacadeLocal periodoFacadeLocal;
	
	@Override
	public Collection<Curso> findAllCursos() {
		return cursoFacadeLocal.findAll();
	}

	@Override
	public Collection<PensumAcademico> findTemasCurso(Integer idCurso) {
		return pensumAcademicoFacadeLocal.findTemasCurso(idCurso);
	}

	@Override
	public Collection<CursoPeriodo> findCursoAbierto() {
		return periodoFacadeLocal.findCursoAbierto();
	}
	
	@Override
	public Collection<Curso> findCursos(){
		return cursoFacadeLocal.findCursoByEstado();
	}

	@Override
	public Collection<CursoPeriodo> findCursoAbiertoByUser(Integer idPersona) {
		return periodoFacadeLocal.findCursoAbiertoByUser(idPersona);
	}
}
