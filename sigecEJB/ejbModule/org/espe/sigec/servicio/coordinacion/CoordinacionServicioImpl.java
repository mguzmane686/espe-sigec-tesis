package org.espe.sigec.servicio.coordinacion;

import java.util.Collection;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;

import org.espe.sigec.model.entities.Aula;
import org.espe.sigec.model.entities.Curso;
import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.Edificio;
import org.espe.sigec.model.entities.Especialidad;
import org.espe.sigec.model.entities.HistoricoCursoEstado;
import org.espe.sigec.model.entities.LugarCurso;
import org.espe.sigec.model.entities.PeriodoAcademico;
import org.espe.sigec.model.sessionBeans.AulaFacadeLocal;
import org.espe.sigec.model.sessionBeans.CursoFacadeLocal;
import org.espe.sigec.model.sessionBeans.CursoPeriodoFacadeLocal;
import org.espe.sigec.model.sessionBeans.EdificioFacadeLocal;
import org.espe.sigec.model.sessionBeans.EspecialidadFacadeLocal;
import org.espe.sigec.model.sessionBeans.HistoricoCursoEstadoFacadeLocal;
import org.espe.sigec.model.sessionBeans.LugarCursoFacadeLocal;
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
	
	@EJB
	private LugarCursoFacadeLocal lugarCursoFacadeLocal;
	@EJB
	private EdificioFacadeLocal edificioFacadeLocal;
	@Resource
	private UserTransaction userTransaction;
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
		
		userTransaction.begin();
		try {
			academicoFacadeLocal.create(periodoAcademico);
			cursoPeriodo.setPeriodoAcademico(periodoAcademico);
			cursoPeriodoFacadeLocal.create(cursoPeriodo);
			
			HistoricoCursoEstado historicoCursoEstado = new HistoricoCursoEstado();
			historicoCursoEstado.setCursoPeriodo(cursoPeriodo);
			historicoCursoEstado.setEstado("1");
			historicoCursoEstado.setEtapaLanzado("1");
			historicoCursoEstado.setEtapaEjecutado("0");
			historicoCursoEstado.setEtapaFinalizado("0");
			historicoCursoEstadoFacadeLocal.create(historicoCursoEstado);
			userTransaction.commit();
		} catch (Exception e) {
			userTransaction.rollback();
			throw new Exception(e.getCause());
		}
		
	}

	@Override
	public Collection<Aula> findAulas() {
		return aulaFacadeLocal.findAll();
	}

	@Override
	public Collection<LugarCurso> findLugarCurso() {
		return lugarCursoFacadeLocal.findAll();
	}

	@Override
	public Collection<Edificio> findEdificioByLugarCurso(String idLugar) {
		return edificioFacadeLocal.findEdificioByLugarCurso(idLugar);
	}

	@Override
	public Collection<Aula> findAulaByEdificio(String idEdificio) {
		return aulaFacadeLocal.findCursoByEdificio(idEdificio);
	}

	@Override
	public void administrarCurso(CursoPeriodo cursoPeriodo) throws Exception {		
		if(cursoPeriodo.getHistoricoCursoEstadoCollection().getCursoPeriodo()==null){
			cursoPeriodo.getHistoricoCursoEstadoCollection().setCursoPeriodo(cursoPeriodo);
			historicoCursoEstadoFacadeLocal.create(cursoPeriodo.getHistoricoCursoEstadoCollection());
		}
		cursoPeriodoFacadeLocal.edit(cursoPeriodo);
	}
	
}
