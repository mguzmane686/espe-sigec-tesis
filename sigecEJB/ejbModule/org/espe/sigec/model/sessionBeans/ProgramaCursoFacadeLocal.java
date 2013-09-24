package org.espe.sigec.model.sessionBeans;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import org.espe.sigec.model.entities.Programa;
import org.espe.sigec.model.entities.ProgramaCurso;

/**
 * @author roberto
 *
 */
@Local
public interface ProgramaCursoFacadeLocal {
	void create(ProgramaCurso programaCurso) throws Exception;

    void edit(ProgramaCurso programaCurso) throws Exception;

    void remove(ProgramaCurso programaCurso) throws Exception;

    ProgramaCurso find(Object id);

    List<ProgramaCurso> findAll();

    List<ProgramaCurso> findRange(int[] range);

    int count();
    
    List<ProgramaCurso> cargarProgramaPortal();
    Collection<ProgramaCurso> buscarCursosAsignadosPrograma(Programa programa);
    Collection<ProgramaCurso> buscarCursosAsignadosPrograma(Integer idPrograma, Integer idPersona);
    Collection<ProgramaCurso> buscarCursosAsignadosProgramaParaFinalizar(Integer idPrograma);
}
