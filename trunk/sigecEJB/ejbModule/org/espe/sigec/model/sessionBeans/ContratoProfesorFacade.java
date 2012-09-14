package org.espe.sigec.model.sessionBeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.espe.sigec.model.entities.ContratoProfesor;

@Stateless
public class ContratoProfesorFacade extends AbstractFacade<ContratoProfesor> implements ContratoProfesorFacadeLocal{
	@PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ContratoProfesorFacade() {
        super(ContratoProfesor.class);
    }
}
