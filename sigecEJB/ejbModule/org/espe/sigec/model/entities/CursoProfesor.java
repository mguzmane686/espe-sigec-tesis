/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author roberto
 */
@Entity
@Table(name = "sgct_acd_curso_profesor")
@NamedQueries({
    @NamedQuery(name = "CursoProfesor.findAll", query = "SELECT c FROM CursoProfesor c")})
public class CursoProfesor implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CursoProfesorPK cursoProfesorPK;
    @Size(max = 1)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "prf_id_profesor", referencedColumnName = "prf_id_profesor", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Profesor profesor;
    @JoinColumn(name = "cur_id_curso", referencedColumnName = "cur_id_curso", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Curso curso;

//    @OneToMany(mappedBy = "cursoProfesor", fetch = FetchType.LAZY)
//    private Collection<DesempenioProfesor> desempenioProfesorCollection;
//    @OneToMany(mappedBy = "cursoProfesor", fetch = FetchType.LAZY)
//    private Collection<ModuloCursoPeriodo> moduloCursoPeriodoCollection;
    
    public CursoProfesor() {
    }

    public CursoProfesor(CursoProfesorPK cursoProfesorPK) {
        this.cursoProfesorPK = cursoProfesorPK;
    }

    public CursoProfesor(int idCurso, BigInteger idProfesor) {
        this.cursoProfesorPK = new CursoProfesorPK(idCurso, idProfesor);
    }

    public CursoProfesorPK getCursoProfesorPK() {
        return cursoProfesorPK;
    }

    public void setCursoProfesorPK(CursoProfesorPK cursoProfesorPK) {
        this.cursoProfesorPK = cursoProfesorPK;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

//    public Collection<DesempenioProfesor> getDesempenioProfesorCollection() {
//        return desempenioProfesorCollection;
//    }
//
//    public void setDesempenioProfesorCollection(Collection<DesempenioProfesor> desempenioProfesorCollection) {
//        this.desempenioProfesorCollection = desempenioProfesorCollection;
//    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cursoProfesorPK != null ? cursoProfesorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CursoProfesor)) {
            return false;
        }
        CursoProfesor other = (CursoProfesor) object;
        if ((this.cursoProfesorPK == null && other.cursoProfesorPK != null) || (this.cursoProfesorPK != null && !this.cursoProfesorPK.equals(other.cursoProfesorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.CursoProfesor[ cursoProfesorPK=" + cursoProfesorPK + " ]";
    }

//	public Collection<ModuloCursoPeriodo> getModuloCursoPeriodoCollection() {
//		return moduloCursoPeriodoCollection;
//	}
//
//	public void setModuloCursoPeriodoCollection(
//			Collection<ModuloCursoPeriodo> moduloCursoPeriodoCollection) {
//		this.moduloCursoPeriodoCollection = moduloCursoPeriodoCollection;
//	}
    
}
