/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author roberto
 */
@Embeddable
public class AsistenciaEstudiantePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_asistencia")
    private int idAsistencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_curso_periodo")
    private BigInteger idCursoPeriodo;

    public AsistenciaEstudiantePK() {
    }

    public AsistenciaEstudiantePK(int idAsistencia, BigInteger idCursoPeriodo) {
        this.idAsistencia = idAsistencia;
        this.idCursoPeriodo = idCursoPeriodo;
    }

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public BigInteger getIdCursoPeriodo() {
        return idCursoPeriodo;
    }

    public void setIdCursoPeriodo(BigInteger idCursoPeriodo) {
        this.idCursoPeriodo = idCursoPeriodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idAsistencia;
        hash += (idCursoPeriodo != null ? idCursoPeriodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsistenciaEstudiantePK)) {
            return false;
        }
        AsistenciaEstudiantePK other = (AsistenciaEstudiantePK) object;
        if (this.idAsistencia != other.idAsistencia) {
            return false;
        }
        if ((this.idCursoPeriodo == null && other.idCursoPeriodo != null) || (this.idCursoPeriodo != null && !this.idCursoPeriodo.equals(other.idCursoPeriodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entites.AsistenciaEstudiantePK[ idAsistencia=" + idAsistencia + ", idCursoPeriodo=" + idCursoPeriodo + " ]";
    }
    
}
