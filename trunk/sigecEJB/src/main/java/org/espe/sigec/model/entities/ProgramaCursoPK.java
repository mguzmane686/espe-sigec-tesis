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
 * @author Roberto
 */
@SuppressWarnings("serial")
@Embeddable
public class ProgramaCursoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "prg_id")
    private int idPrograma;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_curso_periodo")
    private BigDecimal idCursoPeriodo;

    public ProgramaCursoPK() {
    }

    public ProgramaCursoPK(int idPrograma, BigDecimal idCursoPeriodo) {
        this.idPrograma = idPrograma;
        this.idCursoPeriodo = idCursoPeriodo;
    }

    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }

    public BigDecimal getIdCursoPeriodo() {
        return idCursoPeriodo;
    }

    public void setIdCursoPeriodo(BigDecimal idCursoPeriodo) {
        this.idCursoPeriodo = idCursoPeriodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPrograma;
        hash += (idCursoPeriodo != null ? idCursoPeriodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramaCursoPK)) {
            return false;
        }
        ProgramaCursoPK other = (ProgramaCursoPK) object;
        if (this.idPrograma != other.idPrograma) {
            return false;
        }
        if ((this.idCursoPeriodo == null && other.idCursoPeriodo != null) || (this.idCursoPeriodo != null && !this.idCursoPeriodo.equals(other.idCursoPeriodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.ProgramaCursoPK[ idPrograma=" + idPrograma + ", idCursoPeriodo=" + idCursoPeriodo + " ]";
    }
    
}
