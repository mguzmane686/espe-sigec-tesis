package org.espe.sigec.model.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Roberto
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sgct_rh_edc_frm")
public class EducacionFormacion implements Serializable{
   
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "per_id_persona", nullable = false)
    private Integer idPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "edc_primaria", nullable = false, length = 250)
    private String edcPrimaria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "edc_secundaria", nullable = false, length = 250)
    private String edcSecundaria;
    @Size(max = 250)
    @Column(name = "edc_universitaria", length = 250)
    private String edcUniversitaria;
    @Size(max = 250)
    @Column(name = "edc_postgrado", length = 250)
    private String edcPostgrado;
    @Size(max = 250)
    @Column(name = "edc_otro", length = 250)
    private String edcOtro;
    @JoinColumn(name = "per_id_persona", referencedColumnName = "per_id_persona", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Persona persona;

    public EducacionFormacion() {
    }

    public EducacionFormacion(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public EducacionFormacion(Integer idPersona, String edcPrimaria, String edcSecundaria) {
        this.idPersona = idPersona;
        this.edcPrimaria = edcPrimaria;
        this.edcSecundaria = edcSecundaria;
    }
    
    public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	public String getEdcPrimaria() {
        return edcPrimaria;
    }

    public void setEdcPrimaria(String edcPrimaria) {
        this.edcPrimaria = edcPrimaria;
    }

    public String getEdcSecundaria() {
        return edcSecundaria;
    }

    public void setEdcSecundaria(String edcSecundaria) {
        this.edcSecundaria = edcSecundaria;
    }

    public String getEdcUniversitaria() {
        return edcUniversitaria;
    }

    public void setEdcUniversitaria(String edcUniversitaria) {
        this.edcUniversitaria = edcUniversitaria;
    }

    public String getEdcPostgrado() {
        return edcPostgrado;
    }

    public void setEdcPostgrado(String edcPostgrado) {
        this.edcPostgrado = edcPostgrado;
    }

    public String getEdcOtro() {
        return edcOtro;
    }

    public void setEdcOtro(String edcOtro) {
        this.edcOtro = edcOtro;
    }

    public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EducacionFormacion)) {
            return false;
        }
        EducacionFormacion other = (EducacionFormacion) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.EducacionFormacion[ idPersona=" + idPersona + " ]";
    }
}
