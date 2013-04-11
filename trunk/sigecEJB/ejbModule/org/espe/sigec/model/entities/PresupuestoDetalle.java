package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sgct_fcn_pres_det")
public class PresupuestoDetalle implements Serializable {
	private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PresupuestoDetallePK presupuestoDetallePK;
    @Column(name = "pre_det_descripcion")
    private String preDetDescripcion;
    @Column(name = "pre_det_valor_inicial")
    private BigDecimal preDetValorInicial;
    @Column(name = "pre_det_valor_variable")
    private BigDecimal preDetValorVariable;

    
    @JoinColumn(name = "pre_id", referencedColumnName = "pre_id", insertable =false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Presupuesto presupuesto;
    
    public PresupuestoDetalle() {
    }

    public PresupuestoDetalle(PresupuestoDetallePK presupuestoDetallePK) {
        this.presupuestoDetallePK = presupuestoDetallePK;
    }

    public PresupuestoDetalle(int preId, String idCuenta) {
        this.presupuestoDetallePK = new PresupuestoDetallePK(preId, idCuenta);
    }

    public PresupuestoDetallePK getPresupuestoDetallePK() {
        return presupuestoDetallePK;
    }

    public void setPresupuestoDetallePK(PresupuestoDetallePK presupuestoDetallePK) {
        this.presupuestoDetallePK = presupuestoDetallePK;
    }

    public String getPreDetDescripcion() {
        return preDetDescripcion;
    }

    public void setPreDetDescripcion(String preDetDescripcion) {
        this.preDetDescripcion = preDetDescripcion;
    }

    public BigDecimal getPreDetValorInicial() {
        return preDetValorInicial;
    }

    public void setPreDetValorInicial(BigDecimal preDetValorInicial) {
        this.preDetValorInicial = preDetValorInicial;
    }

    public BigDecimal getPreDetValorVariable() {
        return preDetValorVariable;
    }

    public void setPreDetValorVariable(BigDecimal preDetValorVariable) {
        this.preDetValorVariable = preDetValorVariable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (presupuestoDetallePK != null ? presupuestoDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PresupuestoDetalle)) {
            return false;
        }
        PresupuestoDetalle other = (PresupuestoDetalle) object;
        if ((this.presupuestoDetallePK == null && other.presupuestoDetallePK != null) || (this.presupuestoDetallePK != null && !this.presupuestoDetallePK.equals(other.presupuestoDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.PresupuestoDetalle[ presupuestoDetallePK=" + presupuestoDetallePK + " ]";
    }

	public Presupuesto getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}
    
    
}
