/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.espe.sigec.model.entities.Modulo;
import org.espe.sigec.model.entities.Usuario;
import org.espe.sigec.model.entities.UsuarioPerfil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author roberto
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {
    @PersistenceContext(unitName = "prjSigecEJBTestPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

	@Override
	public Usuario validateLogin(String identificador, String clave) {
		Usuario usuario = null;
		
		Criteria crit = null;
    	crit = ((Session)getEntityManager().getDelegate()).createCriteria(Usuario.class);
    	crit.add(Restrictions.eq("identificador", identificador)).add(Restrictions.eq("clave", clave));
    	usuario = (Usuario) crit.uniqueResult();
    	
		return usuario;
	}
	@Override
	public Collection<Modulo> getMenuByProfile(Usuario usuario) {
		Criteria crit = null;
    	crit = ((Session)getEntityManager().getDelegate()).createCriteria(UsuarioPerfil.class);
    	crit.add(Restrictions.eq("idUsuario", usuario.getIdUsuario()));
    	UsuarioPerfil usuarioPerfil = (UsuarioPerfil) crit.uniqueResult();
    	
    	return usuarioPerfil.getModuloCollection();
	}
    
}
