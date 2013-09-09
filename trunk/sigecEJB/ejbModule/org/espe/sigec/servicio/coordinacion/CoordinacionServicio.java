package org.espe.sigec.servicio.coordinacion;

import java.math.BigDecimal;
import java.util.Collection;

import org.espe.sigec.model.entities.Aula;
import org.espe.sigec.model.entities.Curso;
import org.espe.sigec.model.entities.CursoEstudiante;
import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.Edificio;
import org.espe.sigec.model.entities.Especialidad;
import org.espe.sigec.model.entities.Establecimiento;
import org.espe.sigec.model.entities.InvitacionDocente;
import org.espe.sigec.model.entities.PeriodoAcademico;

/**
 * @author roberto
 *
 */
@QCoordinacion
public interface CoordinacionServicio {
	Collection<Especialidad> findEspecialidades();
	Collection<Curso> findCursoByEspecialidad(Integer idEspecialidad);
	void abrirCurso(PeriodoAcademico periodoAcademico, CursoPeriodo cursoPeriodo) throws Exception;
	Collection<Aula> findAulas(); 
	Collection<Establecimiento> findLugarCurso();
	Collection<Edificio> findEdificioByLugarCurso(String idLugar);
	Collection<Aula> findAulaByEdificio(String idEdificio);
	void administrarCurso(CursoPeriodo cursoPeriodo) throws Exception;
	int numeroEstudiantesInscritos(BigDecimal idCursoPeriodo);
	Collection<CursoEstudiante> estudiantesInscritosCurso(BigDecimal idCursoPeriodo);
	Collection<InvitacionDocente> findProfesoresSeleccionados(BigDecimal idCursoPeriodo);
	public void actualizarCursoEstudiante(CursoEstudiante cursoEstudiante) throws Exception;
}
