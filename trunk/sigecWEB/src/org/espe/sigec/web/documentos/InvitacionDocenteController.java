package org.espe.sigec.web.documentos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.InvitacionDocente;
import org.espe.sigec.model.entities.InvitacionDocentePK;
import org.espe.sigec.model.entities.Plantilla;
import org.espe.sigec.model.entities.Profesor;
import org.espe.sigec.model.views.Memo;
import org.espe.sigec.servicio.admGeneral.AdmGeneralServicio;
import org.espe.sigec.servicio.curso.CursoServicio;
import org.espe.sigec.servicio.documentos.DocumentoServicio;
import org.espe.sigec.web.reportes.ReporteGenerico;

/**
 * @author Roberto
 *
 */
@ManagedBean(name ="invitacionDocenteController")
@ViewScoped
public class InvitacionDocenteController {
	
	@Inject
	private CursoServicio cursoServicio;
	@Inject
	private AdmGeneralServicio admGeneralServicio;
	@Inject
	private DocumentoServicio documentoServicio;
	
	private Collection<CursoPeriodo> lstCursoPeriodos;
	private Collection<Profesor> lstProfesors;
	
	private InvitacionDocente invitacionDocente;
	private Plantilla plantilla;
	
	private Memo memo;
	
	public InvitacionDocenteController() {
		setInvitacionDocente(new InvitacionDocente());
		getInvitacionDocente().setInvitacionDocentePK(new InvitacionDocentePK());
		getInvitacionDocente().setProfesor(new Profesor());
	}
	
	@PostConstruct
	public void loadController(){
		setLstCursoPeriodos(cursoServicio.findCursoAbierto());
		setLstProfesors(admGeneralServicio.cargarProfesores());
		setPlantilla(documentoServicio.obtenerPlantillaDocumento(1));
	}
	
	public void btnGenerarInvitacion(ActionEvent e) throws IOException{
		Collection<Memo> lstMemos = new ArrayList<Memo>(1);
		Map<String, Object> valuesMap = new HashMap<String, Object>();
		valuesMap.put("NOMBRE_DEL_CURSO", getLstCursoPeriodos().iterator().next().getCurso().getNombreCurso());
		valuesMap.put("fecha_curso", getLstCursoPeriodos().iterator().next().getPeriodoAcademico().getFechaInicio());
		valuesMap.put("lugar_a_dictarse", "Espe");
		valuesMap.put("valor_a_pagar", "10");
		valuesMap.put("pp", "USD");
		 
		valuesMap.put("n_horas", "10");
		String templateString = getPlantilla().getDocContenido();
		
		System.out.println(templateString);
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		String resolvedString = sub.replace(templateString);
		System.out.println(resolvedString);
		setMemo(new Memo());
		getMemo().setFechaInivitacion(new Date());
		getMemo().setNombreDirectora("Ing. Karla Benavides");
		getMemo().setNombreElaborador("maniac787");
		getMemo().setNombreProveedor(getLstProfesors().iterator().next().getPersona().getPrimerApellido()+" "+getLstProfesors().iterator().next().getPersona().getPrimerNombre());
		getMemo().setNumeroInivitacion("000001");
		getMemo().setCuerpoMemo(resolvedString);
		
		lstMemos.add(getMemo());
		ReporteGenerico.getResource().generarReporteSimpleAsByte("invitacionProveedor",  lstMemos);
		
	}
	
	public InvitacionDocente getInvitacionDocente() {
		return invitacionDocente;
	}
	public void setInvitacionDocente(InvitacionDocente invitacionDocente) {
		this.invitacionDocente = invitacionDocente;
	}

	public Collection<CursoPeriodo> getLstCursoPeriodos() {
		return lstCursoPeriodos;
	}

	public void setLstCursoPeriodos(Collection<CursoPeriodo> lstCursoPeriodos) {
		this.lstCursoPeriodos = lstCursoPeriodos;
	}

	public Collection<Profesor> getLstProfesors() {
		return lstProfesors;
	}

	public void setLstProfesors(Collection<Profesor> lstProfesors) {
		this.lstProfesors = lstProfesors;
	}

	public Plantilla getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(Plantilla plantilla) {
		this.plantilla = plantilla;
	}

	public Memo getMemo() {
		return memo;
	}

	public void setMemo(Memo memo) {
		this.memo = memo;
	}
	
	
}
