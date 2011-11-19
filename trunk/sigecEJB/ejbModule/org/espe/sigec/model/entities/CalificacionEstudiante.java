/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author roberto
 */
@Entity
@Table(name = "calificacion_estudiante")
@NamedQueries({
    @NamedQuery(name = "CalificacionEstudiante.findAll", query = "SELECT c FROM CalificacionEstudiante c")})
public class CalificacionEstudiante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "_id_calificacion")
    private Integer idCalificacion;
    @Column(name = "nota")
    private BigInteger nota;
    @JoinColumns({
        @JoinColumn(name = "id_curso_periodo", referencedColumnName = "id_curso_periodo"),
        @JoinColumn(name = "id_estudiante", referencedColumnName = "id_estudiante")})
    @ManyToOne(fetch = FetchType.LAZY)
    private CursoEstudiante cursoEstudiante;

    public CalificacionEstudiante() {
    }

    public CalificacionEstudiante(Integer idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public Integer getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(Integer idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public BigInteger getNota() {
        return nota;
    }

    public void setNota(BigInteger nota) {
        this.nota = nota;
    }

    public CursoEstudiante getCursoEstudiante() {
        return cursoEstudiante;
    }

    public void setCursoEstudiante(CursoEstudiante cursoEstudiante) {
        this.cursoEstudiante = cursoEstudiante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCalificacion != null ? idCalificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalificacionEstudiante)) {
            return false;
        }
        CalificacionEstudiante other = (CalificacionEstudiante) object;
        if ((this.idCalificacion == null && other.idCalificacion != null) || (this.idCalificacion != null && !this.idCalificacion.equals(other.idCalificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entites.CalificacionEstudiante[ idCalificacion=" + idCalificacion + " ]";
    }
    
}
