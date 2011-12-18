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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
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
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "cod_elemento")
    private String codElemento;
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
    @JoinColumn(name = "id_curso_periodo", referencedColumnName = "id_curso_periodo")
    @ManyToOne(fetch = FetchType.LAZY)
    private PresupuestoCurso presupuestoCurso;

    @Transient
    private String descripcionCatalogo;
    
    public DetallePresupuestoCurso() {
    }

    public DetallePresupuestoCurso(String codElemento) {
        this.codElemento = codElemento;
    }

    public String getCodElemento() {
        return codElemento;
    }

    public void setCodElemento(String codElemento) {
        this.codElemento = codElemento;
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
        hash += (codElemento != null ? codElemento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallePresupuestoCurso)) {
            return false;
        }
        DetallePresupuestoCurso other = (DetallePresupuestoCurso) object;
        if ((this.codElemento == null && other.codElemento != null) || (this.codElemento != null && !this.codElemento.equals(other.codElemento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.DetallePresupuestoCurso[ codElemento=" + codElemento + " ]";
    }

	public String getDescripcionCatalogo() {
		return descripcionCatalogo;
	}

	public void setDescripcionCatalogo(String descripcionCatalogo) {
		this.descripcionCatalogo = descripcionCatalogo;
	}
    
}
