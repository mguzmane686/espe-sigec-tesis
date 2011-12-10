/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author roberto
 */
@SuppressWarnings("serial")
@Embeddable
public class CursoEstudiantePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_curso_periodo")
    private BigDecimal idCursoPeriodo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_estudiante")
    private int idEstudiante;

    public CursoEstudiantePK() {
    }

    public CursoEstudiantePK(BigDecimal idCursoPeriodo, int idEstudiante) {
        this.idCursoPeriodo = idCursoPeriodo;
        this.idEstudiante = idEstudiante;
    }

    public BigDecimal getIdCursoPeriodo() {
        return idCursoPeriodo;
    }

    public void setIdCursoPeriodo(BigDecimal idCursoPeriodo) {
        this.idCursoPeriodo = idCursoPeriodo;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCursoPeriodo != null ? idCursoPeriodo.hashCode() : 0);
        hash += (int) idEstudiante;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CursoEstudiantePK)) {
            return false;
        }
        CursoEstudiantePK other = (CursoEstudiantePK) object;
        if ((this.idCursoPeriodo == null && other.idCursoPeriodo != null) || (this.idCursoPeriodo != null && !this.idCursoPeriodo.equals(other.idCursoPeriodo))) {
            return false;
        }
        if (this.idEstudiante != other.idEstudiante) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entites.CursoEstudiantePK[ idCursoPeriodo=" + idCursoPeriodo + ", idEstudiante=" + idEstudiante + " ]";
    }
    
}
