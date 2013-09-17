package org.espe.sigec.servicio.coordinacion;

import java.math.BigDecimal;
import java.util.Collection;

import org.espe.sigec.exception.SigecException;
import org.espe.sigec.model.entities.Aula;
import org.espe.sigec.model.entities.Curso;
import org.espe.sigec.model.entities.CursoEstudiante;
import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.Edificio;
import org.espe.sigec.model.entities.Especialidad;
import org.espe.sigec.model.entities.Establecimiento;
import org.espe.sigec.model.entities.InvitacionDocente;
import org.espe.sigec.model.entities.MaterialDidacticoCatalogo;
import org.espe.sigec.model.entities.MaterialDidacticoCurso;
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
	Collection<CursoEstudiante> estudiantesInscritosCurso(BigDecimal idCursoPeriodo, String estadoCupo);
	Collection<CursoEstudiante> estudiantesInscritosCurso(BigDecimal idCursoPeriodo, String estadoCupo, String estadoPago);
	Collection<InvitacionDocente> findProfesoresSeleccionados(BigDecimal idCursoPeriodo);
	void actualizarCursoEstudiante(CursoEstudiante cursoEstudiante) throws Exception;
	Collection<MaterialDidacticoCatalogo> findMaterialDidacticoCatalogos(Integer[] listaIdCursosAsignados, String estado) throws Exception;
	Collection<MaterialDidacticoCurso> findMaterialDidacticoAsignado(BigDecimal idCursoPeriodo) throws Exception ;
	/**
     * Permite actualizar la lista de materialies didacticos asignados al curso
     * @param lstMaterialDidacticoOriginal
     * @param lstMaterialDidacticoModificada
     * @throws Exception
     */
	void actualizarListaMaterialesCurso(Collection<MaterialDidacticoCurso> lstMaterialDidacticoOriginal, Collection<MaterialDidacticoCurso> lstMaterialDidacticoModificada) throws SigecException ,Exception;
	InvitacionDocente verificarUltimaInivtacionDocente(BigDecimal idCursoPeriodo, Integer idProfesor) throws Exception;
}
