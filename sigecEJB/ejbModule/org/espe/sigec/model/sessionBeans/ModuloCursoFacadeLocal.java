/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.Collection;
import java.util.List;
import javax.ejb.Local;
import org.espe.sigec.model.entities.ModuloCurso;

/**
 *
 * @author Roberto
 */
@Local
public interface ModuloCursoFacadeLocal {

    void create(ModuloCurso moduloCurso) throws Exception;

    void edit(ModuloCurso moduloCurso) throws Exception;

    void remove(ModuloCurso moduloCurso) throws Exception;

    ModuloCurso find(Object id);

    List<ModuloCurso> findAll();

    List<ModuloCurso> findRange(int[] range);
    
    int count();
    
    Collection<ModuloCurso> findModulosCurso(Integer idCurso);
}
