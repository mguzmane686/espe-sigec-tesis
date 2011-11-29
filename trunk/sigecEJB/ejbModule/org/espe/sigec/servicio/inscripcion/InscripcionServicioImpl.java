package org.espe.sigec.servicio.inscripcion;

import javax.ejb.EJB;

import org.espe.sigec.model.entities.Estudiante;
import org.espe.sigec.model.entities.Persona;
import org.espe.sigec.model.entities.Usuario;
import org.espe.sigec.model.sessionBeans.EstudianteFacadeLocal;
import org.espe.sigec.model.sessionBeans.PersonaFacadeLocal;
import org.espe.sigec.model.sessionBeans.UsuarioFacadeLocal;

public class InscripcionServicioImpl implements InscripcionServicio{
	@SuppressWarnings("unused")
	@EJB
	private EstudianteFacadeLocal estudianteFacadeLocal;
	@EJB
	private PersonaFacadeLocal personaFacadeLocal;
	@EJB
	private UsuarioFacadeLocal usuarioFacade;

	@Override
	public void registrarEstudiante(Usuario usuario, Persona persona, Estudiante estudiante) throws Exception {
		usuarioFacade.create(usuario);
		personaFacadeLocal.create(persona);
//		estudianteFacadeLocal.create(estudiante);	
	}
}
