package org.espe.sigec.servicio.curso;

import java.util.Collection;

import org.espe.sigec.model.entities.Curso;
import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.ModuloCurso;
import org.espe.sigec.model.entities.PensumAcademico;

/**
 * @author Roberto
 *
 */
@QCurso
public interface CursoServicio {
	Collection<Curso> findCursos();
	Collection<PensumAcademico> findTemasCurso(Integer idCurso);
	Collection<ModuloCurso> findModulosCurso(Integer idCurso);
	Collection<Curso> findAllCursos();
	Collection<CursoPeriodo> findCursoAbierto();
	Collection<CursoPeriodo> findCursoAbiertoByUser(Integer idPersona);
}
