/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.List;
import javax.ejb.Local;
import org.espe.sigec.model.entities.Estudiante;

/**
 *
 * @author roberto
 */
@Local
public interface EstudianteFacadeLocal {

    void create(Estudiante estudiante) throws Exception;

    void edit(Estudiante estudiante) throws Exception;

    void remove(Estudiante estudiante) throws Exception;

    Estudiante find(Object id);

    List<Estudiante> findAll();

    List<Estudiante> findRange(int[] range);

    int count();
    
}
