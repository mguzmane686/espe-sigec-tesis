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
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(fetch = FetchType.LAZY)
    private Persona persona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profesor", fetch = FetchType.LAZY)
    private Collection<DesempenioProfesor> desempenioProfesorCollection;
    @OneToMany(mappedBy = "profesor", fetch = FetchType.LAZY)
    private Collection<CursoEstudiante> cursoEstudianteCollection;

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

    public Collection<DesempenioProfesor> getDesempenioProfesorCollection() {
        return desempenioProfesorCollection;
    }

    public void setDesempenioProfesorCollection(Collection<DesempenioProfesor> desempenioProfesorCollection) {
        this.desempenioProfesorCollection = desempenioProfesorCollection;
    }

    public Collection<CursoEstudiante> getCursoEstudianteCollection() {
        return cursoEstudianteCollection;
    }

    public void setCursoEstudianteCollection(Collection<CursoEstudiante> cursoEstudianteCollection) {
        this.cursoEstudianteCollection = cursoEstudianteCollection;
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
    
}
