package org.espe.sigec.servicio.ejecucion;

import java.util.Collection;

import org.espe.sigec.model.entities.CursoEstudiante;
import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.HistoricoCursoEstado;

/**
 * @author roberto
 *
 */
@QEjecucion
public interface EjecucionServicio {
	void finalizarCurso(HistoricoCursoEstado historicoCursoEstado) throws Exception;
	void actualizarAsistenciasEstudianteCurso(CursoPeriodo cursoPeriodo, Collection<CursoEstudiante> lstCursoEstudiantes) throws Exception;
}
