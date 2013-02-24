package org.espe.sigec.web.documentos;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.espe.sigec.web.seguridad.HomeSessionController;
import org.espe.sigec.web.utils.FacesUtils;

/**
 * @author Roberto
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name ="invitacionDocenteController")
@ViewScoped
public class InvitacionDocenteController implements Serializable{
	
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
	private boolean savedInvitacion;
	public InvitacionDocenteController() {
		initController();
	}
	
	private void initController(){
		setInvitacionDocente(new InvitacionDocente());
		getInvitacionDocente().setInvitacionDocentePK(new InvitacionDocentePK());
		getInvitacionDocente().setFechaInvitacion(Calendar.getInstance().getTime());
//		getInvitacionDocente().setProfesor(new Profesor());
	}
	@PostConstruct
	public void loadController(){
		setLstCursoPeriodos(cursoServicio.findCursoAbierto());
		setLstProfesors(admGeneralServicio.cargarProfesores());
		setPlantilla(documentoServicio.obtenerPlantillaDocumento(1));
	}
	
	public void btnGenerarInvitacion(ActionEvent e) throws IOException{
		if(getInvitacionDocente().getInvitacionDocentePK().getDocNumInvit()==null){
			CursoPeriodo cursoPeriodo = findCursoSelected(getInvitacionDocente().getInvitacionDocentePK().getIdCursoPeriodo());
			
			Collection<Memo> lstMemos = new ArrayList<Memo>(1);
			Map<String, Object> valuesMap = new HashMap<String, Object>();
			valuesMap.put("NOMBRE_DEL_CURSO", cursoPeriodo.getCurso().getNombreCurso());
			valuesMap.put("fecha_curso", cursoPeriodo.getPeriodoAcademico().getFechaInicio());
			valuesMap.put("lugar_a_dictarse", "Espe");
			valuesMap.put("valor_a_pagar", getInvitacionDocente().getDocValorPagar());
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
			String elaboradoPor =  ((HomeSessionController)FacesUtils.getManagedBean("homeSessionController")).getUsuarioPerfil().getPersona().getPrimerApellido() +" "+
					((HomeSessionController)FacesUtils.getManagedBean("homeSessionController")).getUsuarioPerfil().getPersona().getPrimerNombre();
			getMemo().setNombreElaborador(elaboradoPor);
			
			Profesor profesor =  findProfesorSelected();
			getMemo().setNombreProveedor(profesor.getPersona().getPrimerApellido()+" "+profesor.getPersona().getPrimerNombre());
			getMemo().setNumeroInivitacion("000001");
			getMemo().setCuerpoMemo(resolvedString);
			
			lstMemos.add(getMemo());
			
			try {
				getInvitacionDocente().setPlantilla(getPlantilla());
				documentoServicio.crearInivtacionDocente(getInvitacionDocente());
				getMemo().setNumeroInivitacion(getInvitacionDocente().getInvitacionDocentePK().getDocNumInvit());
				
				FacesUtils.addInfoMessage("Se genero exitosamente la invitacion");
//				FacesUtils.refresh();
//				
				setSavedInvitacion(Boolean.TRUE);
				initController();
				
			} catch (Exception e1) {
				FacesUtils.addErrorMessage("Ocurrio un error al guardar la invitacion");
				e1.printStackTrace();
			}
		
		}
	}
	
	public void btnNuevaInvitacion(){
		setSavedInvitacion(Boolean.FALSE);
		initController();
	}
	public void lanzarPdfMemo(){
		Collection<Memo> lstMemos = new ArrayList<Memo>(1);
		lstMemos.add(getMemo());
		try {
			ReporteGenerico.getResource().generarReporteSimpleAsByte("invitacionProveedor",  lstMemos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private CursoPeriodo findCursoSelected(BigInteger idCursoPeriodo){
		for(CursoPeriodo cursoPeriodo: getLstCursoPeriodos()){
			if(cursoPeriodo.getIdCursoPeriodo().toString().equals(idCursoPeriodo.toString())){
				return cursoPeriodo;
			}
		}
		return null;
	}
	
	private Profesor findProfesorSelected(){
		for(Profesor profesor: getLstProfesors()){
			if(profesor.getIdProfesor().toString().equals(String.valueOf(getInvitacionDocente().getInvitacionDocentePK().getPrfIdProfesor()))){
				return profesor;
			}
		}
		return null;
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

	public boolean isSavedInvitacion() {
		return savedInvitacion;
	}

	public void setSavedInvitacion(boolean savedInvitacion) {
		this.savedInvitacion = savedInvitacion;
	}
	
	
}
