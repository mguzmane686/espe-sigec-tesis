/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author roberto
 */
@Entity
@Table(name = "sgct_acd_cur_est")
@NamedQueries({
    @NamedQuery(name = "CursoEstudiante.findAll", query = "SELECT c FROM CursoEstudiante c")})
public class CursoEstudiante implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CursoEstudiantePK cursoEstudiantePK;
    @Size(max = 10)
    @Column(name = "estado_pago")
    private String estadoPago;
    
    @Column(name = "prg_id")
    private Integer idPrograma; 
    
//    @JoinColumns({
//        @JoinColumn(name = "id_curso_periodo", referencedColumnName = "id_curso_periodo", insertable = false, updatable = false),
//        @JoinColumn(name = "id_modulo_curso", referencedColumnName = "id_modulo_curso", insertable = false, updatable = false)})
//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    private ModuloCursoPeriodo moduloCursoPeriodo;
    @JoinColumn(name = "est_id_estudiante", referencedColumnName = "est_id_estudiante", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estudiante estudiante;
    @OneToMany(mappedBy = "cursoEstudiante", fetch = FetchType.LAZY)
    private Collection<CalificacionEstudiante> calificacionEstudianteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cursoEstudiante", fetch = FetchType.LAZY)
    private Collection<AsistenciaEstudiante> asistenciaEstudianteCollection;

    @JoinColumns({
        @JoinColumn(name = "prg_id", referencedColumnName = "prg_id", insertable = false, updatable = false),
        @JoinColumn(name = "id_curso_periodo", referencedColumnName = "id_curso_periodo", insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ProgramaCurso programaCurso;
    
    public CursoEstudiante() {
    }

    public CursoEstudiante(CursoEstudiantePK cursoEstudiantePK) {
        this.cursoEstudiantePK = cursoEstudiantePK;
    }

    
    public CursoEstudiante(int idEstudiante, BigInteger idCursoPeriodo) {
        this.cursoEstudiantePK = new CursoEstudiantePK(idEstudiante, idCursoPeriodo);
    }

    public CursoEstudiantePK getCursoEstudiantePK() {
        return cursoEstudiantePK;
    }

    public void setCursoEstudiantePK(CursoEstudiantePK cursoEstudiantePK) {
        this.cursoEstudiantePK = cursoEstudiantePK;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Collection<CalificacionEstudiante> getCalificacionEstudianteCollection() {
        return calificacionEstudianteCollection;
    }

    public void setCalificacionEstudianteCollection(Collection<CalificacionEstudiante> calificacionEstudianteCollection) {
        this.calificacionEstudianteCollection = calificacionEstudianteCollection;
    }

    public Collection<AsistenciaEstudiante> getAsistenciaEstudianteCollection() {
        return asistenciaEstudianteCollection;
    }

    public void setAsistenciaEstudianteCollection(Collection<AsistenciaEstudiante> asistenciaEstudianteCollection) {
        this.asistenciaEstudianteCollection = asistenciaEstudianteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cursoEstudiantePK != null ? cursoEstudiantePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CursoEstudiante)) {
            return false;
        }
        CursoEstudiante other = (CursoEstudiante) object;
        if ((this.cursoEstudiantePK == null && other.cursoEstudiantePK != null) || (this.cursoEstudiantePK != null && !this.cursoEstudiantePK.equals(other.cursoEstudiantePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entites.CursoEstudiante[ cursoEstudiantePK=" + cursoEstudiantePK + " ]";
    }

	public ProgramaCurso getProgramaCurso() {
		return programaCurso;
	}

	public void setProgramaCurso(ProgramaCurso programaCurso) {
		this.programaCurso = programaCurso;
	}

	public Integer getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}

	

//	public ModuloCursoPeriodo getModuloCursoPeriodo() {
//		return moduloCursoPeriodo;
//	}
//
//	public void setModuloCursoPeriodo(ModuloCursoPeriodo moduloCursoPeriodo) {
//		this.moduloCursoPeriodo = moduloCursoPeriodo;
//	}
    
}
