package org.espe.sigec.servicio.admGeneral;

import java.util.Collection;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;

import org.espe.sigec.exception.UserValidateException;
import org.espe.sigec.model.entities.Aula;
import org.espe.sigec.model.entities.Edificio;
import org.espe.sigec.model.entities.Especialidad;
import org.espe.sigec.model.entities.LugarCurso;
import org.espe.sigec.model.entities.Persona;
import org.espe.sigec.model.entities.Presupuesto;
import org.espe.sigec.model.entities.Profesor;
import org.espe.sigec.model.entities.Usuario;
import org.espe.sigec.model.entities.UsuarioPerfil;
import org.espe.sigec.model.entities.UsuarioPerfilPK;
import org.espe.sigec.model.sessionBeans.AulaFacadeLocal;
import org.espe.sigec.model.sessionBeans.EdificioFacadeLocal;
import org.espe.sigec.model.sessionBeans.EspecialidadFacadeLocal;
import org.espe.sigec.model.sessionBeans.LugarCursoFacadeLocal;
import org.espe.sigec.model.sessionBeans.PersonaFacadeLocal;
import org.espe.sigec.model.sessionBeans.PresupuestoFacadeLocal;
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
	@EJB
	private LugarCursoFacadeLocal lugarCursoFacadeLocal;
	@Resource
    private UserTransaction userTransaction;
	@EJB
	private EspecialidadFacadeLocal especialidadFacadeLocal;
	@EJB
	private PresupuestoFacadeLocal presupuestoFacadeLocal;
	
	@Override
	public void editAula(Aula aula) throws Exception {
		aulaFacadeLocal.edit(aula);
	}
	
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
	public void createAdministrativo(Usuario usuario, Persona persona) throws Exception, UserValidateException {
		userTransaction.begin();
		try {
			
		
		usuarioFacadeLocal.isIdentificadorvalida(usuario.getIdentificador());
		usuarioFacadeLocal.create(usuario);
		personaFacadeLocal.create(persona);
		
		UsuarioPerfil usuarioPerfil = new UsuarioPerfil();
		usuarioPerfil.setUsuarioPerfilPK(new UsuarioPerfilPK());
		usuarioPerfil.getUsuarioPerfilPK().setIdPerfil("ADM");
		usuarioPerfil.getUsuarioPerfilPK().setIdUsuario(usuario.getIdUsuario());
		
		usuarioPerfilFacadeLocal.create(usuarioPerfil);
		userTransaction.commit();
		} catch (Exception e) {
			userTransaction.rollback();
			if(e.getMessage().contains("El identificador ya existe")){
				throw new UserValidateException(e.getMessage());
			}else{
				throw new Exception(e.getMessage());
			}
			
		}
	}
	
	public void createProfesor(Usuario usuario, Persona persona, Profesor profesor) throws Exception{
		userTransaction.begin();
		try {
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
			userTransaction.commit();
		} catch (Exception e) {
			userTransaction.rollback();
		}
	}
	
	public void editProfesor(Usuario usuario, Persona persona, Profesor profesor) throws Exception{
		userTransaction.begin();
		try {
			personaFacadeLocal.edit(persona);
			profesorFacadeLocal.edit(profesor);
			userTransaction.commit();
		} catch (Exception e) {
			userTransaction.rollback();
		}
	}
	@Override
	public void createEdificio(Edificio edificio) throws Exception {
		edificioFacadeLocal.create(edificio);
	}
	
	@Override
	public Collection<LugarCurso> findLugar() {
		return lugarCursoFacadeLocal.findAll();
	}
	
	@Override
	public Collection<Edificio> findEdificioByLugar(String idLugarCurso) {
		return edificioFacadeLocal.findEdificioByLugarCurso(idLugarCurso);
	}
	
	@Override
	public void editEdificio(Edificio edificio) throws Exception {
		edificioFacadeLocal.edit(edificio);
	}
	
	@Override
	public Collection<Aula> findAula() {
		return aulaFacadeLocal.findAulas();
	}

	@Override
	public void createLugar(LugarCurso lugarCurso) throws Exception {
		lugarCursoFacadeLocal.create(lugarCurso);
	}

	@Override
	public void editLugar(LugarCurso lugarCurso) throws Exception {
		lugarCursoFacadeLocal.edit(lugarCurso);
	}

	@Override
	public Collection<Edificio> findEdificioReporte() {
		return edificioFacadeLocal.findEdificiosReporte();
	}

	@Override
	public Collection<Especialidad> findEspecialidad() {
		return especialidadFacadeLocal.findAll();
	}

	@Override
	public void createEspecialidad(Especialidad especialidad) throws Exception {
		especialidadFacadeLocal.create(especialidad);
	}

	@Override
	public void editEspecialidad(Especialidad especialidad) throws Exception {
		especialidadFacadeLocal.edit(especialidad);
	}

	@Override
	public Collection<Presupuesto> findPresupuesto() {
		return presupuestoFacadeLocal.findAll(); 
	}

	@Override
	public void createPresupuesto(Presupuesto presupuesto) throws Exception {
		presupuestoFacadeLocal.create(presupuesto);
	}

	@Override
	public void editPresupuesto(Presupuesto presupuesto) throws Exception {
		presupuestoFacadeLocal.edit(presupuesto);
	}

	@Override
	public Collection<Persona> loadPerson() {
		
		return personaFacadeLocal.findAll();
	}

	@Override
	public Collection<Persona> cargarContactos() {
		return personaFacadeLocal.cargarContactos();
	}
	
}
