/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 *
 * @author Roberto
 */
@Entity
@Table(name = "modulo_curso_periodo")
@NamedQueries({
    @NamedQuery(name = "ModuloCursoPeriodo.findAll", query = "SELECT m FROM ModuloCursoPeriodo m")})
public class ModuloCursoPeriodo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ModuloCursoPeriodoPK moduloCursoPeriodoPK;
    @Column(name = "fecha_inicio_inscripcion")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioInscripcion;
    @Column(name = "fecha_fin_inscripcion")
    @Temporal(TemporalType.DATE)
    private Date fechaFinInscripcion;
    @Column(name = "fecha_inicio_modulo")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioModulo;
    @Column(name = "fecha_fin_modulo")
    @Temporal(TemporalType.DATE)
    private Date fechaFinModulo;
    @Size(max = 20)
    @Column(name = "duracion_horas")
    private String duracionHoras;
    @Size(max = 1024)
    @Column(name = "lugar_modulo")
    private String lugarModulo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "moduloCursoPeriodo", fetch = FetchType.LAZY)
    private Collection<CursoEstudiante> cursoEstudianteCollection;
    @JoinColumn(name = "id_modulo_curso", referencedColumnName = "id_modulo_curso", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ModuloCurso moduloCurso;
    @JoinColumns({
        @JoinColumn(name = "id_curso", referencedColumnName = "id_curso"),
        @JoinColumn(name = "id_profesor", referencedColumnName = "id_profesor")})
    @ManyToOne(fetch = FetchType.LAZY)
    private CursoProfesor cursoProfesor;
    @JoinColumn(name = "id_curso_periodo", referencedColumnName = "id_curso_periodo", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CursoPeriodo cursoPeriodo;
    @JoinColumn(name = "id_aula", referencedColumnName = "id_aula")
    @ManyToOne(fetch = FetchType.LAZY)
    private Aula aula;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "moduloCursoPeriodo", fetch = FetchType.LAZY)
    private HorarioModulo horarioModulo;

    public ModuloCursoPeriodo() {
    }

    public ModuloCursoPeriodo(ModuloCursoPeriodoPK moduloCursoPeriodoPK) {
        this.moduloCursoPeriodoPK = moduloCursoPeriodoPK;
    }

    public ModuloCursoPeriodo(BigInteger idCursoPeriodo, int idModuloCurso) {
        this.moduloCursoPeriodoPK = new ModuloCursoPeriodoPK(idCursoPeriodo, idModuloCurso);
    }

    public ModuloCursoPeriodoPK getModuloCursoPeriodoPK() {
        return moduloCursoPeriodoPK;
    }

    public void setModuloCursoPeriodoPK(ModuloCursoPeriodoPK moduloCursoPeriodoPK) {
        this.moduloCursoPeriodoPK = moduloCursoPeriodoPK;
    }

    public Date getFechaInicioInscripcion() {
        return fechaInicioInscripcion;
    }

    public void setFechaInicioInscripcion(Date fechaInicioInscripcion) {
        this.fechaInicioInscripcion = fechaInicioInscripcion;
    }

    public Date getFechaFinInscripcion() {
        return fechaFinInscripcion;
    }

    public void setFechaFinInscripcion(Date fechaFinInscripcion) {
        this.fechaFinInscripcion = fechaFinInscripcion;
    }

    public Date getFechaInicioModulo() {
        return fechaInicioModulo;
    }

    public void setFechaInicioModulo(Date fechaInicioModulo) {
        this.fechaInicioModulo = fechaInicioModulo;
    }

    public Date getFechaFinModulo() {
        return fechaFinModulo;
    }

    public void setFechaFinModulo(Date fechaFinModulo) {
        this.fechaFinModulo = fechaFinModulo;
    }

    public String getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(String duracionHoras) {
        this.duracionHoras = duracionHoras;
    }

    public String getLugarModulo() {
        return lugarModulo;
    }

    public void setLugarModulo(String lugarModulo) {
        this.lugarModulo = lugarModulo;
    }

    public Collection<CursoEstudiante> getCursoEstudianteCollection() {
        return cursoEstudianteCollection;
    }

    public void setCursoEstudianteCollection(Collection<CursoEstudiante> cursoEstudianteCollection) {
        this.cursoEstudianteCollection = cursoEstudianteCollection;
    }

    public ModuloCurso getModuloCurso() {
        return moduloCurso;
    }

    public void setModuloCurso(ModuloCurso moduloCurso) {
        this.moduloCurso = moduloCurso;
    }

    public CursoProfesor getCursoProfesor() {
        return cursoProfesor;
    }

    public void setCursoProfesor(CursoProfesor cursoProfesor) {
        this.cursoProfesor = cursoProfesor;
    }

    public CursoPeriodo getCursoPeriodo() {
        return cursoPeriodo;
    }

    public void setCursoPeriodo(CursoPeriodo cursoPeriodo) {
        this.cursoPeriodo = cursoPeriodo;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public HorarioModulo getHorarioModulo() {
        return horarioModulo;
    }

    public void setHorarioModulo(HorarioModulo horarioModulo) {
        this.horarioModulo = horarioModulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (moduloCursoPeriodoPK != null ? moduloCursoPeriodoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModuloCursoPeriodo)) {
            return false;
        }
        ModuloCursoPeriodo other = (ModuloCursoPeriodo) object;
        if ((this.moduloCursoPeriodoPK == null && other.moduloCursoPeriodoPK != null) || (this.moduloCursoPeriodoPK != null && !this.moduloCursoPeriodoPK.equals(other.moduloCursoPeriodoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.ModuloCursoPeriodo[ moduloCursoPeriodoPK=" + moduloCursoPeriodoPK + " ]";
    }
    
}
