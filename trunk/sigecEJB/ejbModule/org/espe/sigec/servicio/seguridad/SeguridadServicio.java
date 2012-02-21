package org.espe.sigec.servicio.seguridad;

import java.util.Collection;

import org.espe.sigec.model.entities.Modulo;
import org.espe.sigec.model.entities.Perfil;
import org.espe.sigec.model.entities.Usuario;
import org.espe.sigec.model.entities.UsuarioPerfil;
@QSeguridad
public interface SeguridadServicio {
	Usuario validateLogin(String identificador, String clave);
	Collection<Modulo> getMenuByProfile(Usuario usuario);
	UsuarioPerfil getUsuarioPerfil(Usuario usuario);
	void crearUsuario(UsuarioPerfil usuarioPerfil) throws Exception;
	Collection<Perfil> findPerfiles();
}
