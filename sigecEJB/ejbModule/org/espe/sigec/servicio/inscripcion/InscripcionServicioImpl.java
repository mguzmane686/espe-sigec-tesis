package org.espe.sigec.servicio.inscripcion;

import java.util.Collection;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;

import org.espe.sigec.exception.UserValidateException;
import org.espe.sigec.model.entities.CursoEstudiante;
import org.espe.sigec.model.entities.CursoEstudiantePK;
import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.Estudiante;
import org.espe.sigec.model.entities.Persona;
import org.espe.sigec.model.entities.Usuario;
import org.espe.sigec.model.entities.UsuarioPerfil;
import org.espe.sigec.model.entities.UsuarioPerfilPK;
import org.espe.sigec.model.sessionBeans.CursoEstudianteFacadeLocal;
import org.espe.sigec.model.sessionBeans.CursoPeriodoFacadeLocal;
import org.espe.sigec.model.sessionBeans.EstudianteFacadeLocal;
import org.espe.sigec.model.sessionBeans.PersonaFacadeLocal;
import org.espe.sigec.model.sessionBeans.UsuarioFacadeLocal;
import org.espe.sigec.model.sessionBeans.UsuarioPerfilFacadeLocal;

/**
 * @author roberto
 *
 */
public class InscripcionServicioImpl implements InscripcionServicio{
	@EJB
	private EstudianteFacadeLocal estudianteFacadeLocal;
	@EJB
	private PersonaFacadeLocal personaFacadeLocal;
	@EJB
	private UsuarioFacadeLocal usuarioFacade;
	@EJB
	private CursoEstudianteFacadeLocal cursoEstudianteFacadeLocal;
	@EJB
	private CursoPeriodoFacadeLocal cursoPeriodoFacadeLocal;
	
	@EJB
	private UsuarioPerfilFacadeLocal usuarioPerfilFacadeLocal;
	
	@Resource
	private UserTransaction userTransaction;
	@Override
	public void registrarEstudiante(Usuario usuario, Persona persona, Estudiante estudiante) throws Exception, UserValidateException {
		userTransaction.begin();
		try {
			usuarioFacade.isIdentificadorvalida(usuario.getIdentificador());
			usuarioFacade.create(usuario);
			UsuarioPerfil usuarioPerfil = new UsuarioPerfil(new UsuarioPerfilPK(usuario.getIdUsuario(), "EST"));
			usuarioPerfilFacadeLocal.create(usuarioPerfil);
			personaFacadeLocal.create(persona);
			estudiante.setPersona(persona);
			estudianteFacadeLocal.create(estudiante);
			userTransaction.commit();
		} catch (Exception e) {
			userTransaction.rollback();
			if(e.getMessage().contains("El identificador ya existe")){
				throw new UserValidateException("UserValidateException");
			}else{
				throw new Exception(e);
			}
		}
	}

	@Override
	public void inscripcionEstudianteCurso(Estudiante estudiante, CursoPeriodo cursoPeriodo,
			CursoEstudiante cursoEstudiante, boolean isNewStudent) throws Exception {
		try {
			if(isNewStudent){
				registrarEstudiante(estudiante.getPersona().getUsuario(), estudiante.getPersona(), estudiante);
			}
			userTransaction.begin();
			cursoEstudiante.setEstudiante(estudiante);
			cursoEstudiante.setCursoPeriodo(cursoPeriodo);
			cursoEstudiante.setCursoEstudiantePK(new CursoEstudiantePK());
			cursoEstudiante.getCursoEstudiantePK().setIdCursoPeriodo(cursoPeriodo.getIdCursoPeriodo());
			cursoEstudiante.getCursoEstudiantePK().setIdEstudiante(estudiante.getIdEstudiante());
			cursoEstudiante.setEstadoPago("DEBE");
			cursoEstudianteFacadeLocal.create(cursoEstudiante);
			userTransaction.commit();
			
		} catch (Exception e) {
			userTransaction.rollback();
			if(e.getMessage().contains("El identificador ya existe")){
				throw new UserValidateException("UserValidateException");
			}else{
				throw new Exception(e);
			}
		}
	}

	@Override
	public Collection<CursoPeriodo> cargarCursoLanzado() {
		return cursoPeriodoFacadeLocal.cargarCursoLanzado();
	}
	
	@Override
	public Estudiante buscarEstudinateByCedula(String cedula){
		return estudianteFacadeLocal.buscarEstudinateByCedula(cedula);
	}
	
}
