/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author roberto
 */
@Entity
@Table(name = "asistencia_estudiante")
@NamedQueries({
    @NamedQuery(name = "AsistenciaEstudiante.findAll", query = "SELECT a FROM AsistenciaEstudiante a")})
public class AsistenciaEstudiante implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AsistenciaEstudiantePK asistenciaEstudiantePK;
    @Column(name = "fecha_control")
    @Temporal(TemporalType.DATE)
    private Date fechaControl;
    @Size(max = 10)
    @Column(name = "estado_asistencia")
    private String estadoAsistencia;
    @JoinColumns({
        @JoinColumn(name = "id_estudiante", referencedColumnName = "id_estudiante" , insertable = false, updatable = false),
        @JoinColumn(name = "id_curso_periodo", referencedColumnName = "id_curso_periodo", insertable = false, updatable = false),
        @JoinColumn(name = "id_modulo_curso", referencedColumnName = "id_modulo_curso" , insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CursoEstudiante cursoEstudiante;

    public AsistenciaEstudiante() {
    }

    public AsistenciaEstudiante(AsistenciaEstudiantePK asistenciaEstudiantePK) {
        this.asistenciaEstudiantePK = asistenciaEstudiantePK;
    }

    public AsistenciaEstudiante(int idAsistencia, BigInteger idCursoPeriodo) {
        this.asistenciaEstudiantePK = new AsistenciaEstudiantePK(idAsistencia, idCursoPeriodo);
    }

    public AsistenciaEstudiantePK getAsistenciaEstudiantePK() {
        return asistenciaEstudiantePK;
    }

    public void setAsistenciaEstudiantePK(AsistenciaEstudiantePK asistenciaEstudiantePK) {
        this.asistenciaEstudiantePK = asistenciaEstudiantePK;
    }

    public Date getFechaControl() {
        return fechaControl;
    }

    public void setFechaControl(Date fechaControl) {
        this.fechaControl = fechaControl;
    }

    public String getEstadoAsistencia() {
        return estadoAsistencia;
    }

    public void setEstadoAsistencia(String estadoAsistencia) {
        this.estadoAsistencia = estadoAsistencia;
    }

    public CursoEstudiante getCursoEstudiante() {
        return cursoEstudiante;
    }

    public void setCursoEstudiante(CursoEstudiante cursoEstudiante) {
        this.cursoEstudiante = cursoEstudiante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asistenciaEstudiantePK != null ? asistenciaEstudiantePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsistenciaEstudiante)) {
            return false;
        }
        AsistenciaEstudiante other = (AsistenciaEstudiante) object;
        if ((this.asistenciaEstudiantePK == null && other.asistenciaEstudiantePK != null) || (this.asistenciaEstudiantePK != null && !this.asistenciaEstudiantePK.equals(other.asistenciaEstudiantePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entites.AsistenciaEstudiante[ asistenciaEstudiantePK=" + asistenciaEstudiantePK + " ]";
    }
    
}
