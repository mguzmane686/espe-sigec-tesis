package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class EncuestaPK implements Serializable{
	 @Basic(optional = false)
	    @Column(name = "prg_id")
	    private int prgId;
	    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	    @Basic(optional = false)
	    @Column(name = "id_curso_periodo")
	    private BigDecimal idCursoPeriodo;
	    @Basic(optional = false)
	    @Column(name = "est_id_estudiante")
	    private int estIdEstudiante;

	    public EncuestaPK() {
	    }

	    public EncuestaPK(int prgId, BigDecimal idCursoPeriodo, int estIdEstudiante) {
	        this.prgId = prgId;
	        this.idCursoPeriodo = idCursoPeriodo;
	        this.estIdEstudiante = estIdEstudiante;
	    }

	    public int getPrgId() {
	        return prgId;
	    }

	    public void setPrgId(int prgId) {
	        this.prgId = prgId;
	    }

	    public BigDecimal getIdCursoPeriodo() {
	        return idCursoPeriodo;
	    }

	    public void setIdCursoPeriodo(BigDecimal idCursoPeriodo) {
	        this.idCursoPeriodo = idCursoPeriodo;
	    }

	    public int getEstIdEstudiante() {
	        return estIdEstudiante;
	    }

	    public void setEstIdEstudiante(int estIdEstudiante) {
	        this.estIdEstudiante = estIdEstudiante;
	    }

	    @Override
	    public int hashCode() {
	        int hash = 0;
	        hash += (int) prgId;
	        hash += (idCursoPeriodo != null ? idCursoPeriodo.hashCode() : 0);
	        hash += (int) estIdEstudiante;
	        return hash;
	    }

	    @Override
	    public boolean equals(Object object) {
	        // TODO: Warning - this method won't work in the case the id fields are not set
	        if (!(object instanceof EncuestaPK)) {
	            return false;
	        }
	        EncuestaPK other = (EncuestaPK) object;
	        if (this.prgId != other.prgId) {
	            return false;
	        }
	        if ((this.idCursoPeriodo == null && other.idCursoPeriodo != null) || (this.idCursoPeriodo != null && !this.idCursoPeriodo.equals(other.idCursoPeriodo))) {
	            return false;
	        }
	        if (this.estIdEstudiante != other.estIdEstudiante) {
	            return false;
	        }
	        return true;
	    }

	    @Override
	    public String toString() {
	        return "org.espe.sigec.model.entities.SgctStdEncuestaPK[ prgId=" + prgId + ", idCursoPeriodo=" + idCursoPeriodo + ", estIdEstudiante=" + estIdEstudiante + " ]";
	    }
}
