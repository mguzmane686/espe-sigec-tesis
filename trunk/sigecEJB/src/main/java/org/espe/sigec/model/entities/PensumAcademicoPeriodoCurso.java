package org.espe.sigec.model.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Roberto
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sgct_acd_ctn_cur_per")
public class PensumAcademicoPeriodoCurso implements Serializable{
	@EmbeddedId
	private PensumAcademicoPeriodoCursoPK pensumAcademicoPeriodoCursoPK;

	@JoinColumn(name = "id_curso_periodo", referencedColumnName = "id_curso_periodo", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private CursoPeriodo cursoPeriodo;
	
	public PensumAcademicoPeriodoCurso() {
	}

	

	public CursoPeriodo getCursoPeriodo() {
		return cursoPeriodo;
	}

	public void setCursoPeriodo(CursoPeriodo cursoPeriodo) {
		this.cursoPeriodo = cursoPeriodo;
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



	public PensumAcademicoPeriodoCursoPK getPensumAcademicoPeriodoCursoPK() {
		return pensumAcademicoPeriodoCursoPK;
	}

	public void setPensumAcademicoPeriodoCursoPK(
			PensumAcademicoPeriodoCursoPK pensumAcademicoPeriodoCursoPK) {
		this.pensumAcademicoPeriodoCursoPK = pensumAcademicoPeriodoCursoPK;
	}
	
}
