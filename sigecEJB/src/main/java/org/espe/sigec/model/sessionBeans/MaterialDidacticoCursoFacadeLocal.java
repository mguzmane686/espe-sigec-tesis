package org.espe.sigec.model.sessionBeans;

import java.util.List;

import javax.ejb.Local;

import org.espe.sigec.model.entities.MaterialDidacticoCurso;
@Local
public interface MaterialDidacticoCursoFacadeLocal {
	void create(MaterialDidacticoCurso materialDidacticoCurso) throws Exception;

    void edit(MaterialDidacticoCurso materialDidacticoCurso) throws Exception;

    void remove(MaterialDidacticoCurso materialDidacticoCurso) throws Exception;

    MaterialDidacticoCurso find(Object id);

    List<MaterialDidacticoCurso> findAll();

    List<MaterialDidacticoCurso> findRange(int[] range);

    int count();

}
