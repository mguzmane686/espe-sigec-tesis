/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.Collection;
import java.util.List;
import javax.ejb.Local;
import org.espe.sigec.model.entities.Curso;

/**
 *
 * @author roberto
 */
@Local
public interface CursoFacadeLocal {

    void create(Curso curso) throws Exception;

    void edit(Curso curso) throws Exception;

    void remove(Curso curso) throws Exception;

    Curso find(Object id);

    List<Curso> findAll();

    List<Curso> findRange(int[] range);

    int count();
    
    Collection<Curso> findCursoByEspecialidad(Integer idEspecialidad);
    
    Collection<Curso> findCursoByEstado();
}
