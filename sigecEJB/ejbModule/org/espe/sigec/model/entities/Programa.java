/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Roberto
 */
@Entity
@Table(name = "programa")
@NamedQueries({
    @NamedQuery(name = "Programa.findAll", query = "SELECT p FROM Programa p")})
public class Programa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_programa")
    private Integer idPrograma;
    @Size(max = 250)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 1024)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "inicio")
    @Temporal(TemporalType.DATE)
    private Date inicio;
    @Column(name = "finalizacion")
    @Temporal(TemporalType.DATE)
    private Date finalizacion;
    @Column(name = "estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programa", fetch = FetchType.LAZY)
    private Collection<ProgramaCurso> programaCursoCollection;
    
    public Programa() {
    }

    public Programa(Integer idPrograma) {
        this.idPrograma = idPrograma;
    }

    public Integer getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Integer idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFinalizacion() {
        return finalizacion;
    }

    public void setFinalizacion(Date finalizacion) {
        this.finalizacion = finalizacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrograma != null ? idPrograma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Programa)) {
            return false;
        }
        Programa other = (Programa) object;
        if ((this.idPrograma == null && other.idPrograma != null) || (this.idPrograma != null && !this.idPrograma.equals(other.idPrograma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.Programa[ idPrograma=" + idPrograma + " ]";
    }

	public Collection<ProgramaCurso> getProgramaCursoCollection() {
		return programaCursoCollection;
	}

	public void setProgramaCursoCollection(
			Collection<ProgramaCurso> programaCursoCollection) {
		this.programaCursoCollection = programaCursoCollection;
	}
    
}
