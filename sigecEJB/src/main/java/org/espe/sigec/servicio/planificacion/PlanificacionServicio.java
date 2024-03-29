package org.espe.sigec.servicio.planificacion;

import java.util.Collection;

import org.espe.sigec.model.entities.Curso;
import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.Especialidad;
import org.espe.sigec.model.entities.PensumAcademico;
import org.espe.sigec.model.entities.Programa;
import org.espe.sigec.model.entities.ProgramaCurso;

/**
 * @author roberto
 *
 */
@QPlanificacion
public interface PlanificacionServicio {
	void crearNuevoCurso(Curso curso, Collection<PensumAcademico> lstPensumAcademicos) throws Exception;
//	void crearNuevoCursoModulo(Curso curso, Collection<ContenidoCurso> lstModuloCursos) throws Exception;
	public Collection<Especialidad> findEspecialidades();
	void editarCurso(Curso curso, Collection<PensumAcademico> lstPensumAcademicos) throws Exception;
//	void editarCursoModulo(Curso curso, Collection<ContenidoCurso> lstModuloCursos) throws Exception;
	void crearPrograma(Programa programa, Collection<ProgramaCurso> lstProgramaCurso) throws Exception;
	void editarPrograma(Programa programa, Collection<ProgramaCurso> lstProgramaCursoActivar, Collection<ProgramaCurso> lstProgramaCursoRemover) throws Exception;
	Collection<Programa> buscarPrograma();
	
	public Collection<ProgramaCurso> buscarCursosAsignadosPrograma(Programa programa);
	public Collection<ProgramaCurso> buscarCursosAsignadosProgramaParaFinalizar(Integer idPrograma);
	public Collection<ProgramaCurso> buscarCursosAsignadosPrograma(Integer IdPrograma, Integer idPersona);
	public Collection<CursoPeriodo> cargarCursoPerdiodoPorAsignar(Integer[] listaIdCursosAsignados);
}

