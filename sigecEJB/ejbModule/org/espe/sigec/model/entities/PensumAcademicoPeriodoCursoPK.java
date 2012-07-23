package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
@SuppressWarnings("serial")
@Embeddable
public class PensumAcademicoPeriodoCursoPK implements Serializable{
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

	@Basic(optional = false)
    @NotNull
    @Column(name = "id_curso_periodo")
    private BigDecimal idCursoPeriodo;
	
    @Basic(optional = false)
    @NotNull
    @Column(name = "ctn_id_ctn_cur")
    private Integer idPensum;

	public BigDecimal getIdCursoPeriodo() {
		return idCursoPeriodo;
	}

	public void setIdCursoPeriodo(BigDecimal idCursoPeriodo) {
		this.idCursoPeriodo = idCursoPeriodo;
	}

	public Integer getIdPensum() {
		return idPensum;
	}

	public void setIdPensum(Integer idPensum) {
		this.idPensum = idPensum;
	}

	public PensumAcademicoPeriodoCursoPK() {
		
	}
    
    
}
