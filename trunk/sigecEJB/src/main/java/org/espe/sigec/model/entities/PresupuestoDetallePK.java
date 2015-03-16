package org.espe.sigec.model.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class PresupuestoDetallePK implements Serializable{
	@Basic(optional = false)
    @Column(name = "pre_id")
    private Integer preId;
    @Basic(optional = false)
    @Column(name = "id_cuenta")
    private String idCuenta;

    public PresupuestoDetallePK() {
    }

    public PresupuestoDetallePK(Integer preId, String idCuenta) {
        this.preId = preId;
        this.idCuenta = idCuenta;
    }

    public Integer getPreId() {
        return preId;
    }

    public void setPreId(Integer preId) {
        this.preId = preId;
    }

    public String getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) preId;
        hash += (idCuenta != null ? idCuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PresupuestoDetallePK)) {
            return false;
        }
        PresupuestoDetallePK other = (PresupuestoDetallePK) object;
        if (this.preId != other.preId) {
            return false;
        }
        if ((this.idCuenta == null && other.idCuenta != null) || (this.idCuenta != null && !this.idCuenta.equals(other.idCuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.PresupuestoDetallePK[ preId=" + preId + ", idCuenta=" + idCuenta + " ]";
    }
}
