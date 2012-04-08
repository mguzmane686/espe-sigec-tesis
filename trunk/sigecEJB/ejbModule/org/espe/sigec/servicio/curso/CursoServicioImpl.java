package org.espe.sigec.servicio.curso;

import java.util.Collection;

import javax.ejb.EJB;

import org.espe.sigec.model.entities.Curso;
import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.ModuloCurso;
import org.espe.sigec.model.entities.PensumAcademico;
import org.espe.sigec.model.sessionBeans.CursoFacadeLocal;
import org.espe.sigec.model.sessionBeans.CursoPeriodoFacadeLocal;
import org.espe.sigec.model.sessionBeans.ModuloCursoFacadeLocal;
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
	@EJB
	private ModuloCursoFacadeLocal moduloCursoFacadeLocal;
	@Override
	public Collection<Curso> findAllCursos() {
		return cursoFacadeLocal.findAll();
	}

	@Override
	public Collection<PensumAcademico> findTemasModulo(Integer idModulo) {
		return pensumAcademicoFacadeLocal.findTemasModulo(idModulo);
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

	@Override
	public Collection<ModuloCurso> findModulosCurso(Integer idCurso) {
		return moduloCursoFacadeLocal.findModulosCurso(idCurso);
	}
}
