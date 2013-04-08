/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

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
@Table(name = "sgct_fnc_det_pres_cur")
@NamedQueries({
    @NamedQuery(name = "DetallePresupuestoCurso.findAll", query = "SELECT d FROM DetallePresupuestoCurso d")})
public class DetallePresupuestoCurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetallePresupuestoCursoPK detallePresupuestoCursoPK;
    @Size(max = 100)
    @Column(name = "det_detalle")
    private String detalle;
    @Column(name = "det_cantidad")
    private Double cantidad;
    @Size(max = 25)
    @Column(name = "det_unidad")
    private String unidad;
    @Column(name = "det_costo_unitario")
    private BigDecimal costoUnitario;
    @Column(name = "det_costo_total")
    private BigDecimal costoTotal;
    
    @Column(name = "id_cuenta")
    private String idCuenta;
    
    @JoinColumn(name = "id_curso_periodo", referencedColumnName = "id_curso_periodo", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PresupuestoCurso presupuestoCurso;
    
    @Transient
    private String descripcionCatalogo;
    @Transient
    public BigDecimal CostoTotalUSD;
    public DetallePresupuestoCurso() {
    }

    public DetallePresupuestoCurso(DetallePresupuestoCursoPK detallePresupuestoCursoPK) {
        this.detallePresupuestoCursoPK = detallePresupuestoCursoPK;
    }
    
    public DetallePresupuestoCurso(int preId, BigInteger idCursoPeriodo, BigInteger detIdDetalle, String detCodElemento) {
        this.detallePresupuestoCursoPK = new DetallePresupuestoCursoPK(preId, idCursoPeriodo, detIdDetalle, detCodElemento);
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

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    

    public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getCostoUnitario() {
		return costoUnitario;
	}

	public void setCostoUnitario(BigDecimal costoUnitario) {
		this.costoUnitario = costoUnitario;
	}

	public BigDecimal getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(BigDecimal costoTotal) {
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
	
	@Transient
	public BigDecimal getCostoTotalUSD(){
		BigDecimal a = null;
		BigDecimal b = null;
		if(costoUnitario != null ){
			a = new BigDecimal(costoUnitario.doubleValue());
		}else{
			a = new BigDecimal(0);
		}
		
		if(cantidad != null)		{
			b = new BigDecimal(cantidad.doubleValue());
		}else{
			b = new BigDecimal(0);
		}
		BigDecimal total = a.multiply(b);
		total = total.setScale(2, RoundingMode.HALF_UP);
		
		return  total;
	}

	public void setCostoTotalUSD(BigDecimal costoTotalUSD) {
		CostoTotalUSD = costoTotalUSD;
	}

	public String getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	} 

	
}
