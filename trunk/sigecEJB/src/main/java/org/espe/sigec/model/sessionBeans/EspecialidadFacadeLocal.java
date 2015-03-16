/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.List;
import javax.ejb.Local;
import org.espe.sigec.model.entities.Especialidad;

/**
 *
 * @author roberto
 */
@Local
public interface EspecialidadFacadeLocal {

    void create(Especialidad especialidad) throws Exception;

    void edit(Especialidad especialidad) throws Exception;

    void remove(Especialidad especialidad) throws Exception;

    Especialidad find(Object id);

    List<Especialidad> findAll();

    List<Especialidad> findRange(int[] range);

    int count();
    
}
