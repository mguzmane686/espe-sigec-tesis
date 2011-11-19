package org.espe.sigec.servicio.inscripcion;

import org.espe.sigec.model.entities.Estudiante;
import org.espe.sigec.model.entities.Persona;
import org.espe.sigec.model.entities.Usuario;
@QInscripcion
public interface InscripcionServicio {
	void registrarEstudiante(Usuario usuario, Persona persona, Estudiante estudiante) throws Exception;
}