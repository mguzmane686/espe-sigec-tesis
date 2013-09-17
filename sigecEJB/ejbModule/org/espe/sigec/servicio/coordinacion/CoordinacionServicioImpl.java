package org.espe.sigec.servicio.coordinacion;

import java.math.BigDecimal;
import java.util.Collection;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.espe.sigec.exception.SigecException;
import org.espe.sigec.model.entities.Aula;
import org.espe.sigec.model.entities.Curso;
import org.espe.sigec.model.entities.CursoEstudiante;
import org.espe.sigec.model.entities.CursoPeriodo;
import org.espe.sigec.model.entities.Edificio;
import org.espe.sigec.model.entities.Especialidad;
import org.espe.sigec.model.entities.Establecimiento;
import org.espe.sigec.model.entities.HistoricoCursoEstado;
import org.espe.sigec.model.entities.InvitacionDocente;
import org.espe.sigec.model.entities.MaterialDidacticoCatalogo;
import org.espe.sigec.model.entities.MaterialDidacticoCurso;
import org.espe.sigec.model.entities.PeriodoAcademico;
import org.espe.sigec.model.sessionBeans.AulaFacadeLocal;
import org.espe.sigec.model.sessionBeans.CursoEstudianteFacadeLocal;
import org.espe.sigec.model.sessionBeans.CursoFacadeLocal;
import org.espe.sigec.model.sessionBeans.CursoPeriodoFacadeLocal;
import org.espe.sigec.model.sessionBeans.EdificioFacadeLocal;
import org.espe.sigec.model.sessionBeans.EspecialidadFacadeLocal;
import org.espe.sigec.model.sessionBeans.HistoricoCursoEstadoFacadeLocal;
import org.espe.sigec.model.sessionBeans.InvitacionDocenteFacadeLocal;
import org.espe.sigec.model.sessionBeans.LugarCursoFacadeLocal;
import org.espe.sigec.model.sessionBeans.MaterialDidacticoCatalogoFacadeLocal;
import org.espe.sigec.model.sessionBeans.MaterialDidacticoCursoFacadeLocal;
import org.espe.sigec.model.sessionBeans.PeriodoAcademicoFacadeLocal;
import org.espe.sigec.model.sessionBeans.ProfesorFacadeLocal;
import org.espe.sigec.utils.SigecClientResourceBoundle;

/**
 * @author roberto
 *
 */
public class CoordinacionServicioImpl implements CoordinacionServicio{
	@EJB
	private AulaFacadeLocal aulaFacadeLocal;
	@EJB
	private CursoFacadeLocal cursoFacadeLocal;
	@EJB
	private PeriodoAcademicoFacadeLocal academicoFacadeLocal;
	@EJB
	private CursoPeriodoFacadeLocal cursoPeriodoFacadeLocal;
	@EJB
	private EspecialidadFacadeLocal especialidadFacadeLocal;
	@EJB
	private HistoricoCursoEstadoFacadeLocal historicoCursoEstadoFacadeLocal;
	@EJB
	private CursoEstudianteFacadeLocal cursoEstudianteFacadeLocal;
	@EJB
	private LugarCursoFacadeLocal lugarCursoFacadeLocal;
	@EJB
	private EdificioFacadeLocal edificioFacadeLocal;
	@EJB
	private ProfesorFacadeLocal profesorFacadeLocal;
	@EJB
	private InvitacionDocenteFacadeLocal invitacionDocenteFacadeLocal; 
	@EJB
	private MaterialDidacticoCatalogoFacadeLocal materialDidacticoCatalogoFacadeLocal;
	
	@EJB
	private MaterialDidacticoCursoFacadeLocal materialDidacticoCursoFacadeLocal;
	
	@Resource
	private UserTransaction userTransaction;
	@Override
	public Collection<Especialidad> findEspecialidades() {
		
		return especialidadFacadeLocal.findAll();
	}
	
	@Override
	public Collection<Curso> findCursoByEspecialidad(Integer idEspecialidad) {
		return cursoFacadeLocal.findCursoByEspecialidad(idEspecialidad);
	}
	
