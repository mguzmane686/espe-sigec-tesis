package org.espe.sigec.servicio.admGeneral;

import java.util.Collection;

import org.espe.sigec.exception.UserValidateException;
import org.espe.sigec.model.entities.Aula;
import org.espe.sigec.model.entities.Curso;
import org.espe.sigec.model.entities.Edificio;
import org.espe.sigec.model.entities.Especialidad;
import org.espe.sigec.model.entities.LugarCurso;
import org.espe.sigec.model.entities.Persona;
import org.espe.sigec.model.entities.Presupuesto;
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
	Collection<Edificio> findEdificioReporte();
	Collection<Edificio> findEdificioByLugar(String idLugarCurso);
	void createEdificio(Edificio edificio) throws Exception;
	void editEdificio(Edificio edificio) throws Exception;
	
	//Aula
	Collection<Aula> findAula();
	Collection<Aula> findCursoByEdificio(String idEdificio);
	void createAula(Aula aula) throws Exception;
	void editAula(Aula aula) throws Exception;
	
	//Especialidad
	Collection<Especialidad> findEspecialidad();
	void createEspecialidad(Especialidad especialidad) throws Exception;
	void editEspecialidad(Especialidad especialidad) throws Exception;
	
	//Presupuesto
	Collection<Presupuesto> findPresupuesto();
	void createPresupuesto(Presupuesto presupuesto) throws Exception;
	void editPresupuesto(Presupuesto presupuesto) throws Exception;
	Collection<Presupuesto> findPresupuesto(String codigoAnio);
	
	
	void createAdministrativo(Usuario usuario, Persona persona) throws Exception, UserValidateException;
	void createProfesor(Usuario usuario, Persona persona, Profesor profesor) throws Exception;
	void editProfesor(Usuario usuario, Persona persona, Profesor profesor) throws Exception;
	
	Collection<Persona> loadPerson();
	Collection<Persona> cargarUsuarios();
	Collection<Persona> cargarContactos();
	void crearContacto(Persona persona) throws Exception;
	
	//Profesor
	Collection<Profesor> cargarProfesores();
	
	//Cursos Existentes
	Collection<Curso> cargarCursos();

	
}