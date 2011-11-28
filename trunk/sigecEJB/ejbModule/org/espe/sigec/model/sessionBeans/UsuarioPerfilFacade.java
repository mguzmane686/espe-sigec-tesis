package org.espe.sigec.model.sessionBeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.espe.sigec.model.entities.UsuarioPerfil;
@Stateless
public class UsuarioPerfilFacade extends AbstractFacade<UsuarioPerfil> implements UsuarioPerfilFacadeLocal{
	 @PersistenceContext(unitName = "prjSigecEJBTestPU")
	    private EntityManager em;

	    protected EntityManager getEntityManager() {
	        return em;
	    }

	    public UsuarioPerfilFacade() {
	        super(UsuarioPerfil.class);
	    }
}
