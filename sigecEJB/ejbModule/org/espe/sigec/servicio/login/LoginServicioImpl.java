package org.espe.sigec.servicio.login;

import java.util.Collection;

import javax.ejb.EJB;

import org.espe.sigec.model.entities.Modulo;
import org.espe.sigec.model.entities.Usuario;
import org.espe.sigec.model.entities.UsuarioPerfil;
import org.espe.sigec.model.entities.UsuarioPerfilPK;
import org.espe.sigec.model.sessionBeans.ModuloFacadeLocal;
import org.espe.sigec.model.sessionBeans.PersonaFacadeLocal;
import org.espe.sigec.model.sessionBeans.UsuarioFacadeLocal;
import org.espe.sigec.model.sessionBeans.UsuarioPerfilFacadeLocal;


public class LoginServicioImpl implements LoginServicio{

	@EJB
	private UsuarioFacadeLocal usuarioFacadeLocal;
	@SuppressWarnings("unused")
	@EJB
	private ModuloFacadeLocal moduloFacadeLocal;
	@EJB
	private UsuarioPerfilFacadeLocal usuarioPerfilFacadeLocal;
	@EJB
	private PersonaFacadeLocal personaFacadeLocal;
	
	@Override
	public Usuario validateLogin(String identificador, String clave) {
		return usuarioFacadeLocal.validateLogin(identificador, clave);
	}

	@Override
	public Collection<Modulo> getMenuByProfile(Usuario usuario) {
		
		return usuarioFacadeLocal.getMenuByProfile(usuario);
	}

	@Override
	public UsuarioPerfil getUsuarioPerfil(Usuario usuario) {
		UsuarioPerfil id = new UsuarioPerfil(new UsuarioPerfilPK());
		id.getUsuarioPerfilPK().setIdUsuario(usuario.getIdUsuario());
		UsuarioPerfil usuarioPerfil = usuarioPerfilFacadeLocal.findUsuarioPerfilByUserId(usuario);
		usuarioPerfil.setPersona(personaFacadeLocal.findPersonaByUser(usuario));
		return usuarioPerfil;
	}

}
