package org.espe.sigec.servicio.coordinacion;

import java.util.Collection;

import org.espe.sigec.model.entities.Aula;
import org.espe.sigec.model.entities.Curso;
import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.Edificio;
import org.espe.sigec.model.entities.Especialidad;
import org.espe.sigec.model.entities.LugarCurso;
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
	Collection<LugarCurso> findLugarCurso();
	Collection<Edificio> findEdificioByLugarCurso(String idLugar);
	Collection<Aula> findAulaByEdificio(String idEdificio);
}
