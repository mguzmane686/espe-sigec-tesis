/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.List;
import javax.ejb.Local;
import org.espe.sigec.model.entities.CatalogoSigec;

/**
 *
 * @author roberto
 */
@Local
public interface CatalogoSigecFacadeLocal {

    void create(CatalogoSigec catalogoSigec) throws Exception;

    void edit(CatalogoSigec catalogoSigec) throws Exception;

    void remove(CatalogoSigec catalogoSigec) throws Exception;

    CatalogoSigec find(Object id);

    List<CatalogoSigec> findAll();

    List<CatalogoSigec> findRange(int[] range);

    int count();
    
}
