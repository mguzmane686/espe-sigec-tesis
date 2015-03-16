/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
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
@Table(name = "sgct_acd_cur")
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c")})
public class Curso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cur_id_curso")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="curso_seq")
    @SequenceGenerator(name="curso_seq", sequenceName="curso_seq", allocationSize = 1)
    private Integer idCurso;
    @Size(max = 250)
    @Column(name = "cur_nom_cur")
    private String nombreCurso;
    @Column(name = "cur_nom_imp")
    private String nombreImpresion;
    @Size(max = 500)
    @Column(name = "cur_generalidades")
    private String generalidadesCurso;
    @Size(max = 1)
    @Column(name = "cur_estado")
    private String estadoCur;
    
    @Size(max = 1)
    @Column(name = "cur_nivel")
    private String nivelCurso;
    @Size(max = 250)
    @Column(name = "cur_obj_gen")
    private String objetivoGeneral;
    @Size(max = 1024)
    @Column(name = "cur_obj_esp")
    private String objetivosEspecificos;
    @Size(max = 1024)
    @Column(name = "cur_per_par")
    private String perfilParticipante;
    @Size(max = 1024)
    @Column(name = "cur_per_doc")
    private String perfilDocente;
    
//    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
//    private Collection<ModuloCurso> moduloCursoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso", fetch = FetchType.LAZY)
    private Collection<CursoProfesor> cursoProfesorCollection;
    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
    private Collection<CursoPeriodo> cursoPeriodoCollection;
    @JoinColumn(name = "esp_id_especialidad", referencedColumnName = "esp_id_especialidad")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Especialidad especialidad;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso", fetch = FetchType.LAZY)
    private Collection<PensumAcademico> pensumAcademicoCollection;
    
    @Transient
    private boolean showCursoPeriodoCollection;
    
    public Curso() {
    }

    public Curso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getGeneralidadesCurso() {
		return generalidadesCurso;
	}

	public void setGeneralidadesCurso(String generalidadesCurso) {
		this.generalidadesCurso = generalidadesCurso;
	}

    public Collection<CursoPeriodo> getCursoPeriodoCollection() {
        return cursoPeriodoCollection;
    }

    public void setCursoPeriodoCollection(Collection<CursoPeriodo> cursoPeriodoCollection) {
        this.cursoPeriodoCollection = cursoPeriodoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCurso != null ? idCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.idCurso == null && other.idCurso != null) || (this.idCurso != null && !this.idCurso.equals(other.idCurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entites.Curso[ idCurso=" + idCurso + " ]";
    }

	public boolean isShowCursoPeriodoCollection() {
		return showCursoPeriodoCollection;
	}

	public void setShowCursoPeriodoCollection(boolean showCursoPeriodoCollection) {
		this.showCursoPeriodoCollection = showCursoPeriodoCollection;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public String getEstadoCur() {
		return estadoCur;
	}

	public void setEstadoCur(String estadoCur) {
		this.estadoCur = estadoCur;
	}

	public Collection<CursoProfesor> getCursoProfesorCollection() {
		return cursoProfesorCollection;
	}

	public void setCursoProfesorCollection(
			Collection<CursoProfesor> cursoProfesorCollection) {
		this.cursoProfesorCollection = cursoProfesorCollection;
	}

	public String getNivelCurso() {
		return nivelCurso;
	}

	public void setNivelCurso(String nivelCurso) {
		this.nivelCurso = nivelCurso;
	}

	public String getObjetivoGeneral() {
		return objetivoGeneral;
	}

	public void setObjetivoGeneral(String objetivoGeneral) {
		this.objetivoGeneral = objetivoGeneral;
	}

	public String getObjetivosEspecificos() {
		return objetivosEspecificos;
	}

	public void setObjetivosEspecificos(String objetivosEspecificos) {
		this.objetivosEspecificos = objetivosEspecificos;
	}

//	public Collection<ModuloCurso> getModuloCursoCollection() {
//		return moduloCursoCollection;
//	}
//
//	public void setModuloCursoCollection(
//			Collection<ModuloCurso> moduloCursoCollection) {
//		this.moduloCursoCollection = moduloCursoCollection;
//	}

	public String getPerfilParticipante() {
		return perfilParticipante;
	}

	public void setPerfilParticipante(String perfilParticipante) {
		this.perfilParticipante = perfilParticipante;
	}

	public String getPerfilDocente() {
		return perfilDocente;
	}

	public void setPerfilDocente(String perfilDocente) {
		this.perfilDocente = perfilDocente;
	}

	public String getNombreImpresion() {
		return nombreImpresion;
	}

	public void setNombreImpresion(String nombreImpresion) {
		this.nombreImpresion = nombreImpresion;
	}

	public Collection<PensumAcademico> getPensumAcademicoCollection() {
		return pensumAcademicoCollection;
	}

	public void setPensumAcademicoCollection(
			Collection<PensumAcademico> pensumAcademicoCollection) {
		this.pensumAcademicoCollection = pensumAcademicoCollection;
	}
	
}