package org.espe.sigec.servicio.portal;

import java.util.Collection;

import org.espe.sigec.model.entities.CursoEstudiante;
import org.espe.sigec.model.entities.Encuesta;
import org.espe.sigec.model.entities.EncuestaPK;
import org.espe.sigec.model.entities.Programa;
import org.espe.sigec.model.entities.ProgramaCurso;

public interface PortalServicio {
	Collection<ProgramaCurso> buscarPrograma();
	Collection<Programa> buscarProgramaActivo();
	
	void guardarEncuesta(Encuesta encuesta);
	Encuesta buscarEncuesta(EncuestaPK encuestaPK);
	
	Collection<CursoEstudiante> buscarCursosEstudiante(int idEstudiante);
}
