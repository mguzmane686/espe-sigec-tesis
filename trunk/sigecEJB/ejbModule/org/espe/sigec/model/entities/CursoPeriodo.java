/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author roberto
 */
@Entity
@Table(name = "curso_periodo")
@NamedQueries({
    @NamedQuery(name = "CursoPeriodo.findAll", query = "SELECT c FROM CursoPeriodo c")})
public class CursoPeriodo implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_curso_periodo")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="curper_seq")
    @SequenceGenerator(name="curper_seq", sequenceName="curper_seq", allocationSize = 1)
    private BigDecimal idCursoPeriodo;
    @Column(name = "fecha_inicio_inscripcion")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioInscripcion;
    @Column(name = "fecha_fin_inscripcion")
    @Temporal(TemporalType.DATE)
    private Date fechaFinInscripcion;
    @Column(name = "fecha_inicio_pago")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioPago;
    @Column(name = "fecha_fin_pago")
    @Temporal(TemporalType.DATE)
    private Date fechaFinPago;
    @Size(max = 10)
    @Column(name = "tipo_curso")
    private String tipoCurso;
    @Column(name = "minimo_estudiantes")
    private Integer minimoEstudiantes;
    @Column(name = "maximo_estudiantes")
    private Integer maximoEstudiantes;
    @Column(name = "lugar_capacitacion")
    private String lugarCapacitacion;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "cursoPeriodo", fetch = FetchType.LAZY)
    private Presupuesto presupuesto;
    @OneToMany(mappedBy = "cursoPeriodo", fetch = FetchType.LAZY)
    private Collection<HistoricoCursoEstado> historicoCursoEstadoCollection;
    @JoinColumn(name = "id_per_academico", referencedColumnName = "id_per_academico")
    @ManyToOne(fetch = FetchType.LAZY)
    private PeriodoAcademico periodoAcademico;
    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso")
    @ManyToOne(fetch = FetchType.LAZY)
    private Curso curso;
    @OneToMany(mappedBy = "cursoPeriodo", fetch = FetchType.LAZY)
    private Collection<Aula> aulaCollection;
    @OneToMany(mappedBy = "cursoPeriodo", fetch = FetchType.LAZY)
    private Collection<MaterialDidactico> materialDidacticoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cursoPeriodo", fetch = FetchType.LAZY)
    private Collection<CursoEstudiante> cursoEstudianteCollection;

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

    public Date getFechaInicioInscripcion() {
        return fechaInicioInscripcion;
    }

    public void setFechaInicioInscripcion(Date fechaInicioInscripcion) {
        this.fechaInicioInscripcion = fechaInicioInscripcion;
    }

    public Date getFechaFinInscripcion() {
        return fechaFinInscripcion;
    }

    public void setFechaFinInscripcion(Date fechaFinInscripcion) {
        this.fechaFinInscripcion = fechaFinInscripcion;
    }

    public Date getFechaInicioPago() {
        return fechaInicioPago;
    }

    public void setFechaInicioPago(Date fechaInicioPago) {
        this.fechaInicioPago = fechaInicioPago;
    }

    public Date getFechaFinPago() {
        return fechaFinPago;
    }

    public void setFechaFinPago(Date fechaFinPago) {
        this.fechaFinPago = fechaFinPago;
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

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Collection<HistoricoCursoEstado> getHistoricoCursoEstadoCollection() {
        return historicoCursoEstadoCollection;
    }

    public void setHistoricoCursoEstadoCollection(Collection<HistoricoCursoEstado> historicoCursoEstadoCollection) {
        this.historicoCursoEstadoCollection = historicoCursoEstadoCollection;
    }

    public PeriodoAcademico getPeriodoAcademico() {
        return periodoAcademico;
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

    public Collection<Aula> getAulaCollection() {
        return aulaCollection;
    }

    public void setAulaCollection(Collection<Aula> aulaCollection) {
        this.aulaCollection = aulaCollection;
    }

    public Collection<MaterialDidactico> getMaterialDidacticoCollection() {
        return materialDidacticoCollection;
    }

    public void setMaterialDidacticoCollection(Collection<MaterialDidactico> materialDidacticoCollection) {
        this.materialDidacticoCollection = materialDidacticoCollection;
    }

    public Collection<CursoEstudiante> getCursoEstudianteCollection() {
        return cursoEstudianteCollection;
    }

    public void setCursoEstudianteCollection(Collection<CursoEstudiante> cursoEstudianteCollection) {
        this.cursoEstudianteCollection = cursoEstudianteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCursoPeriodo != null ? idCursoPeriodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CursoPeriodo)) {
            return false;
        }
        CursoPeriodo other = (CursoPeriodo) object;
        if ((this.idCursoPeriodo == null && other.idCursoPeriodo != null) || (this.idCursoPeriodo != null && !this.idCursoPeriodo.equals(other.idCursoPeriodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entites.CursoPeriodo[ idCursoPeriodo=" + idCursoPeriodo + " ]";
    }

	public String getLugarCapacitacion() {
		return lugarCapacitacion;
	}

	public void setLugarCapacitacion(String lugarCapacitacion) {
		this.lugarCapacitacion = lugarCapacitacion;
	}

    
}
