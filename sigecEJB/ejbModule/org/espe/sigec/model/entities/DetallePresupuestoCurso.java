/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author roberto
 */
@Entity
@Table(name = "detalle_presupuesto_curso")
@NamedQueries({
    @NamedQuery(name = "DetallePresupuestoCurso.findAll", query = "SELECT d FROM DetallePresupuestoCurso d")})
public class DetallePresupuestoCurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetallePresupuestoCursoPK detallePresupuestoCursoPK;
    @Size(max = 100)
    @Column(name = "detalle")
    private String detalle;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Size(max = 25)
    @Column(name = "unidad")
    private String unidad;
    @Column(name = "costo_unitario")
    private BigInteger costoUnitario;
    @Column(name = "costo_total")
    private BigInteger costoTotal;
    
    @JoinColumn(name = "id_curso_periodo", referencedColumnName = "id_curso_periodo", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PresupuestoCurso presupuestoCurso;
    
    @Transient
    private String descripcionCatalogo;
    
    public DetallePresupuestoCurso() {
    }

    public DetallePresupuestoCurso(DetallePresupuestoCursoPK detallePresupuestoCursoPK) {
        this.detallePresupuestoCursoPK = detallePresupuestoCursoPK;
    }
    
    public DetallePresupuestoCurso(BigInteger idDetalle, BigInteger idCursoPeriodo, String codElemento) {
        this.detallePresupuestoCursoPK = new DetallePresupuestoCursoPK(idDetalle, idCursoPeriodo, codElemento);
    }
    public DetallePresupuestoCursoPK getDetallePresupuestoCursoPK() {
        return detallePresupuestoCursoPK;
    }

    public void setDetallePresupuestoCursoPK(DetallePresupuestoCursoPK detallePresupuestoCursoPK) {
        this.detallePresupuestoCursoPK = detallePresupuestoCursoPK;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public BigInteger getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(BigInteger costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public BigInteger getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(BigInteger costoTotal) {
        this.costoTotal = costoTotal;
    }

    public PresupuestoCurso getPresupuestoCurso() {
        return presupuestoCurso;
    }

    public void setPresupuestoCurso(PresupuestoCurso presupuestoCurso) {
        this.presupuestoCurso = presupuestoCurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detallePresupuestoCursoPK != null ? detallePresupuestoCursoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallePresupuestoCurso)) {
            return false;
        }
        DetallePresupuestoCurso other = (DetallePresupuestoCurso) object;
        if ((this.detallePresupuestoCursoPK == null && other.detallePresupuestoCursoPK != null) || (this.detallePresupuestoCursoPK != null && !this.detallePresupuestoCursoPK.equals(other.detallePresupuestoCursoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.DetallePresupuestoCurso[ detallePresupuestoCursoPK=" + detallePresupuestoCursoPK + " ]";
    }

	public String getDescripcionCatalogo() {
		return descripcionCatalogo;
	}

	public void setDescripcionCatalogo(String descripcionCatalogo) {
		this.descripcionCatalogo = descripcionCatalogo;
	}    
}
