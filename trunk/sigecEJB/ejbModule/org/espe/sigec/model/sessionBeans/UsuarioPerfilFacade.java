package org.espe.sigec.model.sessionBeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.espe.sigec.model.entities.Usuario;
import org.espe.sigec.model.entities.UsuarioPerfil;
import org.espe.sigec.model.entities.UsuarioPerfilPK;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
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
		public UsuarioPerfil findUsuarioPerfilByUserId(Usuario usuario) {
			Query query = getEntityManager().createQuery("select a from UsuarioPerfil a " +
					"where a.usuarioPerfilPK.idUsuario =:idUsuario");
			query.setParameter("idUsuario", usuario.getIdUsuario());
			UsuarioPerfil usuarioPerfil = (UsuarioPerfil) query.getSingleResult();
			return usuarioPerfil;
		}
}
