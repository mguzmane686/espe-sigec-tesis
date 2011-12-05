package org.espe.sigec.servicio.inscripcion;

import java.util.Collection;

import org.espe.sigec.model.entities.CursoEstudiante;
import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.Estudiante;
import org.espe.sigec.model.entities.Persona;
import org.espe.sigec.model.entities.Usuario;
/**
 * @author roberto
 *
 */
@QInscripcion
public interface InscripcionServicio {
	void registrarEstudiante(Usuario usuario, Persona persona, Estudiante estudiante) throws Exception;
	void inscripcionEstudianteCurso(Estudiante estudiante, CursoPeriodo cursoPeriodo, CursoEstudiante cursoEstudiante) throws Exception;
	Collection<CursoPeriodo> cargarCursoLanzado();
}