/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 *
 * @author Roberto
 */
@Entity
@Table(name = "horario_modulo")
@NamedQueries({
    @NamedQuery(name = "HorarioModulo.findAll", query = "SELECT h FROM HorarioModulo h")})
public class HorarioModulo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HorarioModuloPK horarioModuloPK;
    @Size(max = 30)
    @Column(name = "lunes")
    private String lunes;
    @Size(max = 30)
    @Column(name = "martes")
    private String martes;
    @Size(max = 30)
    @Column(name = "miercoles")
    private String miercoles;
    @Size(max = 30)
    @Column(name = "jueves")
    private String jueves;
    @Size(max = 30)
    @Column(name = "viernes")
    private String viernes;
    @Size(max = 30)
    @Column(name = "sabado")
    private String sabado;
    @Size(max = 30)
    @Column(name = "domingo")
    private String domingo;
    @JoinColumns({
        @JoinColumn(name = "id_curso_periodo", referencedColumnName = "id_curso_periodo", insertable = false, updatable = false),
        @JoinColumn(name = "id_modulo_curso", referencedColumnName = "id_modulo_curso", insertable = false, updatable = false)})
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private ModuloCursoPeriodo moduloCursoPeriodo;

    public HorarioModulo() {
    }

    public HorarioModulo(HorarioModuloPK horarioModuloPK) {
        this.horarioModuloPK = horarioModuloPK;
    }

    public HorarioModulo(BigInteger idCursoPeriodo, int idModuloCurso) {
        this.horarioModuloPK = new HorarioModuloPK(idCursoPeriodo, idModuloCurso);
    }

    public HorarioModuloPK getHorarioModuloPK() {
        return horarioModuloPK;
    }

    public void setHorarioModuloPK(HorarioModuloPK horarioModuloPK) {
        this.horarioModuloPK = horarioModuloPK;
    }

    public String getLunes() {
        return lunes;
    }

    public void setLunes(String lunes) {
        this.lunes = lunes;
    }

    public String getMartes() {
        return martes;
    }

    public void setMartes(String martes) {
        this.martes = martes;
    }

    public String getMiercoles() {
        return miercoles;
    }

    public void setMiercoles(String miercoles) {
        this.miercoles = miercoles;
    }

    public String getJueves() {
        return jueves;
    }

    public void setJueves(String jueves) {
        this.jueves = jueves;
    }

    public String getViernes() {
        return viernes;
    }

    public void setViernes(String viernes) {
        this.viernes = viernes;
    }

    public String getSabado() {
        return sabado;
    }

    public void setSabado(String sabado) {
        this.sabado = sabado;
    }

    public String getDomingo() {
        return domingo;
    }

    public void setDomingo(String domingo) {
        this.domingo = domingo;
    }

    public ModuloCursoPeriodo getModuloCursoPeriodo() {
        return moduloCursoPeriodo;
    }

    public void setModuloCursoPeriodo(ModuloCursoPeriodo moduloCursoPeriodo) {
        this.moduloCursoPeriodo = moduloCursoPeriodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (horarioModuloPK != null ? horarioModuloPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HorarioModulo)) {
            return false;
        }
        HorarioModulo other = (HorarioModulo) object;
        if ((this.horarioModuloPK == null && other.horarioModuloPK != null) || (this.horarioModuloPK != null && !this.horarioModuloPK.equals(other.horarioModuloPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.HorarioModulo[ horarioModuloPK=" + horarioModuloPK + " ]";
    }
    
}
