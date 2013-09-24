package org.espe.sigec.servicio.ejecucion;

import org.espe.sigec.model.entities.HistoricoCursoEstado;

/**
 * @author roberto
 *
 */
@QEjecucion
public interface EjecucionServicio {
	void finalizarCurso(HistoricoCursoEstado historicoCursoEstado) throws Exception;
}
