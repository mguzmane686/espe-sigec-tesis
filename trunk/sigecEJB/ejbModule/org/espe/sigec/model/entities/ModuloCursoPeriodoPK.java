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
 * @author Roberto
 */
@Embeddable
public class ModuloCursoPeriodoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_curso_periodo")
    private BigInteger idCursoPeriodo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_modulo_curso")
    private int idModuloCurso;

    public ModuloCursoPeriodoPK() {
    }

    public ModuloCursoPeriodoPK(BigInteger idCursoPeriodo, int idModuloCurso) {
        this.idCursoPeriodo = idCursoPeriodo;
        this.idModuloCurso = idModuloCurso;
    }

    public BigInteger getIdCursoPeriodo() {
        return idCursoPeriodo;
    }

    public void setIdCursoPeriodo(BigInteger idCursoPeriodo) {
        this.idCursoPeriodo = idCursoPeriodo;
    }

    public int getIdModuloCurso() {
        return idModuloCurso;
    }

    public void setIdModuloCurso(int idModuloCurso) {
        this.idModuloCurso = idModuloCurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCursoPeriodo != null ? idCursoPeriodo.hashCode() : 0);
        hash += (int) idModuloCurso;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModuloCursoPeriodoPK)) {
            return false;
        }
        ModuloCursoPeriodoPK other = (ModuloCursoPeriodoPK) object;
        if ((this.idCursoPeriodo == null && other.idCursoPeriodo != null) || (this.idCursoPeriodo != null && !this.idCursoPeriodo.equals(other.idCursoPeriodo))) {
            return false;
        }
        if (this.idModuloCurso != other.idModuloCurso) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.ModuloCursoPeriodoPK[ idCursoPeriodo=" + idCursoPeriodo + ", idModuloCurso=" + idModuloCurso + " ]";
    }
    
}
