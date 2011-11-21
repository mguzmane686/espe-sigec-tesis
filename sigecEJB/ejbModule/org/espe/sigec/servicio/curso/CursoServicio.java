package org.espe.sigec.servicio.curso;

import java.util.Collection;

import org.espe.sigec.model.entities.Curso;

/**
 * @author Roberto
 *
 */
@QCurso
public interface CursoServicio {
	public Collection<Curso> findCursos();
}
