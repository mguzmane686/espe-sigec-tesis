/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.List;
import javax.ejb.Local;
import org.espe.sigec.model.entities.DesempenioProfesor;

/**
 *
 * @author roberto
 */
@Local
public interface DesempenioProfesorFacadeLocal {

    void create(DesempenioProfesor desempenioProfesor) throws Exception;

    void edit(DesempenioProfesor desempenioProfesor) throws Exception;

    void remove(DesempenioProfesor desempenioProfesor) throws Exception;

    DesempenioProfesor find(Object id);

    List<DesempenioProfesor> findAll();

    List<DesempenioProfesor> findRange(int[] range);

    int count();
    
}
