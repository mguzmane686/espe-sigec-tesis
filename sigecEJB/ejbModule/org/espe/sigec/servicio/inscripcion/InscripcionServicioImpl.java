package org.espe.sigec.servicio.inscripcion;

import java.util.Collection;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;

import org.espe.sigec.exception.CupoNoDisponibleException;
import org.espe.sigec.exception.EstudianteDobleInscripcionException;
import org.espe.sigec.exception.UserValidateException;
import org.espe.sigec.exception.UsuarioNoNuevoException;
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
import org.espe.sigec.model.sessionBeans.HistoricoCursoEstadoFacadeLocal;
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
	@EJB
	private HistoricoCursoEstadoFacadeLocal historicoCursoEstadoFacadeLocal;
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
			userTransaction.begin();
			
			if(isNewStudent){
				if(usuarioFacade.isIdentificadorvalida(estudiante.getPersona().getUsuario().getIdentificador())){
					usuarioFacade.create(estudiante.getPersona().getUsuario());
					UsuarioPerfil usuarioPerfil = new UsuarioPerfil(new UsuarioPerfilPK(estudiante.getPersona().getUsuario().getIdUsuario(), "EST"));
					usuarioPerfilFacadeLocal.create(usuarioPerfil);
					personaFacadeLocal.create(estudiante.getPersona());
					estudiante.setPersona(estudiante.getPersona());
					estudianteFacadeLocal.create(estudiante);
				}else{
					throw new Exception("El identificador ya existe");
				}
			}
			
			if(cursoEstudianteFacadeLocal.numeroEstudiantesInscritos(cursoPeriodo.getIdCursoPeriodo()) < cursoPeriodo.getMaximoEstudiantes()){
				cursoEstudiante.setEstudiante(estudiante);
//				cursoEstudiante.setCursoPeriodo(cursoPeriodo);
				cursoEstudiante.setCursoEstudiantePK(new CursoEstudiantePK());
//				cursoEstudiante.getCursoEstudiantePK().setIdCursoPeriodo(cursoPeriodo.getIdCursoPeriodo());
				cursoEstudiante.getCursoEstudiantePK().setIdEstudiante(estudiante.getIdEstudiante());
				cursoEstudiante.setEstadoPago("DEBE");
				cursoEstudiante.setEstadoCupo("CUPO-VIGENTE");
				cursoEstudianteFacadeLocal.create(cursoEstudiante);
				
				if( (cursoEstudianteFacadeLocal.numeroEstudiantesInscritos(cursoPeriodo.getIdCursoPeriodo())) >= cursoPeriodo.getMinimoEstudiantes()){
					cursoPeriodo.getHistoricoCursoEstadoCollection().setEtapaAsignacionProfesor("1");
					historicoCursoEstadoFacadeLocal.edit(cursoPeriodo.getHistoricoCursoEstadoCollection());
					
				}
				
				userTransaction.commit();
			}else{
				throw new Exception("Ya no quedan cupos para el curso");
			}
			
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

	@Override
	public void inscripcionEstudianteCurso(Persona persona, CursoPeriodo cursoPeriodo) throws Exception {
		try {
			userTransaction.begin();
			
			int numeroEstudiantesInscritos = cursoEstudianteFacadeLocal.numeroEstudiantesInscritos(cursoPeriodo.getIdCursoPeriodo());
			if(numeroEstudiantesInscritos < cursoPeriodo.getMaximoEstudiantes()){
				
				Estudiante estudiante = null;
				
				if(persona.getIdPersona()==null){
					if(usuarioFacade.isIdentificadorvalida(persona.getUsuario().getIdentificador())){
						usuarioFacade.create(persona.getUsuario());
						UsuarioPerfil usuarioPerfil = new UsuarioPerfil(new UsuarioPerfilPK(persona.getUsuario().getIdUsuario(), "EST"));
						usuarioPerfilFacadeLocal.create(usuarioPerfil);
						personaFacadeLocal.create(persona);
						
						estudiante = new Estudiante();
						estudiante.setPersona(persona);
						
						estudianteFacadeLocal.create(estudiante);
					}else{
						throw new UsuarioNoNuevoException("El identificador ya existe");
					}
				}else{
					UsuarioPerfilPK usuarioPerfilPK = new UsuarioPerfilPK(persona.getUsuario().getIdUsuario(), "EST");
					UsuarioPerfil usuarioPerfil = usuarioPerfilFacadeLocal.find(usuarioPerfilPK);
					if(usuarioPerfil==null){
						UsuarioPerfil usuarioPerfilTMP = new UsuarioPerfil(new UsuarioPerfilPK(persona.getUsuario().getIdUsuario(), "EST"));
						usuarioPerfilFacadeLocal.create(usuarioPerfilTMP);
						
						estudiante = new Estudiante();
						estudiante.setPersona(persona);
						Estudiante estudianteTMP = estudianteFacadeLocal.buscarEstudinateByCedula(persona.getCedula());
						if(estudianteTMP == null){
							estudianteFacadeLocal.create(estudiante);
						}else{
							estudiante = estudianteTMP;
						}
						
						
					}else{
						Estudiante estudianteTMP = estudianteFacadeLocal.buscarEstudinateByCedula(persona.getCedula());
						estudiante = estudianteTMP;
					}
				}
				//Validacion maximo estudiantes
				
				if(numeroEstudiantesInscritos < cursoPeriodo.getMaximoEstudiantes()){
					CursoEstudiante cursoEstudiante = new CursoEstudiante();
					cursoEstudiante.setEstudiante(estudiante);
	//				cursoEstudiante.setCursoPeriodo(cursoPeriodo);
					cursoEstudiante.setCursoEstudiantePK(new CursoEstudiantePK());
					cursoEstudiante.getCursoEstudiantePK().setIdCursoPeriodo(cursoPeriodo.getIdCursoPeriodo());
					cursoEstudiante.getCursoEstudiantePK().setIdEstudiante(estudiante.getIdEstudiante());
					cursoEstudiante.setEstadoPago("DEBE");
					cursoEstudiante.setEstadoCupo("CUPO-VIGENTE");
					//ojo
					cursoEstudiante.setIdPrograma(cursoPeriodo.getProgramaCurso().getProgramaCursoPK().getIdPrograma());
					
					CursoEstudiante cursoEstudianteTMP = cursoEstudianteFacadeLocal.find(cursoEstudiante.getCursoEstudiantePK());
					if(cursoEstudianteTMP != null){
						throw new EstudianteDobleInscripcionException("El estudiante ya se encuentra inscrito en ese curso");
					}
					
					cursoEstudianteFacadeLocal.create(cursoEstudiante);
					
					if(numeroEstudiantesInscritos+1 >= cursoPeriodo.getMinimoEstudiantes()){
						cursoPeriodo.getHistoricoCursoEstadoCollection().setEtapaAsignacionProfesor("1");
						historicoCursoEstadoFacadeLocal.edit(cursoPeriodo.getHistoricoCursoEstadoCollection());
					}
					
					userTransaction.commit();
				}else{
					throw new CupoNoDisponibleException("Ya no quedan cupos para el curso");
					
				}
			}else{
				throw new CupoNoDisponibleException("Ya no quedan cupos para el curso");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			userTransaction.rollback();
			if(e instanceof CupoNoDisponibleException){
				throw new CupoNoDisponibleException(e.getMessage());
			}else if(e instanceof UsuarioNoNuevoException){
				throw new UsuarioNoNuevoException(e.getMessage());
			}else if (e instanceof EstudianteDobleInscripcionException){
				throw new EstudianteDobleInscripcionException(e.getMessage());
			}else if (e instanceof UserValidateException){
				throw new EstudianteDobleInscripcionException(e.getMessage());
			}else {
				throw new Exception("Lo sentimos ocurrio un error en el proceso");
			}
		}
		
	}
	@Override
	public CursoEstudianteFacadeLocal getCursoEstudianteFacadeLocal() {
		return cursoEstudianteFacadeLocal;
	}
	
}
