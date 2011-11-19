/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.List;
import javax.ejb.Local;
import org.espe.sigec.model.entities.MaterialDidactico;

/**
 *
 * @author roberto
 */
@Local
public interface MaterialDidacticoFacadeLocal {

    void create(MaterialDidactico materialDidactico) throws Exception;

    void edit(MaterialDidactico materialDidactico) throws Exception;

    void remove(MaterialDidactico materialDidactico) throws Exception;

    MaterialDidactico find(Object id);

    List<MaterialDidactico> findAll();

    List<MaterialDidactico> findRange(int[] range);

    int count();
    
}
