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
import javax.validation.constraints.Size;

/**
 *
 * @author roberto
 */
@SuppressWarnings("serial")
@Embeddable
public class DetallePresupuestoCursoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_curso_periodo")
    private BigInteger idCursoPeriodo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "cod_elemento")
    private String codElemento;

    public DetallePresupuestoCursoPK() {
    }

    public DetallePresupuestoCursoPK(BigInteger idCursoPeriodo, String codElemento) {
        this.idCursoPeriodo = idCursoPeriodo;
        this.codElemento = codElemento;
    }

    public BigInteger getIdCursoPeriodo() {
        return idCursoPeriodo;
    }

    public void setIdCursoPeriodo(BigInteger idCursoPeriodo) {
        this.idCursoPeriodo = idCursoPeriodo;
    }

    public String getCodElemento() {
        return codElemento;
    }

    public void setCodElemento(String codElemento) {
        this.codElemento = codElemento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCursoPeriodo != null ? idCursoPeriodo.hashCode() : 0);
        hash += (codElemento != null ? codElemento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallePresupuestoCursoPK)) {
            return false;
        }
        DetallePresupuestoCursoPK other = (DetallePresupuestoCursoPK) object;
        if ((this.idCursoPeriodo == null && other.idCursoPeriodo != null) || (this.idCursoPeriodo != null && !this.idCursoPeriodo.equals(other.idCursoPeriodo))) {
            return false;
        }
        if ((this.codElemento == null && other.codElemento != null) || (this.codElemento != null && !this.codElemento.equals(other.codElemento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.DetallePresupuestoCursoPK[ idCursoPeriodo=" + idCursoPeriodo + ", codElemento=" + codElemento + " ]";
    }
    
}