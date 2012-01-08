package org.espe.sigec.servicio.admGeneral;

import java.util.Collection;

import org.espe.sigec.model.entities.Aula;
import org.espe.sigec.model.entities.Edificio;
import org.espe.sigec.model.entities.LugarCurso;
import org.espe.sigec.model.entities.Persona;
import org.espe.sigec.model.entities.Profesor;
import org.espe.sigec.model.entities.Usuario;

/**
 * @author roberto
 *
 */
@QAdmGeneral
public interface AdmGeneralServicio {
	void createAula(Aula aula) throws Exception;
	void createAdministrativo(Usuario usuario, Persona persona) throws Exception;
	Collection<LugarCurso> findLugar();
	Collection<Edificio> findEdificioByLugar(String idLugarCurso);
	
	Collection<Edificio> findEdificio();
	Collection<Aula> findCursoByEdificio(String idEdificio);
	void createProfesor(Usuario usuario, Persona persona, Profesor profesor) throws Exception;
	void createEdificio(Edificio edificio) throws Exception;
	void createLocalidad(LugarCurso lugarCurso) throws Exception;
	void editEdificio(Edificio edificio) throws Exception;
}