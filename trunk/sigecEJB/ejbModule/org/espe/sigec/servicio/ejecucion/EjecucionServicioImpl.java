package org.espe.sigec.servicio.ejecucion;

import javax.ejb.EJB;

import org.espe.sigec.model.entities.HistoricoCursoEstado;
import org.espe.sigec.model.sessionBeans.HistoricoCursoEstadoFacadeLocal;

/**
 * @author roberto
 *
 */
public class EjecucionServicioImpl implements EjecucionServicio{
	@EJB
	HistoricoCursoEstadoFacadeLocal historicoCursoEstadoFacadeLocal;
	

	@Override
	public void finalizarCurso(HistoricoCursoEstado historicoCursoEstado) throws Exception {
		historicoCursoEstadoFacadeLocal.edit(historicoCursoEstado);
	}
	
	
}
