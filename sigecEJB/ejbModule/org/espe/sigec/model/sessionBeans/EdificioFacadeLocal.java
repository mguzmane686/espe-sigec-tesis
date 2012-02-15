/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.Collection;
import java.util.List;
import javax.ejb.Local;
import org.espe.sigec.model.entities.Edificio;

/**
 *
 * @author roberto
 */
@Local
public interface EdificioFacadeLocal {

    void create(Edificio edificio) throws Exception;

    void edit(Edificio edificio) throws Exception;

    void remove(Edificio edificio) throws Exception;

    Edificio find(Object id);

    List<Edificio> findAll();

    List<Edificio> findRange(int[] range);

    int count();
    
    Collection<Edificio> findEdificioByLugarCurso(String idLugar);

	Collection<Edificio> findEdificiosReporte();
    
}
