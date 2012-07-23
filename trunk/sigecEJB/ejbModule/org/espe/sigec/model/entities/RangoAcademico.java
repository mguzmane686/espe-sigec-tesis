package org.espe.sigec.model.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sgct_rh_rng_acd")
/**
 * @author Roberto
 *
 */
public class RangoAcademico implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "rng_acd_sec")
    private String rngAcdSec;
    @Basic(optional = false)
    @Column(name = "rng_acd_nombre")
    private String rngAcdNombre;
    @Column(name = "rng_acd_descripcion")
    private String rngAcdDescripcion;
//    @ManyToMany(mappedBy = "sgctRhRngAcdCollection", fetch = FetchType.LAZY)
//    private Collection<Profesor> lstProfesorCollection;
    @JoinColumn(name = "esp_id_especialidad", referencedColumnName = "esp_id_especialidad")
    @ManyToOne(fetch = FetchType.LAZY)
    private Especialidad especialidad;

    public RangoAcademico() {
    }

    public RangoAcademico(String rngAcdSec) {
        this.rngAcdSec = rngAcdSec;
    }

    public RangoAcademico(String rngAcdSec, String rngAcdNombre) {
        this.rngAcdSec = rngAcdSec;
        this.rngAcdNombre = rngAcdNombre;
    }

    public String getRngAcdSec() {
        return rngAcdSec;
    }

    public void setRngAcdSec(String rngAcdSec) {
        this.rngAcdSec = rngAcdSec;
    }

    public String getRngAcdNombre() {
        return rngAcdNombre;
    }

    public void setRngAcdNombre(String rngAcdNombre) {
        this.rngAcdNombre = rngAcdNombre;
    }

    public String getRngAcdDescripcion() {
        return rngAcdDescripcion;
    }

    public void setRngAcdDescripcion(String rngAcdDescripcion) {
        this.rngAcdDescripcion = rngAcdDescripcion;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rngAcdSec != null ? rngAcdSec.hashCode() : 0);
        return hash;
    }

   

    @Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
    public String toString() {
        return "org.espe.sigec.model.entities.SgctRhRngAcd[rngAcdSec=" + rngAcdSec + "]";
    }

//	public Collection<Profesor> getLstProfesorCollection() {
//		return lstProfesorCollection;
//	}
//
//	public void setLstProfesorCollection(Collection<Profesor> lstProfesorCollection) {
//		this.lstProfesorCollection = lstProfesorCollection;
//	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
	
}
