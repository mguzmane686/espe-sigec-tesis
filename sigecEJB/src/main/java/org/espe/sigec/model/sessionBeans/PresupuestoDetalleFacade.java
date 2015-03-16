/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.espe.sigec.model.entities.PresupuestoDetalle;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author roberto
 */
@Stateless
public class PresupuestoDetalleFacade extends AbstractFacade<PresupuestoDetalle> implements PresupuestoDetalleFacadeLocal {
    @PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public PresupuestoDetalleFacade() {
        super(PresupuestoDetalle.class);
    }

	@Override
	public Collection<PresupuestoDetalle> findDetallesByRestrictionIN(
			Collection<String> idCuentas) {
		Criteria crit = ((Session)getEntityManager().getDelegate()).createCriteria(PresupuestoDetalle.class);
    	crit.add(Restrictions.in("presupuestoDetallePK.idCuenta", idCuentas));
    	crit.add(Restrictions.eq("presupuestoDetallePK.preId", 5));
    	Collection<PresupuestoDetalle> lst = crit.list();
		return lst;
	}
}
