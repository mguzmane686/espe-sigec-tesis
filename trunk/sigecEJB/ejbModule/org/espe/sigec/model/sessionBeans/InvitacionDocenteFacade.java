package org.espe.sigec.model.sessionBeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.espe.sigec.model.entities.InvitacionDocente;
/**
 * @author Roberto
 *
 */
@Stateless
public class InvitacionDocenteFacade extends AbstractFacade<InvitacionDocente> implements InvitacionDocenteFacadeLocal {
    @PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public InvitacionDocenteFacade() {
        super(InvitacionDocente.class);
    }
}
