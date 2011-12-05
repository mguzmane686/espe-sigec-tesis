/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.espe.sigec.model.entities.Estudiante;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author roberto
 */
@Stateless
public class EstudianteFacade extends AbstractFacade<Estudiante> implements EstudianteFacadeLocal {
    @PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudianteFacade() {
        super(Estudiante.class);
    }

	@Override
	public Estudiante buscarEstudinateByCedula(String cedula) {
		Criteria criteria = ((Session)getEntityManager().getDelegate()).createCriteria(Estudiante.class);
		criteria.createAlias("persona", "persona_al");
		criteria.add(Restrictions.eq("persona_al.cedula", cedula));
		return (Estudiante) criteria.uniqueResult();
	}
    
}
