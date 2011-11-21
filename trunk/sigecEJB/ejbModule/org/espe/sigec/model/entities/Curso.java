/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "curso")
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c")})
public class Curso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_curso")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="curso_seq")
    @SequenceGenerator(name="curso_seq", sequenceName="curso_seq", allocationSize = 1)
    private Integer idCurso;
    @Size(max = 250)
    @Column(name = "nombre_curso")
    private String nombreCurso;
    @Size(max = 500)
    @Column(name = "descripcion_curso")
    private String descripcionCurso;
    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
    private Collection<PensumAcademico> pensumAcademicoCollection;
    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
    private Collection<CursoPeriodo> cursoPeriodoCollection;

    @Transient
    private boolean showCursoPeriodoCollection;
    
    public Curso() {
    }

    public Curso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getDescripcionCurso() {
        return descripcionCurso;
    }

    public void setDescripcionCurso(String descripcionCurso) {
        this.descripcionCurso = descripcionCurso;
    }

    public Collection<PensumAcademico> getPensumAcademicoCollection() {
        return pensumAcademicoCollection;
    }

    public void setPensumAcademicoCollection(Collection<PensumAcademico> pensumAcademicoCollection) {
        this.pensumAcademicoCollection = pensumAcademicoCollection;
    }

    public Collection<CursoPeriodo> getCursoPeriodoCollection() {
        return cursoPeriodoCollection;
    }

    public void setCursoPeriodoCollection(Collection<CursoPeriodo> cursoPeriodoCollection) {
        this.cursoPeriodoCollection = cursoPeriodoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCurso != null ? idCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.idCurso == null && other.idCurso != null) || (this.idCurso != null && !this.idCurso.equals(other.idCurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entites.Curso[ idCurso=" + idCurso + " ]";
    }

	public boolean isShowCursoPeriodoCollection() {
		return showCursoPeriodoCollection;
	}

	public void setShowCursoPeriodoCollection(boolean showCursoPeriodoCollection) {
		this.showCursoPeriodoCollection = showCursoPeriodoCollection;
	}
    
}
