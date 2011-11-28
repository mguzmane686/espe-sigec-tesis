package org.espe.sigec.servicio.admGeneral;

import org.espe.sigec.model.entities.Aula;
import org.espe.sigec.model.entities.Persona;
import org.espe.sigec.model.entities.Usuario;

/**
 * @author roberto
 *
 */
@QAdmGeneral
public interface AdmGeneralServicio {
	void createAula(Aula aula) throws Exception;
	void createAdministrativo(Usuario usuario, Persona persona) throws Exception;
}
