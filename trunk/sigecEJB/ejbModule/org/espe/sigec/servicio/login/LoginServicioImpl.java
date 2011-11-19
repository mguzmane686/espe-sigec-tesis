package org.espe.sigec.servicio.login;

import java.util.Collection;

import javax.ejb.EJB;

import org.espe.sigec.model.entities.Modulo;
import org.espe.sigec.model.entities.Usuario;
import org.espe.sigec.model.sessionBeans.ModuloFacadeLocal;
import org.espe.sigec.model.sessionBeans.UsuarioFacadeLocal;


public class LoginServicioImpl implements LoginServicio{

	@EJB
	private UsuarioFacadeLocal usuarioFacadeLocal;
	@EJB
	private ModuloFacadeLocal moduloFacadeLocal;
	
	@Override
	public Usuario validateLogin(String identificador, String clave) {
		return usuarioFacadeLocal.validateLogin(identificador, clave);
	}

	@Override
	public Collection<Modulo> getMenuByProfile(Usuario usuario) {
		
		return usuarioFacadeLocal.getMenuByProfile(usuario);
	}

}
