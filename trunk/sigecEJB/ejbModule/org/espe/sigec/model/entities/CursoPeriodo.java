/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author roberto
 */
@Entity
@Table(name = "sgct_acd_cur_per")
@NamedQueries({ @NamedQuery(name = "CursoPeriodo.findAll", query = "SELECT c FROM CursoPeriodo c") })
public class CursoPeriodo implements Serializable {
	private static final long serialVersionUID = 1L;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id_curso_periodo")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "curper_seq")
	@SequenceGenerator(name = "curper_seq", sequenceName = "curper_seq", allocationSize = 1)
	private BigDecimal idCursoPeriodo;

	@Column(name = "tipo_curso")
	private String tipoCurso;
	@Column(name = "cper_min_est")
	private Integer minimoEstudiantes;
	@Column(name = "cper_max_est")
	private Integer maximoEstudiantes;
	@Column(name = "cper_lugar_cap")
	private String lugarCapacitacion;
	@Size(max = 20)
	@Column(name = "cper_modalidad")
	private String modalidad;

	@Column(name = "prf_id_profesor")
	private BigDecimal idProfesor;

	@JoinColumn(name = "prf_id_profesor", referencedColumnName = "prf_id_profesor", insertable = false, updatable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Profesor profesor;

	@OneToMany(mappedBy = "cursoPeriodo", fetch = FetchType.LAZY)
	private Collection<PensumAcademicoPeriodoCurso> lstPensumAcademicoPerCursoCollection;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "cursoPeriodo", fetch = FetchType.LAZY)
	private PresupuestoCurso presupuestoCurso;

	@JoinColumn(name = "per_id_persona", referencedColumnName = "per_id_persona")
	@ManyToOne(fetch = FetchType.LAZY)
	private Persona persona;

	@OneToOne(mappedBy = "cursoPeriodo", fetch = FetchType.LAZY)
	private HistoricoCursoEstado historicoCursoEstadoCollection;

