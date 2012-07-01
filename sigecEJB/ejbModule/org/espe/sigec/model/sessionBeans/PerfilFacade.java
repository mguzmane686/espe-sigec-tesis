/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.espe.sigec.model.entities.Perfil;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author roberto
 */
@Stateless
public class PerfilFacade extends AbstractFacade<Perfil> implements PerfilFacadeLocal {
    @PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public PerfilFacade() {
        super(Perfil.class);
    }

	@Override
	public Collection<Perfil> findPerfiles() {
		Criteria criteria = ((Session)getEntityManager().getDelegate()).createCriteria(Perfil.class);
		Collection<Perfil> lstPerfiles = criteria.list(); 
		return lstPerfiles;
	}
    
}
