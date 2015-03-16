package org.espe.sigec.model.sessionBeans;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

		@Override
		public Collection<UsuarioPerfil> findUsuarioPerfilByUserId(Integer usuarioId) {
			Query query = getEntityManager().createQuery("select a from UsuarioPerfil a " +
					"where a.usuarioPerfilPK.idUsuario =:idUsuario and a.estado =:estado");
			query.setParameter("idUsuario", usuarioId);
			query.setParameter("estado", "1");
			Collection<UsuarioPerfil> usuarioPerfil =  query.getResultList();
			return usuarioPerfil;
		}
}
