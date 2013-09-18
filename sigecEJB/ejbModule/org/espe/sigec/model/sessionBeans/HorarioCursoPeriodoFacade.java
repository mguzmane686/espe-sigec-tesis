package org.espe.sigec.model.sessionBeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.espe.sigec.model.entities.HorarioCursoPeriodo;
@Stateless
public class HorarioCursoPeriodoFacade extends AbstractFacade<HorarioCursoPeriodo> implements HorarioCursoPeriodoFacadeLocal {
	@PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public HorarioCursoPeriodoFacade() {
        super(HorarioCursoPeriodo.class);
    }
}
