package org.espe.sigec.model.sessionBeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.espe.sigec.model.entities.Plantilla;

/**
 * @author Roberto
 * 
 */
@Stateless
public class PlantillaFacade extends AbstractFacade<Plantilla> implements PlantillaFacadeLocal {

	@PersistenceContext(unitName = "prjSigecEJBTestPU")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}

	public PlantillaFacade() {
		super(Plantilla.class);
	}

}
