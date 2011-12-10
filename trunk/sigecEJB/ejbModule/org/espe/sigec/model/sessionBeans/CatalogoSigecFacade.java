/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.espe.sigec.model.entities.CatalogoSigec;

/**
 *
 * @author roberto
 */
@Stateless
public class CatalogoSigecFacade extends AbstractFacade<CatalogoSigec> implements CatalogoSigecFacadeLocal {
    @PersistenceContext(unitName = "baseRamosPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public CatalogoSigecFacade() {
        super(CatalogoSigec.class);
    }
    
}
