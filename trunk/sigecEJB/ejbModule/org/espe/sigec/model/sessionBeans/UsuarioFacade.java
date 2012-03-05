/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.sessionBeans;

import java.util.Collection;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.espe.sigec.exception.UserValidateException;
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
    @PersistenceContext(unitName = "prjSigecEJBTestPU", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    
    @SuppressWarnings("unused")
	@Resource
    private SessionContext sessionContext;
    
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

	@Override
	public boolean isIdentificadorvalida(String identificacdor) throws UserValidateException{
		Criteria criteria = ((Session)getEntityManager().getDelegate()).createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("identificador", identificacdor));
		
		@SuppressWarnings("unchecked")
		Collection<Usuario> lstUsuarios = criteria.list();
		boolean existUser = Boolean.FALSE;
		
		if(lstUsuarios != null && lstUsuarios.size()>0){
			existUser = Boolean.FALSE;
			throw new UserValidateException("El identificador ya existe");
		}else{
			existUser = Boolean.TRUE;
		}
		return existUser;
	}
    
}
