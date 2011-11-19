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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author roberto
 */
@Entity
@Table(name = "presupuesto")
@NamedQueries({
    @NamedQuery(name = "Presupuesto.findAll", query = "SELECT p FROM Presupuesto p")})
public class Presupuesto implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_curso_periodo")
    private BigDecimal idCursoPeriodo;
    @Column(name = "dinero_asignado")
    private BigInteger dineroAsignado;
    @JoinColumn(name = "id_curso_periodo", referencedColumnName = "id_curso_periodo", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private CursoPeriodo cursoPeriodo;

    public Presupuesto() {
    }

    public Presupuesto(BigDecimal idCursoPeriodo) {
        this.idCursoPeriodo = idCursoPeriodo;
    }

    public BigDecimal getIdCursoPeriodo() {
        return idCursoPeriodo;
    }

    public void setIdCursoPeriodo(BigDecimal idCursoPeriodo) {
        this.idCursoPeriodo = idCursoPeriodo;
    }

    public BigInteger getDineroAsignado() {
        return dineroAsignado;
    }

    public void setDineroAsignado(BigInteger dineroAsignado) {
        this.dineroAsignado = dineroAsignado;
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
        hash += (idCursoPeriodo != null ? idCursoPeriodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Presupuesto)) {
            return false;
        }
        Presupuesto other = (Presupuesto) object;
        if ((this.idCursoPeriodo == null && other.idCursoPeriodo != null) || (this.idCursoPeriodo != null && !this.idCursoPeriodo.equals(other.idCursoPeriodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entites.Presupuesto[ idCursoPeriodo=" + idCursoPeriodo + " ]";
    }
    
}
