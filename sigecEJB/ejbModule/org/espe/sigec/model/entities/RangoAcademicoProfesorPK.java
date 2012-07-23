package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * @author Roberto
 *
 */
@SuppressWarnings("serial")
@Embeddable
public class RangoAcademicoProfesorPK implements Serializable{
	@Basic(optional = false)
    @NotNull
    @Column(name = "rng_acd_sec")
    private String rngAcdSec;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prf_id_profesor")
    private BigDecimal idProfesor;
	
    public RangoAcademicoProfesorPK() {
		super();
	}

	public String getRngAcdSec() {
		return rngAcdSec;
	}

	public void setRngAcdSec(String rngAcdSec) {
		this.rngAcdSec = rngAcdSec;
	}

	public BigDecimal getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(BigDecimal idProfesor) {
		this.idProfesor = idProfesor;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
    
    
}
