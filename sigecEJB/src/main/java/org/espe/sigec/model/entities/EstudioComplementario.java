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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "sgct_rh_prf_est_cmp")
public class EstudioComplementario implements Serializable{
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "prf_sec_est_cmp", nullable = false)
    private Integer secunecialEstCompl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "prf_nom_cur", nullable = false, length = 500)
    private String nombreCurso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "prf_nom_ins", nullable = false, length = 250)
    private String nombreInstitucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "prf_cer_obt", nullable = false, length = 500)
    private String certificacionObtenida;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "prf_duracion", nullable = false, length = 20)
    private String duracion;
    @JoinColumn(name = "prf_id_profesor", referencedColumnName = "prf_id_profesor", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Profesor profesor;

    public EstudioComplementario() {
    }

    public EstudioComplementario(Integer secunecialEstCompl) {
        this.secunecialEstCompl = secunecialEstCompl;
    }

    public EstudioComplementario(Integer secunecialEstCompl, String nombreCurso, String nombreInstitucion, String certificacionObtenida, String duracion) {
        this.secunecialEstCompl = secunecialEstCompl;
        this.nombreCurso = nombreCurso;
        this.nombreInstitucion = nombreInstitucion;
        this.certificacionObtenida = certificacionObtenida;
        this.duracion = duracion;
    }

    public Integer getSecunecialEstCompl() {
		return secunecialEstCompl;
	}

	public void setSecunecialEstCompl(Integer secunecialEstCompl) {
		this.secunecialEstCompl = secunecialEstCompl;
	}

	public String getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	public String getNombreInstitucion() {
		return nombreInstitucion;
	}

	public void setNombreInstitucion(String nombreInstitucion) {
		this.nombreInstitucion = nombreInstitucion;
	}

	public String getCertificacionObtenida() {
		return certificacionObtenida;
	}

	public void setCertificacionObtenida(String certificacionObtenida) {
		this.certificacionObtenida = certificacionObtenida;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public Integer getPrfSecEstCmp() {
        return secunecialEstCompl;
    }

    public void setPrfSecEstCmp(Integer secunecialEstCompl) {
        this.secunecialEstCompl = secunecialEstCompl;
    }

    
    public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (secunecialEstCompl != null ? secunecialEstCompl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstudioComplementario)) {
            return false;
        }
        EstudioComplementario other = (EstudioComplementario) object;
        if ((this.secunecialEstCompl == null && other.secunecialEstCompl != null) || (this.secunecialEstCompl != null && !this.secunecialEstCompl.equals(other.secunecialEstCompl))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.EstudiosComplementarios[ secunecialEstCompl=" + secunecialEstCompl + " ]";
    }
}
