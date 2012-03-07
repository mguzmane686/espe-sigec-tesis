/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 *
 * @author Roberto
 */
@Entity
@Table(name = "programa_curso")
@NamedQueries({
    @NamedQuery(name = "ProgramaCurso.findAll", query = "SELECT p FROM ProgramaCurso p"),
    @NamedQuery(name = "ProgramaCurso.findByIdPrograma", query = "SELECT p FROM ProgramaCurso p WHERE p.programaCursoPK.idPrograma = :idPrograma"),
    @NamedQuery(name = "ProgramaCurso.findByIdCursoPeriodo", query = "SELECT p FROM ProgramaCurso p WHERE p.programaCursoPK.idCursoPeriodo = :idCursoPeriodo"),
    @NamedQuery(name = "ProgramaCurso.findByFechaInicio", query = "SELECT p FROM ProgramaCurso p WHERE p.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "ProgramaCurso.findByFechaFin", query = "SELECT p FROM ProgramaCurso p WHERE p.fechaFin = :fechaFin"),
    @NamedQuery(name = "ProgramaCurso.findByModalidad", query = "SELECT p FROM ProgramaCurso p WHERE p.modalidad = :modalidad")})
public class ProgramaCurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProgramaCursoPK programaCursoPK;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Size(max = 10)
    @Column(name = "modalidad")
    private String modalidad;
    @JoinColumn(name = "id_programa", referencedColumnName = "id_programa", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Programa programa;
    @JoinColumn(name = "id_curso_periodo", referencedColumnName = "id_curso_periodo", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CursoPeriodo cursoPeriodo;

    @Transient
    private boolean selected;
    public ProgramaCurso() {
    }

    public ProgramaCurso(ProgramaCursoPK programaCursoPK) {
        this.programaCursoPK = programaCursoPK;
    }

    public ProgramaCurso(int idPrograma, BigInteger idCursoPeriodo) {
        this.programaCursoPK = new ProgramaCursoPK(idPrograma, idCursoPeriodo);
    }

    public ProgramaCursoPK getProgramaCursoPK() {
        return programaCursoPK;
    }

    public void setProgramaCursoPK(ProgramaCursoPK programaCursoPK) {
        this.programaCursoPK = programaCursoPK;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public CursoPeriodo getCursoPeriodo() {
        return cursoPeriodo;
    }

    public void setCursoPeriodo(CursoPeriodo cursoPeriodo) {
        this.cursoPeriodo = cursoPeriodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (programaCursoPK != null ? programaCursoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramaCurso)) {
            return false;
        }
        ProgramaCurso other = (ProgramaCurso) object;
        if ((this.programaCursoPK == null && other.programaCursoPK != null) || (this.programaCursoPK != null && !this.programaCursoPK.equals(other.programaCursoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.ProgramaCurso[ programaCursoPK=" + programaCursoPK + " ]";
    }

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
    
}
