package org.espe.sigec.servicio.seguridad;

import java.util.Collection;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;

import org.espe.sigec.model.entities.Estudiante;
import org.espe.sigec.model.entities.Modulo;
import org.espe.sigec.model.entities.Perfil;
import org.espe.sigec.model.entities.Profesor;
import org.espe.sigec.model.entities.Usuario;
import org.espe.sigec.model.entities.UsuarioPerfil;
import org.espe.sigec.model.entities.UsuarioPerfilPK;
import org.espe.sigec.model.sessionBeans.EstudianteFacadeLocal;
import org.espe.sigec.model.sessionBeans.ModuloFacadeLocal;
import org.espe.sigec.model.sessionBeans.PerfilFacadeLocal;
import org.espe.sigec.model.sessionBeans.PersonaFacadeLocal;
import org.espe.sigec.model.sessionBeans.ProfesorFacadeLocal;
import org.espe.sigec.model.sessionBeans.UsuarioFacadeLocal;
import org.espe.sigec.model.sessionBeans.UsuarioPerfilFacadeLocal;


public class SeguridadServicioImpl implements SeguridadServicio{

	@EJB
	private UsuarioFacadeLocal usuarioFacadeLocal;
	@SuppressWarnings("unused")
	@EJB
	private ModuloFacadeLocal moduloFacadeLocal;
	@EJB
	private UsuarioPerfilFacadeLocal usuarioPerfilFacadeLocal;
	@EJB
	private PersonaFacadeLocal personaFacadeLocal;
	@EJB
	private EstudianteFacadeLocal estudianteFacadeLocal;
	@EJB
	private ProfesorFacadeLocal profesorFacadeLocal;
	@EJB
	private PerfilFacadeLocal perfilFacadeLocal;
	@Resource
	private UserTransaction userTransaction;
	
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
		UsuarioPerfil usuarioPerfil = usuarioPerfilFacadeLocal.findUsuarioPerfilByUserId(usuario.getIdUsuario()).iterator().next();
		usuarioPerfil.setPersona(personaFacadeLocal.findPersonaByUser(usuario));
		return usuarioPerfil;
	}

	@Override
	public void crearUsuario(UsuarioPerfil usuarioPerfil) throws Exception{
		userTransaction.begin();
		try {
			if(usuarioFacadeLocal.isIdentificadorvalida(usuarioPerfil.getUsuario().getIdentificador())){
				usuarioPerfil.getUsuario().setEstadoUsr("1");
				//se crea el usuario
				usuarioFacadeLocal.create(usuarioPerfil.getUsuario());
				usuarioPerfil.getPersona().setUsuario(usuarioPerfil.getUsuario());
				//se crea la persona
				personaFacadeLocal.create(usuarioPerfil.getPersona());
				usuarioPerfil.getUsuarioPerfilPK().setIdUsuario(usuarioPerfil.getUsuario().getIdUsuario());
				for (Perfil perfil : usuarioPerfil.getLstPerfils()) {
					UsuarioPerfil usuarioPerfilTMP = new UsuarioPerfil();
					usuarioPerfilTMP.setEstado("1");
					usuarioPerfilTMP.setPerfil(perfil);
					
					usuarioPerfilTMP.setUsuarioPerfilPK(new UsuarioPerfilPK(usuarioPerfil.getUsuario().getIdUsuario(), perfil.getIdPerfil()));
					usuarioPerfilFacadeLocal.create(usuarioPerfilTMP);
					
					if(perfil.getIdPerfil().equals("EST")){
						Estudiante estudiante = new Estudiante();
						estudiante.setPersona(usuarioPerfil.getPersona());
						estudianteFacadeLocal.create(estudiante);
					}else if(perfil.getIdPerfil().equals("PRO")){
						Profesor profesor = new Profesor();
						profesor.setPersona(usuarioPerfil.getPersona());
						profesorFacadeLocal.create(profesor);
					}
				}
				
				
				
			}
			userTransaction.commit();
		} catch (Exception e) {
			userTransaction.rollback();
			new Exception("Ha ocurrido un error al crear el usuario", e);
		}
	}

	@Override
	public Collection<Perfil> findPerfiles() {
		return perfilFacadeLocal.findPerfiles();
	}

	@Override
	public Collection<UsuarioPerfil> findPerfilesUsuario(Integer idUsuario) throws Exception {
		return usuarioPerfilFacadeLocal.findUsuarioPerfilByUserId(idUsuario);
		
	}
	
	

}