	@JoinColumn(name = "paca_id", referencedColumnName = "paca_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private PeriodoAcademico periodoAcademico;
	@JoinColumn(name = "cur_id_curso", referencedColumnName = "cur_id_curso")
	@ManyToOne(fetch = FetchType.LAZY)
	private Curso curso;
	@OneToMany(mappedBy = "cursoPeriodo", fetch = FetchType.LAZY)
	private Collection<MaterialDidacticoCurso> materialDidacticoCollection;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cursoPeriodo", fetch = FetchType.LAZY)
	private Collection<ProgramaCurso> programaCursoCollection;

	@Transient
	private ProgramaCurso programaCurso;

	@Transient
	HorarioCursoPeriodo horarioCursoPeriodo;
	
	@Column(name = "numero_horas")
	private Integer numeroHoras;

	public CursoPeriodo() {
	}

	public CursoPeriodo(BigDecimal idCursoPeriodo) {
		this.idCursoPeriodo = idCursoPeriodo;
	}

	public BigDecimal getIdCursoPeriodo() {
		return idCursoPeriodo;
	}

	public void setIdCursoPeriodo(BigDecimal idCursoPeriodo) {
		this.idCursoPeriodo = idCursoPeriodo;
	}

	public String getTipoCurso() {
		return tipoCurso;
	}

	public void setTipoCurso(String tipoCurso) {
		this.tipoCurso = tipoCurso;
	}

	public Integer getMinimoEstudiantes() {
		return minimoEstudiantes;
	}

	public void setMinimoEstudiantes(Integer minimoEstudiantes) {
		this.minimoEstudiantes = minimoEstudiantes;
	}

	public Integer getMaximoEstudiantes() {
		return maximoEstudiantes;
	}

	public void setMaximoEstudiantes(Integer maximoEstudiantes) {
		this.maximoEstudiantes = maximoEstudiantes;
	}

	public PeriodoAcademico getPeriodoAcademico() {
		return periodoAcademico;
	}

	public HistoricoCursoEstado getHistoricoCursoEstadoCollection() {
		return historicoCursoEstadoCollection;
	}

	public void setHistoricoCursoEstadoCollection(
			HistoricoCursoEstado historicoCursoEstadoCollection) {
		this.historicoCursoEstadoCollection = historicoCursoEstadoCollection;
	}

	public void setPeriodoAcademico(PeriodoAcademico periodoAcademico) {
		this.periodoAcademico = periodoAcademico;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Collection<MaterialDidacticoCurso> getMaterialDidacticoCollection() {
		return materialDidacticoCollection;
	}

	public void setMaterialDidacticoCollection(
			Collection<MaterialDidacticoCurso> materialDidacticoCollection) {
		this.materialDidacticoCollection = materialDidacticoCollection;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idCursoPeriodo != null ? idCursoPeriodo.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof CursoPeriodo)) {
			return false;
		}
		CursoPeriodo other = (CursoPeriodo) object;
		if ((this.idCursoPeriodo == null && other.idCursoPeriodo != null)
				|| (this.idCursoPeriodo != null && !this.idCursoPeriodo
						.equals(other.idCursoPeriodo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.espe.sigec.model.entites.CursoPeriodo[ idCursoPeriodo="
				+ idCursoPeriodo + " ]";
	}

	public String getLugarCapacitacion() {
		return lugarCapacitacion;
	}

	public void setLugarCapacitacion(String lugarCapacitacion) {
		this.lugarCapacitacion = lugarCapacitacion;
	}

	public PresupuestoCurso getPresupuestoCurso() {
		return presupuestoCurso;
	}

	public void setPresupuestoCurso(PresupuestoCurso presupuestoCurso) {
		this.presupuestoCurso = presupuestoCurso;
	}

	@Transient
	public String getEstadoProceso() {
		String estadoProceso = "";
		if (getHistoricoCursoEstadoCollection() != null) {
			if (getHistoricoCursoEstadoCollection().getEtapaLanzado() != null && getHistoricoCursoEstadoCollection().getEtapaLanzado().equals("1")) {
				estadoProceso = "ABIERTO";
				if (getHistoricoCursoEstadoCollection().getEtapaEjecutado() != null && getHistoricoCursoEstadoCollection() .getEtapaEjecutado().equals("1")) {
					estadoProceso = "EJECUCION";
					if (getHistoricoCursoEstadoCollection().getEtapaFinalizado() != null && getHistoricoCursoEstadoCollection().getEtapaFinalizado().equals("1")) {
						estadoProceso = "FINALIZADO";
					}
				}
			}
		}

		return estadoProceso;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	// public Collection<ModuloCursoPeriodo> getModuloCursoPeriodoCollection() {
	// return moduloCursoPeriodoCollection;
	// }
	//
	// public void setModuloCursoPeriodoCollection(
	// Collection<ModuloCursoPeriodo> moduloCursoPeriodoCollection) {
	// this.moduloCursoPeriodoCollection = moduloCursoPeriodoCollection;
	// }

	public Collection<ProgramaCurso> getProgramaCursoCollection() {
		return programaCursoCollection;
	}

	public Collection<PensumAcademicoPeriodoCurso> getLstPensumAcademicoPerCursoCollection() {
		return lstPensumAcademicoPerCursoCollection;
	}

	public void setLstPensumAcademicoPerCursoCollection(
			Collection<PensumAcademicoPeriodoCurso> lstPensumAcademicoPerCursoCollection) {
		this.lstPensumAcademicoPerCursoCollection = lstPensumAcademicoPerCursoCollection;
	}

	public void setProgramaCursoCollection(
			Collection<ProgramaCurso> programaCursoCollection) {
		this.programaCursoCollection = programaCursoCollection;
	}

	public ProgramaCurso getProgramaCurso() {
		return programaCurso;
	}

	public void setProgramaCurso(ProgramaCurso programaCurso) {
		this.programaCurso = programaCurso;
	}

	public BigDecimal getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(BigDecimal idProfesor) {
		this.idProfesor = idProfesor;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Integer getNumeroHoras() {
		return numeroHoras;
	}

	public void setNumeroHoras(Integer numeroHoras) {
		this.numeroHoras = numeroHoras;
	}

	public HorarioCursoPeriodo getHorarioCursoPeriodo() {
		return horarioCursoPeriodo;
	}

	public void setHorarioCursoPeriodo(HorarioCursoPeriodo horarioCursoPeriodo) {
		this.horarioCursoPeriodo = horarioCursoPeriodo;
	}

}
