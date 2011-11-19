/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.List;
import javax.ejb.Local;
import org.espe.sigec.model.entities.Persona;

/**
 *
 * @author roberto
 */
@Local
public interface PersonaFacadeLocal {

    void create(Persona persona) throws Exception;

    void edit(Persona persona) throws Exception;

    void remove(Persona persona) throws Exception;

    Persona find(Object id);

    List<Persona> findAll();

    List<Persona> findRange(int[] range);

    int count();
    
}
