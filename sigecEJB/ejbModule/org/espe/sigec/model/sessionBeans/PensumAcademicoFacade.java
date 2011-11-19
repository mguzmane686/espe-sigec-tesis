/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.espe.sigec.model.entities.PensumAcademico;

/**
 *
 * @author roberto
 */
@Stateless
public class PensumAcademicoFacade extends AbstractFacade<PensumAcademico> implements PensumAcademicoFacadeLocal {
    @PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public PensumAcademicoFacade() {
        super(PensumAcademico.class);
    }
    
}
