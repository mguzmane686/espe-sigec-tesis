/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Roberto
 */
@SuppressWarnings("serial")
@Embeddable
public class SgctSgSecuenciaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "sg_id_secuencia")
    private int sgIdSecuencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "sg_tipo")
    private String sgTipo;

    public SgctSgSecuenciaPK() {
    }

    public SgctSgSecuenciaPK(int sgIdSecuencia, String sgTipo) {
        this.sgIdSecuencia = sgIdSecuencia;
        this.sgTipo = sgTipo;
    }

    public int getSgIdSecuencia() {
        return sgIdSecuencia;
    }

    public void setSgIdSecuencia(int sgIdSecuencia) {
        this.sgIdSecuencia = sgIdSecuencia;
    }

    public String getSgTipo() {
        return sgTipo;
    }

    public void setSgTipo(String sgTipo) {
        this.sgTipo = sgTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) sgIdSecuencia;
        hash += (sgTipo != null ? sgTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SgctSgSecuenciaPK)) {
            return false;
        }
        SgctSgSecuenciaPK other = (SgctSgSecuenciaPK) object;
        if (this.sgIdSecuencia != other.sgIdSecuencia) {
            return false;
        }
        if ((this.sgTipo == null && other.sgTipo != null) || (this.sgTipo != null && !this.sgTipo.equals(other.sgTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.SgctSgSecuenciaPK[ sgIdSecuencia=" + sgIdSecuencia + ", sgTipo=" + sgTipo + " ]";
    }
    
}
