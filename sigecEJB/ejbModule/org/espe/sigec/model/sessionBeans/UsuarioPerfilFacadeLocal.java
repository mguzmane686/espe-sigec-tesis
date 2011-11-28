package org.espe.sigec.model.sessionBeans;

import java.util.List;

import javax.ejb.Local;

import org.espe.sigec.model.entities.UsuarioPerfil;
@Local
public interface UsuarioPerfilFacadeLocal {
	void create(UsuarioPerfil usuario) throws Exception;

	void edit(UsuarioPerfil usuario) throws Exception;
	
	void remove(UsuarioPerfil usuario) throws Exception;
	
	UsuarioPerfil find(Object id);
	
	List<UsuarioPerfil> findAll();
	
	List<UsuarioPerfil> findRange(int[] range);
	
	int count();
}
