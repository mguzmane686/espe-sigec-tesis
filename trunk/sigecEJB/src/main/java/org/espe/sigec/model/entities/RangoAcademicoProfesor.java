package org.espe.sigec.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
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
@Table(name = "sgct_rh_rng_acd")
public class RangoAcademicoProfesor implements Serializable{
	@EmbeddedId
	private RangoAcademicoProfesorPK academicoProfesorPK;
	
	

	@Column(name = "prf_rng_estado")
    private String estado;

	
//	@JoinColumn(name = "prf_id_profesor", referencedColumnName = "prf_id_profesor")
//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//	private Profesor profesor;
//	
//	@JoinColumn(name = "rng_acd_sec", referencedColumnName = "rng_acd_sec")
//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//	private RangoAcademico rangoAcademico;
	public RangoAcademicoProfesor() {
		super();
	}

	public RangoAcademicoProfesorPK getAcademicoProfesorPK() {
		return academicoProfesorPK;
	}

	public void setAcademicoProfesorPK(RangoAcademicoProfesorPK academicoProfesorPK) {
		this.academicoProfesorPK = academicoProfesorPK;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

//	public Profesor getProfesor() {
//		return profesor;
//	}
//
//	public void setProfesor(Profesor profesor) {
//		this.profesor = profesor;
//	}
//
//	public RangoAcademico getRangoAcademico() {
//		return rangoAcademico;
//	}
//
//	public void setRangoAcademico(RangoAcademico rangoAcademico) {
//		this.rangoAcademico = rangoAcademico;
//	}
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
