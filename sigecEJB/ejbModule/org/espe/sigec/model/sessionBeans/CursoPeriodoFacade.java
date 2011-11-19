/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.espe.sigec.model.entities.CursoPeriodo;

/**
 *
 * @author roberto
 */
@Stateless
public class CursoPeriodoFacade extends AbstractFacade<CursoPeriodo> implements CursoPeriodoFacadeLocal {
    @PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public CursoPeriodoFacade() {
        super(CursoPeriodo.class);
    }
    
}
