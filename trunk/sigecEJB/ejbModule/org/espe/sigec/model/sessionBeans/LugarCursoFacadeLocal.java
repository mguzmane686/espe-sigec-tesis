/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.List;
import javax.ejb.Local;
import org.espe.sigec.model.entities.LugarCurso;

/**
 *
 * @author roberto
 */
@Local
public interface LugarCursoFacadeLocal {

    void create(LugarCurso lugarCurso) throws Exception;

    void edit(LugarCurso lugarCurso) throws Exception;

    void remove(LugarCurso lugarCurso) throws Exception;

    LugarCurso find(Object id);

    List<LugarCurso> findAll();

    List<LugarCurso> findRange(int[] range);

    int count();
    
}
