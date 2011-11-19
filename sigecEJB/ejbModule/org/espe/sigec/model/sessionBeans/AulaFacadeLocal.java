/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.List;
import javax.ejb.Local;
import org.espe.sigec.model.entities.Aula;

/**
 *
 * @author roberto
 */
@Local
public interface AulaFacadeLocal {

    void create(Aula aula) throws Exception;

    void edit(Aula aula) throws Exception;

    void remove(Aula aula) throws Exception;

    Aula find(Object id);

    List<Aula> findAll();

    List<Aula> findRange(int[] range);

    int count();
    
}
