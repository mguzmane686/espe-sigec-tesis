package org.espe.sigec.servicio.ejecucion;

import java.util.Collection;

import javax.ejb.EJB;

import org.espe.sigec.model.entities.CursoEstudiante;
import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.HistoricoCursoEstado;
import org.espe.sigec.model.sessionBeans.CursoEstudianteFacadeLocal;
import org.espe.sigec.model.sessionBeans.CursoPeriodoFacadeLocal;
import org.espe.sigec.model.sessionBeans.HistoricoCursoEstadoFacadeLocal;

/**
 * @author roberto
 *
 */
public class EjecucionServicioImpl implements EjecucionServicio{
	@EJB
	HistoricoCursoEstadoFacadeLocal historicoCursoEstadoFacadeLocal;
	@EJB
	CursoPeriodoFacadeLocal cursoPeriodoFacadeLocal;
	@EJB
	CursoEstudianteFacadeLocal cursoEstudianteFacadeLocal;
	@Override
	public void finalizarCurso(HistoricoCursoEstado historicoCursoEstado) throws Exception {
		historicoCursoEstadoFacadeLocal.edit(historicoCursoEstado);
	}


	@Override
	public void actualizarAsistenciasEstudianteCurso(CursoPeriodo cursoPeriodo,
			Collection<CursoEstudiante> lstCursoEstudiantes) throws Exception {
		cursoPeriodoFacadeLocal.edit(cursoPeriodo);
		for(CursoEstudiante cursoEstudiante: lstCursoEstudiantes){
			cursoEstudianteFacadeLocal.edit(cursoEstudiante);
		}
	}
	
	
}
