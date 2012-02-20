/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Roberto
 */
@Entity
@Table(name = "modulo_curso")
@NamedQueries({
    @NamedQuery(name = "ModuloCurso.findAll", query = "SELECT m FROM ModuloCurso m")})
public class ModuloCurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_modulo_curso")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="modulo_curso_seq")
    @SequenceGenerator(name="modulo_curso_seq", sequenceName="modulo_curso_seq", allocationSize = 1)
    private Integer idModuloCurso;
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 500)
    @Column(name = "generalidades")
    private String generalidades;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nivel")
    private int nivel;
    @OneToMany(mappedBy = "moduloCurso", fetch = FetchType.LAZY)
    private Collection<PensumAcademico> pensumAcademicoCollection;
    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso")
    @ManyToOne(fetch = FetchType.LAZY)
    private Curso curso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "moduloCurso", fetch = FetchType.LAZY)
    private Collection<ModuloCursoPeriodo> moduloCursoPeriodoCollection;
    @Transient 
    private boolean existInBase;
    public ModuloCurso() {
    }

    public ModuloCurso(Integer idModuloCurso) {
        this.idModuloCurso = idModuloCurso;
    }

    public ModuloCurso(Integer idModuloCurso, int nivel) {
        this.idModuloCurso = idModuloCurso;
        this.nivel = nivel;
    }

    public Integer getIdModuloCurso() {
        return idModuloCurso;
    }

    public void setIdModuloCurso(Integer idModuloCurso) {
        this.idModuloCurso = idModuloCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGeneralidades() {
        return generalidades;
    }

    public void setGeneralidades(String generalidades) {
        this.generalidades = generalidades;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Collection<PensumAcademico> getPensumAcademicoCollection() {
        return pensumAcademicoCollection;
    }

    public void setPensumAcademicoCollection(Collection<PensumAcademico> pensumAcademicoCollection) {
        this.pensumAcademicoCollection = pensumAcademicoCollection;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Collection<ModuloCursoPeriodo> getModuloCursoPeriodoCollection() {
        return moduloCursoPeriodoCollection;
    }

    public void setModuloCursoPeriodoCollection(Collection<ModuloCursoPeriodo> moduloCursoPeriodoCollection) {
        this.moduloCursoPeriodoCollection = moduloCursoPeriodoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModuloCurso != null ? idModuloCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModuloCurso)) {
            return false;
        }
        ModuloCurso other = (ModuloCurso) object;
        if ((this.idModuloCurso == null && other.idModuloCurso != null) || (this.idModuloCurso != null && !this.idModuloCurso.equals(other.idModuloCurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.ModuloCurso[ idModuloCurso=" + idModuloCurso + " ]";
    }

	public boolean isExistInBase() {
		return existInBase;
	}

	public void setExistInBase(boolean existInBase) {
		this.existInBase = existInBase;
	}
    
}
