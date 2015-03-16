/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.List;
import javax.ejb.Local;
import org.espe.sigec.model.entities.Establecimiento;

/**
 *
 * @author roberto
 */
@Local
public interface LugarCursoFacadeLocal {

    void create(Establecimiento lugarCurso) throws Exception;

    void edit(Establecimiento lugarCurso) throws Exception;

    void remove(Establecimiento lugarCurso) throws Exception;

    Establecimiento find(Object id);

    List<Establecimiento> findAll();

    List<Establecimiento> findRange(int[] range);

    int count();
    
}
