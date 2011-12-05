package org.espe.sigec.servicio.coordinacion;

import java.util.Collection;

import javax.ejb.EJB;

import org.espe.sigec.model.entities.Aula;
import org.espe.sigec.model.entities.Curso;
import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.Especialidad;
import org.espe.sigec.model.entities.HistoricoCursoEstado;
import org.espe.sigec.model.entities.PeriodoAcademico;
import org.espe.sigec.model.sessionBeans.AulaFacadeLocal;
import org.espe.sigec.model.sessionBeans.CursoFacadeLocal;
import org.espe.sigec.model.sessionBeans.CursoPeriodoFacadeLocal;
import org.espe.sigec.model.sessionBeans.EspecialidadFacadeLocal;
import org.espe.sigec.model.sessionBeans.HistoricoCursoEstadoFacadeLocal;
import org.espe.sigec.model.sessionBeans.PeriodoAcademicoFacadeLocal;

/**
 * @author roberto
 *
 */
public class CoordinacionServicioImpl implements CoordinacionServicio{
	@EJB
	private AulaFacadeLocal aulaFacadeLocal;
	@EJB
	private CursoFacadeLocal cursoFacadeLocal;
	@EJB
	private PeriodoAcademicoFacadeLocal academicoFacadeLocal;
	@EJB
	private CursoPeriodoFacadeLocal cursoPeriodoFacadeLocal;
	@EJB
	private EspecialidadFacadeLocal especialidadFacadeLocal;
	@EJB
	private HistoricoCursoEstadoFacadeLocal historicoCursoEstadoFacadeLocal;
	@Override
	public Collection<Especialidad> findEspecialidades() {
		
		return especialidadFacadeLocal.findAll();
	}
	
	@Override
	public Collection<Curso> findCursoByEspecialidad(Integer idEspecialidad) {
		return cursoFacadeLocal.findCursoByEspecialidad(idEspecialidad);
	}
	
	@Override
	public void abrirCurso(PeriodoAcademico periodoAcademico,
			CursoPeriodo cursoPeriodo) throws Exception {
		academicoFacadeLocal.create(periodoAcademico);
		cursoPeriodo.setPeriodoAcademico(periodoAcademico);
		cursoPeriodoFacadeLocal.create(cursoPeriodo);
		
		HistoricoCursoEstado historicoCursoEstado = new HistoricoCursoEstado();
		historicoCursoEstado.setCursoPeriodo(cursoPeriodo);
		historicoCursoEstado.setEstado("1");
		historicoCursoEstado.setEtapaLanzado("1");
		historicoCursoEstadoFacadeLocal.create(historicoCursoEstado);
	}

	@Override
	public Collection<Aula> findAulas() {
		return aulaFacadeLocal.findAll();
	}
	
	
}
