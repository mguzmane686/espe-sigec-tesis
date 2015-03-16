/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Roberto
 */
@Entity
@Table(name = "sgct_sg_secuencia", catalog = "sigec", schema = "public")
@NamedQueries({
    @NamedQuery(name = "SgctSgSecuencia.findAll", query = "SELECT s FROM SgctSgSecuencia s"),
    @NamedQuery(name = "SgctSgSecuencia.findBySgIdSecuencia", query = "SELECT s FROM SgctSgSecuencia s WHERE s.sgctSgSecuenciaPK.sgIdSecuencia = :sgIdSecuencia"),
    @NamedQuery(name = "SgctSgSecuencia.findBySgTipo", query = "SELECT s FROM SgctSgSecuencia s WHERE s.sgctSgSecuenciaPK.sgTipo = :sgTipo"),
    @NamedQuery(name = "SgctSgSecuencia.findBySgPrefijo", query = "SELECT s FROM SgctSgSecuencia s WHERE s.sgPrefijo = :sgPrefijo"),
    @NamedQuery(name = "SgctSgSecuencia.findBySgDescripcion", query = "SELECT s FROM SgctSgSecuencia s WHERE s.sgDescripcion = :sgDescripcion")})
public class SgctSgSecuencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SgctSgSecuenciaPK sgctSgSecuenciaPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "sg_prefijo")
    private String sgPrefijo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "sg_descripcion")
    private String sgDescripcion;

    public SgctSgSecuencia() {
    }

    public SgctSgSecuencia(SgctSgSecuenciaPK sgctSgSecuenciaPK) {
        this.sgctSgSecuenciaPK = sgctSgSecuenciaPK;
    }

    public SgctSgSecuencia(SgctSgSecuenciaPK sgctSgSecuenciaPK, String sgPrefijo, String sgDescripcion) {
        this.sgctSgSecuenciaPK = sgctSgSecuenciaPK;
        this.sgPrefijo = sgPrefijo;
        this.sgDescripcion = sgDescripcion;
    }

    public SgctSgSecuencia(int sgIdSecuencia, String sgTipo) {
        this.sgctSgSecuenciaPK = new SgctSgSecuenciaPK(sgIdSecuencia, sgTipo);
    }

    public SgctSgSecuenciaPK getSgctSgSecuenciaPK() {
        return sgctSgSecuenciaPK;
    }

    public void setSgctSgSecuenciaPK(SgctSgSecuenciaPK sgctSgSecuenciaPK) {
        this.sgctSgSecuenciaPK = sgctSgSecuenciaPK;
    }

    public String getSgPrefijo() {
        return sgPrefijo;
    }

    public void setSgPrefijo(String sgPrefijo) {
        this.sgPrefijo = sgPrefijo;
    }

    public String getSgDescripcion() {
        return sgDescripcion;
    }

    public void setSgDescripcion(String sgDescripcion) {
        this.sgDescripcion = sgDescripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sgctSgSecuenciaPK != null ? sgctSgSecuenciaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SgctSgSecuencia)) {
            return false;
        }
        SgctSgSecuencia other = (SgctSgSecuencia) object;
        if ((this.sgctSgSecuenciaPK == null && other.sgctSgSecuenciaPK != null) || (this.sgctSgSecuenciaPK != null && !this.sgctSgSecuenciaPK.equals(other.sgctSgSecuenciaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.SgctSgSecuencia[ sgctSgSecuenciaPK=" + sgctSgSecuenciaPK + " ]";
    }
    
}
