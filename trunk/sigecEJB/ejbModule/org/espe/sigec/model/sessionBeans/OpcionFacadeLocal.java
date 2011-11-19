/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.List;
import javax.ejb.Local;
import org.espe.sigec.model.entities.Opcion;

/**
 *
 * @author roberto
 */
@Local
public interface OpcionFacadeLocal {

    void create(Opcion opcion) throws Exception;

    void edit(Opcion opcion) throws Exception;

    void remove(Opcion opcion) throws Exception;

    Opcion find(Object id);

    List<Opcion> findAll();

    List<Opcion> findRange(int[] range);

    int count();
    
}
