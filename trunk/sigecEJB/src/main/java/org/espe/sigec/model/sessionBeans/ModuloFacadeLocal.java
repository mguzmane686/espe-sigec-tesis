/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.List;
import javax.ejb.Local;
import org.espe.sigec.model.entities.Modulo;

/**
 *
 * @author roberto
 */
@Local
public interface ModuloFacadeLocal {

    void create(Modulo modulo) throws Exception;

    void edit(Modulo modulo) throws Exception;

    void remove(Modulo modulo) throws Exception;

    Modulo find(Object id);

    List<Modulo> findAll();

    List<Modulo> findRange(int[] range);

    int count();
    
}
