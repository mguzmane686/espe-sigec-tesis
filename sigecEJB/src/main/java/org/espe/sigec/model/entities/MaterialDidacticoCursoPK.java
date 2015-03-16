package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
*
* @author roberto
*/
@SuppressWarnings("serial")
@Embeddable
public class MaterialDidacticoCursoPK implements Serializable {
	@Basic(optional = false)
    @Column(name = "mtdt_id_material")
    private int idMaterial;
    @Basic(optional = false)
    @Column(name = "id_curso_periodo")
    private BigDecimal idCursoPeriodo;

    public MaterialDidacticoCursoPK() {
    }

    public MaterialDidacticoCursoPK(int idMaterial, BigDecimal idCursoPeriodo) {
        this.idMaterial = idMaterial;
        this.idCursoPeriodo = idCursoPeriodo;
    }

    public int getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(int idMaterial) {
		this.idMaterial = idMaterial;
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
        hash += (int) idMaterial;
        hash += (idCursoPeriodo != null ? idCursoPeriodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaterialDidacticoCursoPK)) {
            return false;
        }
        MaterialDidacticoCursoPK other = (MaterialDidacticoCursoPK) object;
        if (this.idMaterial != other.idMaterial) {
            return false;
        }
        if ((this.idCursoPeriodo == null && other.idCursoPeriodo != null) || (this.idCursoPeriodo != null && !this.idCursoPeriodo.equals(other.idCursoPeriodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.MaterialDidacticoCursoPK[ mtdtIdMaterial=" + idMaterial + ", idCursoPeriodo=" + idCursoPeriodo + " ]";
    }
}
