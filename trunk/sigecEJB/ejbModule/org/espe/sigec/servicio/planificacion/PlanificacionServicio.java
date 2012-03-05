package org.espe.sigec.servicio.planificacion;

import java.util.Collection;

import org.espe.sigec.model.entities.Curso;
import org.espe.sigec.model.entities.Especialidad;
import org.espe.sigec.model.entities.ModuloCurso;
import org.espe.sigec.model.entities.PensumAcademico;
import org.espe.sigec.model.entities.Programa;

/**
 * @author roberto
 *
 */
@QPlanificacion
public interface PlanificacionServicio {
	void crearNuevoCurso(Curso curso, Collection<PensumAcademico> lstPensumAcademicos) throws Exception;
	void crearNuevoCursoModulo(Curso curso, Collection<ModuloCurso> lstModuloCursos) throws Exception;
	public Collection<Especialidad> findEspecialidades();
	void editarCurso(Curso curso, Collection<PensumAcademico> lstPensumAcademicos) throws Exception;
	void editarCursoModulo(Curso curso, Collection<ModuloCurso> lstModuloCursos) throws Exception;
	void crearPrograma(Programa programa) throws Exception;
}
