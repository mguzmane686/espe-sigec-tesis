package org.espe.sigec.servicio.seguridad;

import java.util.Collection;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;

import org.espe.sigec.model.entities.Estudiante;
import org.espe.sigec.model.entities.Modulo;
import org.espe.sigec.model.entities.Perfil;
import org.espe.sigec.model.entities.Persona;
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
	public Collection<UsuarioPerfil> getUsuarioPerfil(Usuario usuario) {
		UsuarioPerfil id = new UsuarioPerfil(new UsuarioPerfilPK());
		id.getUsuarioPerfilPK().setIdUsuario(usuario.getIdUsuario());
		Collection<UsuarioPerfil> lstUsuarioPerfil = usuarioPerfilFacadeLocal.findUsuarioPerfilByUserId(usuario.getIdUsuario());
		
		Persona persona = personaFacadeLocal.findPersonaByUser(usuario);
		for(UsuarioPerfil usuarioPerfil: lstUsuarioPerfil){
			usuarioPerfil.setPersona(persona);
		}
		
//		usuarioPerfil.setPersona(personaFacadeLocal.findPersonaByUser(usuario));
		return lstUsuarioPerfil;
	}

	private void elimiarEspaciosPersona(Persona persona){
		if(persona.getUsuario().getIdentificador()!=null){
			persona.getUsuario().setIdentificador(persona.getUsuario().getIdentificador().trim());
		}
		
		if(persona.getUsuario().getClave()!=null){
			persona.getUsuario().setClave(persona.getUsuario().getClave().trim());
		}
		
		if(persona.getPrimerNombre()!=null){
			persona.setPrimerNombre(persona.getPrimerNombre().trim());
		}
		
		if(persona.getPrimerApellido()!=null){
			persona.setPrimerApellido(persona.getPrimerApellido().trim());
		}
				
		if(persona.getSegundoNombre()!=null){
			persona.setSegundoNombre(persona.getSegundoNombre().trim());
		}
		
		if(persona.getSegundoApellido()!=null){
			persona.setSegundoApellido(persona.getSegundoApellido().trim());
		}
		
		if(persona.getMail()!=null){
			persona.setMail(persona.getMail().trim());
		}
		
		if(persona.getDireccion()!=null){
			persona.setDireccion(persona.getDireccion().trim());
		}
		
		if(persona.getNacionalidad()!=null){
			persona.setNacionalidad(persona.getNacionalidad().trim());
		}
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
				elimiarEspaciosPersona(usuarioPerfil.getPersona());
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
			throw new Exception("Ha ocurrido un error al crear el usuario", e);
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

	@Override
	public void actualizarUsuarioPerfil(Persona persona, Collection<Perfil> lstPerfiles) throws Exception {
		userTransaction.begin();
		try {
			personaFacadeLocal.edit(persona);
			usuarioFacadeLocal.edit(persona.getUsuario());
			for (Perfil perfil : lstPerfiles) {
				UsuarioPerfil usuarioPerfilTMP = new UsuarioPerfil();
				usuarioPerfilTMP.setEstado("1");
				usuarioPerfilTMP.setPerfil(perfil);
				
				usuarioPerfilTMP.setUsuarioPerfilPK(new UsuarioPerfilPK(persona.getUsuario().getIdUsuario(), perfil.getIdPerfil()));
				
				
				if(perfil.isSelected()){
					UsuarioPerfil tmpUsr = usuarioPerfilFacadeLocal.find(new UsuarioPerfilPK(persona.getUsuario().getIdUsuario(), perfil.getIdPerfil()));
					if(tmpUsr!=null){
						perfil.setExistInBase(Boolean.TRUE);
					}else{
						perfil.setExistInBase(Boolean.FALSE);
					}
					
					if(perfil.isExistInBase()){
						
						usuarioPerfilFacadeLocal.edit(usuarioPerfilTMP);
					}else{
						usuarioPerfilFacadeLocal.create(usuarioPerfilTMP);
						if(perfil.getIdPerfil().equals("EST")){
							Estudiante estudiante = new Estudiante();
							estudiante.setPersona(persona);
							estudianteFacadeLocal.create(estudiante);
						}else if(perfil.getIdPerfil().equals("PRO")){
							Profesor profesor = new Profesor();
							profesor.setPersona(persona);
							profesorFacadeLocal.create(profesor);
						}
					}
				}else{
					usuarioPerfilTMP.setEstado("0");
					usuarioPerfilFacadeLocal.edit(usuarioPerfilTMP);
				}
			}
			
			userTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			userTransaction.rollback();
			throw new Exception("Ha ocurrido un error al crear el usuario", e);
		}
	}
	
	

}
