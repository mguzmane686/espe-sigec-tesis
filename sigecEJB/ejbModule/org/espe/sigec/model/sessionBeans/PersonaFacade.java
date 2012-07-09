/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.espe.sigec.model.entities.Persona;
import org.espe.sigec.model.entities.Usuario;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author roberto
 */
@Stateless
public class PersonaFacade extends AbstractFacade<Persona> implements PersonaFacadeLocal {
    @PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaFacade() {
        super(Persona.class);
    }

	@Override
	public Persona findPersonaByUser(Usuario usuario) {
		Criteria criteria = ((Session)getEntityManager().getDelegate()).createCriteria(Persona.class);
		criteria.add(Restrictions.eq("usuario.idUsuario", usuario.getIdUsuario()));
		return (Persona) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Persona> cargarContactos() {
		Criteria criteria = ((Session)getEntityManager().getDelegate()).createCriteria(Persona.class);
		criteria.add(Restrictions.isNotNull("esContacto"));
		criteria.add(Restrictions.eq("esContacto", "1"));
		criteria.addOrder(Order.asc("primerApellido"));
		Collection<Persona> lst =  criteria.list();
		
		return lst;
	}
    
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Persona> cargarUsuarios() {
		Criteria criteria = ((Session)getEntityManager().getDelegate()).createCriteria(Persona.class);
		criteria.setFetchMode("usuario", FetchMode.JOIN);
		
		criteria.add(Restrictions.or( Restrictions.isNull("esContacto"), Restrictions.ne("esContacto", "1") ));
		
		criteria.addOrder(Order.asc("primerApellido"));
		Collection<Persona> lst =  criteria.list();
		
		return lst;
	}

	@Override
	public Collection<Persona> findPersonByCriteria(String criterio, String valor) {
		System.out.println(criterio.concat(valor));
		
		Criteria criteria = ((Session)getEntityManager().getDelegate()).createCriteria(Persona.class);
		criteria.createAlias("usuario", "usrPer");
		criteria.setFetchMode("usrPer", FetchMode.JOIN);
		if(criterio.equals("usr")){
			criteria.add(Restrictions.like("usrPer.identificador", valor));
		}else if(criterio.equals("ced")){
			criteria.add(Restrictions.eq("cedula", valor));
		}else if(criterio.equals("ape")){
			criteria.add(Restrictions.like("primerApellido", valor));
		}
		
		
		Collection<Persona> lstPersonas = criteria.list();
		if(lstPersonas ==null){
			lstPersonas = new ArrayList<Persona>();
		}
		return lstPersonas;
	}
	
}
