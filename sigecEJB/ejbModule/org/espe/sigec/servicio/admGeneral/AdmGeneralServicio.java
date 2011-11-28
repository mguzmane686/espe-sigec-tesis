package org.espe.sigec.servicio.admGeneral;

import org.espe.sigec.model.entities.Aula;

/**
 * @author roberto
 *
 */
@QAdmGeneral
public interface AdmGeneralServicio {
	void createAula(Aula aula) throws Exception;
}
