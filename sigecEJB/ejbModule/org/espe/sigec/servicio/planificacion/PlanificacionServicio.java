package org.espe.sigec.servicio.planificacion;

import java.util.Collection;

import org.espe.sigec.model.entities.Curso;
import org.espe.sigec.model.entities.Especialidad;
import org.espe.sigec.model.entities.PensumAcademico;

/**
 * @author roberto
 *
 */
@QPlanificacion
public interface PlanificacionServicio {
	void crearNuevoCurso(Curso curso, Collection<PensumAcademico> lstPensumAcademicos) throws Exception;
	public Collection<Especialidad> findEspecialidades();
	void editarCurso(Curso curso, Collection<PensumAcademico> lstPensumAcademicos) throws Exception;
}
