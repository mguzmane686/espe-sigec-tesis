package org.espe.sigec.servicio.inscripcion;

import java.util.Collection;

import javax.ejb.EJB;

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
	@Override
	public void registrarEstudiante(Usuario usuario, Persona persona, Estudiante estudiante) throws Exception, UserValidateException {
		usuarioFacade.isIdentificadorvalida(usuario.getIdentificador());
		usuarioFacade.create(usuario);
		UsuarioPerfil usuarioPerfil = new UsuarioPerfil(new UsuarioPerfilPK(usuario.getIdUsuario(), "EST"));
		usuarioPerfilFacadeLocal.create(usuarioPerfil);
		personaFacadeLocal.create(persona);
		estudiante.setPersona(persona);
		estudianteFacadeLocal.create(estudiante);	
	}

	@Override
	public void inscripcionEstudianteCurso(Estudiante estudiante, CursoPeriodo cursoPeriodo,
			CursoEstudiante cursoEstudiante, boolean isNewStudent) throws Exception {
		
		if(isNewStudent){
			registrarEstudiante(estudiante.getPersona().getUsuario(), estudiante.getPersona(), estudiante);
		}
		cursoEstudiante.setEstudiante(estudiante);
		cursoEstudiante.setCursoPeriodo(cursoPeriodo);
		cursoEstudiante.setCursoEstudiantePK(new CursoEstudiantePK());
		cursoEstudiante.getCursoEstudiantePK().setIdCursoPeriodo(cursoPeriodo.getIdCursoPeriodo());
		cursoEstudiante.getCursoEstudiantePK().setIdEstudiante(estudiante.getIdEstudiante());
		cursoEstudiante.setEstadoPago("DEBE");
		cursoEstudianteFacadeLocal.create(cursoEstudiante);
	
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
