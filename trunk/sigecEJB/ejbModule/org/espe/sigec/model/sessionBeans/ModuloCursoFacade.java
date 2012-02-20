/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.espe.sigec.model.entities.ModuloCurso;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Roberto
 */
@Stateless
public class ModuloCursoFacade extends AbstractFacade<ModuloCurso> implements ModuloCursoFacadeLocal {
    @PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModuloCursoFacade() {
        super(ModuloCurso.class);
    }
    @SuppressWarnings("unchecked")
	@Override
    public Collection<ModuloCurso> findModulosCurso(Integer idCurso){
    	Criteria crit = null;
    	crit = ((Session)getEntityManager().getDelegate()).createCriteria(ModuloCurso.class);
    	crit.add(Restrictions.eq("curso.idCurso", idCurso));
    	return crit.list();
    }
}
