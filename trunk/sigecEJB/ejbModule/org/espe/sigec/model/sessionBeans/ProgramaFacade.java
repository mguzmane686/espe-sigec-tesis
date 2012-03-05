package org.espe.sigec.model.sessionBeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.espe.sigec.model.entities.Programa;
@Stateless
public class ProgramaFacade extends AbstractFacade<Programa>  implements ProgramaFacadeLocal{
	
	@PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ProgramaFacade() {
        super(Programa.class);
    }
}