	@Override
	public void abrirCurso(PeriodoAcademico periodoAcademico,
			CursoPeriodo cursoPeriodo) throws Exception {
		
		userTransaction.begin();
		try {
			academicoFacadeLocal.create(periodoAcademico);
			cursoPeriodo.setPeriodoAcademico(periodoAcademico);
			cursoPeriodoFacadeLocal.create(cursoPeriodo);
			
			HistoricoCursoEstado historicoCursoEstado = new HistoricoCursoEstado();
			historicoCursoEstado.setCursoPeriodo(cursoPeriodo);
			historicoCursoEstado.setEstado("1");
			historicoCursoEstado.setEtapaLanzado("1");
			historicoCursoEstado.setEtapaAsignacionProfesor("0");
			historicoCursoEstado.setEtapaEjecutado("0");
			historicoCursoEstado.setEtapaFinalizado("0");
			historicoCursoEstadoFacadeLocal.create(historicoCursoEstado);
			userTransaction.commit();
		} catch (Exception e) {
			userTransaction.rollback();
			throw new Exception(e.getCause());
		}
		
	}

	@Override
	public Collection<Aula> findAulas() {
		return aulaFacadeLocal.findAll();
	}

	@Override
	public Collection<Establecimiento> findLugarCurso() {
		return lugarCursoFacadeLocal.findAll();
	}

	@Override
	public Collection<Edificio> findEdificioByLugarCurso(String idLugar) {
		return edificioFacadeLocal.findEdificioByLugarCurso(idLugar);
	}

	@Override
	public Collection<Aula> findAulaByEdificio(String idEdificio) {
		return aulaFacadeLocal.findCursoByEdificio(idEdificio);
	}

