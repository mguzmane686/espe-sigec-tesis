package org.espe.sigec.servicio.login;

import java.util.Collection;

import org.espe.sigec.model.entities.Modulo;
import org.espe.sigec.model.entities.Usuario;
@QLogin
public interface LoginServicio {
	Usuario validateLogin(String identificador, String clave);
	Collection<Modulo> getMenuByProfile(Usuario usuario);
	
}
