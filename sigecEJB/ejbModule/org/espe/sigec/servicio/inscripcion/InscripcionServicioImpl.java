package org.espe.sigec.servicio.inscripcion;

import java.util.Collection;

import javax.ejb.EJB;

import org.espe.sigec.model.entities.CursoEstudiante;
import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.Estudiante;
import org.espe.sigec.model.entities.Persona;
import org.espe.sigec.model.entities.Usuario;
import org.espe.sigec.model.sessionBeans.CursoEstudianteFacadeLocal;
import org.espe.sigec.model.sessionBeans.CursoPeriodoFacadeLocal;
import org.espe.sigec.model.sessionBeans.EstudianteFacadeLocal;
import org.espe.sigec.model.sessionBeans.PersonaFacadeLocal;
import org.espe.sigec.model.sessionBeans.UsuarioFacadeLocal;

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
	
	@Override
	public void registrarEstudiante(Usuario usuario, Persona persona, Estudiante estudiante) throws Exception {
		usuarioFacade.create(usuario);
		personaFacadeLocal.create(persona);
//		estudianteFacadeLocal.create(estudiante);	
	}

	@Override
	public void inscripcionEstudianteCurso(Estudiante estudiante, CursoPeriodo cursoPeriodo,
			CursoEstudiante cursoEstudiante) throws Exception {
		cursoEstudiante.setEstudiante(estudiante);
		cursoEstudiante.setCursoPeriodo(cursoPeriodo);
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
