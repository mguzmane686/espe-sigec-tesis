/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Roberto
 */
@Entity
@Table(name = "sgct_doc_invitacion_porf", catalog = "sigec", schema = "public")
@NamedQueries({
    @NamedQuery(name = "InvitacionDocente.findAll", query = "SELECT s FROM InvitacionDocente s"),
    @NamedQuery(name = "InvitacionDocente.findByDocNumInvit", query = "SELECT s FROM InvitacionDocente s WHERE s.invitacionDocentePK.docNumInvit = :docNumInvit"),
    @NamedQuery(name = "InvitacionDocente.findByPrfIdProfesor", query = "SELECT s FROM InvitacionDocente s WHERE s.invitacionDocentePK.prfIdProfesor = :prfIdProfesor"),
    @NamedQuery(name = "InvitacionDocente.findByIdCursoPeriodo", query = "SELECT s FROM InvitacionDocente s WHERE s.invitacionDocentePK.idCursoPeriodo = :idCursoPeriodo"),
    @NamedQuery(name = "InvitacionDocente.findByDocFechaInivit", query = "SELECT s FROM InvitacionDocente s WHERE s.fechaInvitacion = :docFechaInivit"),
    @NamedQuery(name = "InvitacionDocente.findByDocValorPagar", query = "SELECT s FROM InvitacionDocente s WHERE s.docValorPagar = :docValorPagar")})
public class InvitacionDocente  implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InvitacionDocentePK invitacionDocentePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "doc_fecha_inivit")
    @Temporal(TemporalType.DATE)
    private Date fechaInvitacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "doc_valor_pagar")
    private BigDecimal docValorPagar;
    
    @Column(name = "doc_inv_estado")
    private String estado;
    @Column(name = "doc_contrato_generado")
    private String contratoGenerado;
    
    
    @JoinColumn(name = "prf_id_profesor", referencedColumnName = "prf_id_profesor", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Profesor profesor;
    @JoinColumn(name = "doc_id_plantilla", referencedColumnName = "doc_id_plantilla")
    @ManyToOne(fetch = FetchType.LAZY)
    private Plantilla plantilla;
    @JoinColumn(name = "id_curso_periodo", referencedColumnName = "id_curso_periodo", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CursoPeriodo cursoPeriodo;

    public InvitacionDocente() {
    }

    public InvitacionDocente(InvitacionDocentePK invitacionDocentePK) {
        this.invitacionDocentePK = invitacionDocentePK;
    }

    public InvitacionDocente(InvitacionDocentePK invitacionDocentePK, Date fechaInvitacion, BigDecimal docValorPagar) {
        this.invitacionDocentePK = invitacionDocentePK;
        this.fechaInvitacion = fechaInvitacion;
        this.docValorPagar = docValorPagar;
    }

    public InvitacionDocente(String docNumInvit, int prfIdProfesor, BigInteger idCursoPeriodo) {
        this.invitacionDocentePK = new InvitacionDocentePK(docNumInvit, prfIdProfesor, idCursoPeriodo);
    }

    public InvitacionDocentePK getInvitacionDocentePK() {
        return invitacionDocentePK;
    }

    public void setInvitacionDocentePK(InvitacionDocentePK invitacionDocentePK) {
        this.invitacionDocentePK = invitacionDocentePK;
    }

    

    public Date getFechaInvitacion() {
		return fechaInvitacion;
	}

	public void setFechaInvitacion(Date fechaInvitacion) {
		this.fechaInvitacion = fechaInvitacion;
	}

	public BigDecimal getDocValorPagar() {
        return docValorPagar;
    }

    public void setDocValorPagar(BigDecimal docValorPagar) {
        this.docValorPagar = docValorPagar;
    }

   

    public Plantilla getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(Plantilla plantilla) {
		this.plantilla = plantilla;
	}

	public CursoPeriodo getCursoPeriodo() {
		return cursoPeriodo;
	}

	public void setCursoPeriodo(CursoPeriodo cursoPeriodo) {
		this.cursoPeriodo = cursoPeriodo;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (invitacionDocentePK != null ? invitacionDocentePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvitacionDocente)) {
            return false;
        }
        InvitacionDocente other = (InvitacionDocente) object;
        if ((this.invitacionDocentePK == null && other.invitacionDocentePK != null) || (this.invitacionDocentePK != null && !this.invitacionDocentePK.equals(other.invitacionDocentePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.InvitacionDocente[ invitacionDocentePK=" + invitacionDocentePK + " ]";
    }

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getContratoGenerado() {
		return contratoGenerado;
	}

	public void setContratoGenerado(String contratoGenerado) {
		this.contratoGenerado = contratoGenerado;
	}
	
}
