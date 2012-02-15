package org.espe.sigec.servicio.admGeneral;

import java.util.Collection;

import org.espe.sigec.exception.UserValidateException;
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
	
	//Lugar
	Collection<LugarCurso> findLugar();
	void createLugar(LugarCurso lugarCurso) throws Exception;
	void editLugar(LugarCurso lugarCurso) throws Exception;
	
	//Edificio
	Collection<Edificio> findEdificio();
	Collection<Edificio> findEdificioByLugar(String idLugarCurso);
	void createEdificio(Edificio edificio) throws Exception;
	void editEdificio(Edificio edificio) throws Exception;
	
	//Aula
	Collection<Aula> findAula();
	Collection<Aula> findCursoByEdificio(String idEdificio);
	void createAula(Aula aula) throws Exception;
	void editAula(Aula aula) throws Exception;
	
	
	
	void createAdministrativo(Usuario usuario, Persona persona) throws Exception, UserValidateException;
	void createProfesor(Usuario usuario, Persona persona, Profesor profesor) throws Exception;

}