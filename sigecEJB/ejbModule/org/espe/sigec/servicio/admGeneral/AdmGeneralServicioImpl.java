package org.espe.sigec.servicio.admGeneral;

import java.util.Collection;

import javax.ejb.EJB;

import org.espe.sigec.model.entities.Aula;
import org.espe.sigec.model.entities.Edificio;
import org.espe.sigec.model.entities.Persona;
import org.espe.sigec.model.entities.Profesor;
import org.espe.sigec.model.entities.Usuario;
import org.espe.sigec.model.entities.UsuarioPerfil;
import org.espe.sigec.model.entities.UsuarioPerfilPK;
import org.espe.sigec.model.sessionBeans.AulaFacadeLocal;
import org.espe.sigec.model.sessionBeans.EdificioFacadeLocal;
import org.espe.sigec.model.sessionBeans.PersonaFacadeLocal;
import org.espe.sigec.model.sessionBeans.ProfesorFacadeLocal;
import org.espe.sigec.model.sessionBeans.UsuarioFacadeLocal;
import org.espe.sigec.model.sessionBeans.UsuarioPerfilFacadeLocal;

public class AdmGeneralServicioImpl implements AdmGeneralServicio{
	@EJB
	private AulaFacadeLocal aulaFacadeLocal;
	
	@EJB 
	private UsuarioFacadeLocal usuarioFacadeLocal;
	@EJB 
	private PersonaFacadeLocal personaFacadeLocal; 
	
	@EJB
	private UsuarioPerfilFacadeLocal usuarioPerfilFacadeLocal;
	@EJB
	private EdificioFacadeLocal edificioFacadeLocal;
	
	@EJB
	private ProfesorFacadeLocal profesorFacadeLocal;
	
	@Override
	public void createAula(Aula aula) throws Exception {
		aulaFacadeLocal.create(aula);
	}
	@Override
	public Collection<Edificio> findEdificio(){
		return edificioFacadeLocal.findAll();
	}
	@Override
	public Collection<Aula> findCursoByEdificio(String idEdificio){
		return aulaFacadeLocal.findCursoByEdificio(idEdificio);
	}
	@Override
	public void createAdministrativo(Usuario usuario, Persona persona) throws Exception {
		usuarioFacadeLocal.isIdentificadorvalida(usuario.getIdentificador());
		usuarioFacadeLocal.create(usuario);
		personaFacadeLocal.create(persona);
		
		UsuarioPerfil usuarioPerfil = new UsuarioPerfil();
		usuarioPerfil.setUsuarioPerfilPK(new UsuarioPerfilPK());
		usuarioPerfil.getUsuarioPerfilPK().setIdPerfil("ADM");
		usuarioPerfil.getUsuarioPerfilPK().setIdUsuario(usuario.getIdUsuario());
		
		usuarioPerfilFacadeLocal.create(usuarioPerfil);
	}
	
	public void createProfesor(Usuario usuario, Persona persona, Profesor profesor) throws Exception{
		usuarioFacadeLocal.isIdentificadorvalida(persona.getCedula());
		usuario.setIdentificador(persona.getCedula());
		usuario.setClave(persona.getCedula());
		usuarioFacadeLocal.create(usuario);
		
		UsuarioPerfil usuarioPerfil = new UsuarioPerfil(new UsuarioPerfilPK());
		usuarioPerfil.getUsuarioPerfilPK().setIdUsuario(usuario.getIdUsuario());
		usuarioPerfil.getUsuarioPerfilPK().setIdPerfil("PRO");
		usuarioPerfil.setEstado("1");
		
		usuarioPerfilFacadeLocal.create(usuarioPerfil);
		
		profesor.getPersona().setUsuario(usuario);
		personaFacadeLocal.create(persona);
		profesorFacadeLocal.create(profesor);
	}
}
