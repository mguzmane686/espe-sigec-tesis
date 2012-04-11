/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author roberto
 */
@Entity
@Table(name = "profesor")
@NamedQueries({
    @NamedQuery(name = "Profesor.findAll", query = "SELECT p FROM Profesor p")})
public class Profesor implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_profesor")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="prof_seq")
    @SequenceGenerator(name="prof_seq", sequenceName="prof_seq", allocationSize = 1)
    private BigDecimal idProfesor;
    @Size(max = 250)
    @Column(name = "titulo")
    private String titulo;
    
    @Size(max = 1)
    @Column(name = "titulo_nivel_tres")
    private String tituloNivelTres;
    @Size(max = 1)
    @Column(name = "expe_anio_docente")
    private String expeAnioDocente;
    @Size(max = 1)
    @Column(name = "expe_anio_area")
    private String expeAnioArea;
    @Size(max = 1)
    @Column(name = "estado_seleccion")
    private String estadoSeleccion;
    @Size(max = 1)
    @Column(name = "estado_prof")
    private String estadoProf;
    @Column(name = "experiencia")
    private Integer experiencia;
    @Size(max = 1)
    @Column(name = "tiempo_comp")
    private String tiempoComp;
    @Size(max = 1024)
    @Column(name = "observacion")
    private String observacion;
    
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(fetch = FetchType.LAZY)
    private Persona persona;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profesor", fetch = FetchType.LAZY)
    private Collection<CursoProfesor> cursoProfesorCollection;
    
    @JoinColumn(name = "id_especialidad", referencedColumnName = "id_especialidad")
    @ManyToOne(fetch = FetchType.LAZY)
    private Especialidad especialidad;

    public Profesor() {
    }

    public Profesor(BigDecimal idProfesor) {
        this.idProfesor = idProfesor;
    }

    public BigDecimal getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(BigDecimal idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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
        hash += (idProfesor != null ? idProfesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profesor)) {
            return false;
        }
        Profesor other = (Profesor) object;
        if ((this.idProfesor == null && other.idProfesor != null) || (this.idProfesor != null && !this.idProfesor.equals(other.idProfesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entites.Profesor[ idProfesor=" + idProfesor + " ]";
    }

	public String getTituloNivelTres() {
		return tituloNivelTres;
	}

	public void setTituloNivelTres(String tituloNivelTres) {
		this.tituloNivelTres = tituloNivelTres;
	}

	public String getExpeAnioDocente() {
		return expeAnioDocente;
	}

	public void setExpeAnioDocente(String expeAnioDocente) {
		this.expeAnioDocente = expeAnioDocente;
	}

	public String getExpeAnioArea() {
		return expeAnioArea;
	}

	public void setExpeAnioArea(String expeAnioArea) {
		this.expeAnioArea = expeAnioArea;
	}
	@Transient
	public double getPonderacion(){
		double ponderacion =0;
		try {
			if(getExpeAnioArea()!=null){
				ponderacion += (Integer.parseInt(getExpeAnioArea())*10);
			}
			if(getExpeAnioDocente() !=null){
				ponderacion += (Integer.parseInt(getExpeAnioDocente())*10);
			}
			if(getTituloNivelTres() !=null){
				ponderacion += (Integer.parseInt(getTituloNivelTres())*10);
			}
			
			ponderacion = Math.round((ponderacion*100)/100);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ponderacion;
	}
	
	public String getEstadoSeleccion() {
		return estadoSeleccion;
	}

	public void setEstadoSeleccion(String estadoSeleccion) {
		this.estadoSeleccion = estadoSeleccion;
	}

	public String getEstadoProf() {
		return estadoProf;
	}

	public void setEstadoProf(String estadoProf) {
		this.estadoProf = estadoProf;
	}

	public Integer getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(Integer experiencia) {
		this.experiencia = experiencia;
	}

	public String getTiempoComp() {
		return tiempoComp;
	}

	public void setTiempoComp(String tiempoComp) {
		this.tiempoComp = tiempoComp;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Collection<CursoProfesor> getCursoProfesorCollection() {
		return cursoProfesorCollection;
	}

	public void setCursoProfesorCollection(
			Collection<CursoProfesor> cursoProfesorCollection) {
		this.cursoProfesorCollection = cursoProfesorCollection;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
	
}