	@Override
	public void administrarCurso(CursoPeriodo cursoPeriodo) throws Exception {		
		userTransaction.begin();
		if(cursoPeriodo.getHistoricoCursoEstadoCollection().getCursoPeriodo()==null){
			cursoPeriodo.getHistoricoCursoEstadoCollection().setCursoPeriodo(cursoPeriodo);
			historicoCursoEstadoFacadeLocal.create(cursoPeriodo.getHistoricoCursoEstadoCollection());
		}
		cursoPeriodoFacadeLocal.edit(cursoPeriodo);
		
		if(cursoPeriodo.getIdProfesor() != null){
			InvitacionDocente invitacionDocente = null;
			
			try {
				invitacionDocente = invitacionDocenteFacadeLocal.verificarUltimaInivtacionDocente(cursoPeriodo.getIdCursoPeriodo(), cursoPeriodo.getIdProfesor().intValue());
				if(invitacionDocente!=null){
					invitacionDocente.setEstado("PCT");
					invitacionDocenteFacadeLocal.edit(invitacionDocente);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				userTransaction.rollback();
			}
			
		}
		try {
			userTransaction.commit();
		} catch (Exception e) {
			userTransaction.rollback();
		}
		
	}

	@Override
	public int numeroEstudiantesInscritos(BigDecimal idCursoPeriodo) {
		return cursoEstudianteFacadeLocal.numeroEstudiantesInscritos(idCursoPeriodo);
	}

	@Override
	public Collection<InvitacionDocente> findProfesoresSeleccionados(BigDecimal idCursoPeriodo) {
		return profesorFacadeLocal.findProfesoresSeleccionados(idCursoPeriodo);
	}

	@Override
	public Collection<CursoEstudiante> estudiantesInscritosCurso(BigDecimal idCursoPeriodo) {
		return cursoEstudianteFacadeLocal.estudiantesInscritosCurso(idCursoPeriodo);
	}

	@Override
	public void actualizarCursoEstudiante(CursoEstudiante cursoEstudiante) throws Exception {
		CursoEstudiante cursoEstudianteTMP = SerializationUtils.clone(cursoEstudiante);
		cursoEstudianteTMP.setAsistenciaEstudianteCollection(null);
		cursoEstudianteTMP.setCalificacionEstudianteCollection(null);
		cursoEstudianteTMP.setProgramaCurso(null);
		cursoEstudianteTMP.getEstudiante().setCursoEstudianteCollection(null);
		cursoEstudianteTMP.getEstudiante().setPersona(null);
		cursoEstudianteFacadeLocal.editCursosEstudiante(cursoEstudianteTMP);
		
	}

	@Override
	public Collection<CursoEstudiante> estudiantesInscritosCurso(
			BigDecimal idCursoPeriodo, String estadoCupo) {
		return cursoEstudianteFacadeLocal.estudiantesInscritosCurso(idCursoPeriodo, estadoCupo);
	}

	@Override
	public Collection<CursoEstudiante> estudiantesInscritosCurso(
			BigDecimal idCursoPeriodo, String estadoCupo, String estadoPago) {
		return cursoEstudianteFacadeLocal.estudiantesInscritosCurso(idCursoPeriodo, estadoCupo, estadoPago);
	}

	@Override
	public Collection<MaterialDidacticoCatalogo> findMaterialDidacticoCatalogos(Integer[] listaIdCursosAsignados, String estado) throws Exception {
		return materialDidacticoCatalogoFacadeLocal.findMaterialDidacticoCatalogos(listaIdCursosAsignados, estado);
	}
	
	@Override
	public Collection<MaterialDidacticoCurso> findMaterialDidacticoAsignado(BigDecimal idCursoPeriodo) throws Exception {
		return materialDidacticoCatalogoFacadeLocal.findMaterialDidacticoAsignado(idCursoPeriodo);
	}

	/* (non-Javadoc)
	 * @see org.espe.sigec.servicio.coordinacion.CoordinacionServicio#actualizarListaMaterialesCurso(java.util.Collection, java.util.Collection)
	 */
	@Override
	public void actualizarListaMaterialesCurso(
			Collection<MaterialDidacticoCurso> lstMaterialDidacticoOriginal,
			Collection<MaterialDidacticoCurso> lstMaterialDidacticoModificada) throws SigecException, Exception {
		int tamOriginal = CollectionUtils.size(lstMaterialDidacticoOriginal);
		userTransaction.begin();
		try {
			
			if(tamOriginal ==0){
	//			Todos los elementos agregados son nuevos
				for(MaterialDidacticoCurso materialDidacticoCursoAgregado: lstMaterialDidacticoModificada){
					if(materialDidacticoCursoAgregado.isSelected()){
						System.out.println("Agregando: "+materialDidacticoCursoAgregado);
						materialDidacticoCursoAgregado.setEstado("ACT");
						try {
							if(materialDidacticoCursoFacadeLocal.find(materialDidacticoCursoAgregado.getMaterialDidacticoCursoPK())==null){
								materialDidacticoCursoFacadeLocal.create(materialDidacticoCursoAgregado);
							}else{
								materialDidacticoCursoFacadeLocal.edit(materialDidacticoCursoAgregado);
							}
						} catch (Exception e) {
								e.printStackTrace();
						}
					}
				}
			}else{
	//			Elementos que existian en la coleccion y fueron modificados de estado
				for(int numItem = 0; numItem < tamOriginal; numItem++){
					MaterialDidacticoCurso materialDidacticoCursoORG = (MaterialDidacticoCurso) CollectionUtils.get(lstMaterialDidacticoOriginal, numItem);
					MaterialDidacticoCurso materialDidacticoCursoMOD = (MaterialDidacticoCurso) CollectionUtils.get(lstMaterialDidacticoModificada, numItem);
					
	//				Se cambio el estado del elemento y se lo actualiza
					if(!(materialDidacticoCursoORG.isSelected() && materialDidacticoCursoMOD.isSelected())){
						System.out.println("Editando: "+ materialDidacticoCursoMOD);
						
						materialDidacticoCursoMOD.setEstado(BooleanUtils.toString(materialDidacticoCursoMOD.isSelected(), "ACT", "INA"));
						materialDidacticoCursoFacadeLocal.edit(materialDidacticoCursoMOD);
					}
				}
				
				for(int numItem = tamOriginal; numItem < lstMaterialDidacticoModificada.size(); numItem++){
					MaterialDidacticoCurso materialDidacticoCursoMOD = (MaterialDidacticoCurso) CollectionUtils.get(lstMaterialDidacticoModificada, numItem);
					materialDidacticoCursoMOD.setEstado(BooleanUtils.toString(materialDidacticoCursoMOD.isSelected(), "ACT", "INA"));
					System.out.println("Editando: "+ materialDidacticoCursoMOD);
					if(materialDidacticoCursoMOD.isSelected()){
						try {
							if(materialDidacticoCursoFacadeLocal.find(materialDidacticoCursoMOD.getMaterialDidacticoCursoPK())==null){
								materialDidacticoCursoFacadeLocal.edit(materialDidacticoCursoMOD);
							}else{
								materialDidacticoCursoFacadeLocal.edit(materialDidacticoCursoMOD);
							}
						} catch (Exception e) {
							e.printStackTrace();							
						}
					}
				}
			}
			userTransaction.commit();
		} catch (Exception e) {
			userTransaction.rollback();
		}
	}

	@Override
	public InvitacionDocente verificarUltimaInivtacionDocente(BigDecimal idCursoPeriodo, Integer idProfesor) throws Exception {
		
		return invitacionDocenteFacadeLocal.verificarUltimaInivtacionDocente(idCursoPeriodo, idProfesor);
	}
}
