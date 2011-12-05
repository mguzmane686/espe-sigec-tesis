/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author roberto
 */
@Entity
@Table(name = "historico_curso_estado")
@NamedQueries({
    @NamedQuery(name = "HistoricoCursoEstado.findAll", query = "SELECT h FROM HistoricoCursoEstado h")})
public class HistoricoCursoEstado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_historico_estado")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hist_cur_seq")
    @SequenceGenerator(name="hist_cur_seq", sequenceName="hist_cur_seq", allocationSize = 1)
    private Integer idHistoricoEstado;
    @Size(max = 10)
    @Column(name = "estado")
    private String estado;
    @Size(max = 1)
    @Column(name = "etapa_lanzado")
    private String etapaLanzado;
    @Size(max = 1)
    @Column(name = "etapa_ejecutado")
    private String etapaEjecutado;
    @Size(max = 1)
    @Column(name = "etapa_finalizado")
    private String etapaFinalizado;
    @JoinColumn(name = "id_curso_periodo", referencedColumnName = "id_curso_periodo")
    @ManyToOne(fetch = FetchType.LAZY)
    private CursoPeriodo cursoPeriodo;

    public HistoricoCursoEstado() {
    }

    public HistoricoCursoEstado(Integer idHistoricoEstado) {
        this.idHistoricoEstado = idHistoricoEstado;
    }

    public Integer getIdHistoricoEstado() {
        return idHistoricoEstado;
    }

    public void setIdHistoricoEstado(Integer idHistoricoEstado) {
        this.idHistoricoEstado = idHistoricoEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEtapaLanzado() {
        return etapaLanzado;
    }

    public void setEtapaLanzado(String etapaLanzado) {
        this.etapaLanzado = etapaLanzado;
    }

    public String getEtapaEjecutado() {
        return etapaEjecutado;
    }

    public void setEtapaEjecutado(String etapaEjecutado) {
        this.etapaEjecutado = etapaEjecutado;
    }

    public String getEtapaFinalizado() {
        return etapaFinalizado;
    }

    public void setEtapaFinalizado(String etapaFinalizado) {
        this.etapaFinalizado = etapaFinalizado;
    }

    public CursoPeriodo getCursoPeriodo() {
        return cursoPeriodo;
    }

    public void setCursoPeriodo(CursoPeriodo cursoPeriodo) {
        this.cursoPeriodo = cursoPeriodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistoricoEstado != null ? idHistoricoEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricoCursoEstado)) {
            return false;
        }
        HistoricoCursoEstado other = (HistoricoCursoEstado) object;
        if ((this.idHistoricoEstado == null && other.idHistoricoEstado != null) || (this.idHistoricoEstado != null && !this.idHistoricoEstado.equals(other.idHistoricoEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.HistoricoCursoEstado[ idHistoricoEstado=" + idHistoricoEstado + " ]";
    }
    
}
