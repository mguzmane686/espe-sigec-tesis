package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "sgct_doc_contrato_prof")
public class ContratoProfesor implements Serializable{
    @EmbeddedId
    protected ContratoProfesorPK sgctDocContratoProfPK;
//    @Column(name = "ctr_fecha_certificacion")
//    @Temporal(TemporalType.DATE)
//    private Date ctrFechaCertificacion;
//    @Size(max = 20)
//    @Column(name = "ctr_num_cert_presu")
//    private String ctrNumCertPresu;
    @Size(max = 120)
    @Column(name = "ctr_nom_part_pre")
    private String nombrePartidaPresupuestaria;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ctr_valor_part_pre")
    private BigDecimal valorPartidaPresupuestaria;
//    @Size(max = 256)
//    @Column(name = "ctr_lugar_dictarse")
//    private String ctrLugarDictarse;
    @JoinColumns({
        @JoinColumn(name = "doc_num_invit", referencedColumnName = "doc_num_invit", insertable = false, updatable = false),
        @JoinColumn(name = "prf_id_profesor", referencedColumnName = "prf_id_profesor", insertable = false, updatable = false),
        @JoinColumn(name = "id_curso_periodo", referencedColumnName = "id_curso_periodo", insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InvitacionDocente invitacionDocente;
    
    @Column(name = "prf_id_profesor")
    private int  idProfesor;
    
    @Column(name = "id_curso_periodo")
    private BigDecimal idCursoPeriodo;
    
    @Column(name = "ctr_num_certificacion")
    private String numeroCertificacion;
    
    @Column(name = "ctr_actividades")
    private String actividades;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "ctr_fecha_carta_de_pago")
	private Date fechaCartaPago;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "ctr_fecha_certificacion")
    private Date fechaCertificacion;
    
    @Column(name = "ctr_num_memo")
	private String numeroMemo;
    
    @Column(name = "ctr_val_hora_clase")
	private BigDecimal valorHoraClase;
    
    @Column(name = "ctr_tipo_relacion_laboral")
	private String tipoRelacionLaboral;
    
    @Column(name = "ctr_banco")
	private String banco;
    
    @Column(name = "ctr_num_cuenta")
	private String numeroCuenta;
    
    @Column(name = "ctr_plan")
	private String plan;
    
	

    public ContratoProfesor() {
    }

    public ContratoProfesor(ContratoProfesorPK sgctDocContratoProfPK) {
        this.sgctDocContratoProfPK = sgctDocContratoProfPK;
    }

    public ContratoProfesor(String numeroContrato, String docNumInvit) {
        this.sgctDocContratoProfPK = new ContratoProfesorPK(numeroContrato, docNumInvit);
    }

    public ContratoProfesorPK getContratoProfesorPK() {
        return sgctDocContratoProfPK;
    }

    public void setContratoProfesorPK(ContratoProfesorPK sgctDocContratoProfPK) {
        this.sgctDocContratoProfPK = sgctDocContratoProfPK;
    }

    public String getNombrePartidaPresupuestaria() {
		return nombrePartidaPresupuestaria;
	}

	public void setNombrePartidaPresupuestaria(String nombrePartidaPresupuestaria) {
		this.nombrePartidaPresupuestaria = nombrePartidaPresupuestaria;
	}

    public BigDecimal getValorPartidaPresupuestaria() {
		return valorPartidaPresupuestaria;
	}

	public void setValorPartidaPresupuestaria(BigDecimal valorPartidaPresupuestaria) {
		this.valorPartidaPresupuestaria = valorPartidaPresupuestaria;
	}

	public InvitacionDocente getInvitacionDocente() {
		return invitacionDocente;
	}

	public void setInvitacionDocente(InvitacionDocente invitacionDocente) {
		this.invitacionDocente = invitacionDocente;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (sgctDocContratoProfPK != null ? sgctDocContratoProfPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContratoProfesor)) {
            return false;
        }
        ContratoProfesor other = (ContratoProfesor) object;
        if ((this.sgctDocContratoProfPK == null && other.sgctDocContratoProfPK != null) || (this.sgctDocContratoProfPK != null && !this.sgctDocContratoProfPK.equals(other.sgctDocContratoProfPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.ContratoProfesor[ sgctDocContratoProfPK=" + sgctDocContratoProfPK + " ]";
    }

	public int  getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(int  idProfesor) {
		this.idProfesor = idProfesor;
	}

	public BigDecimal getIdCursoPeriodo() {
		return idCursoPeriodo;
	}

	public void setIdCursoPeriodo(BigDecimal idCursoPeriodo) {
		this.idCursoPeriodo = idCursoPeriodo;
	}

	public ContratoProfesorPK getSgctDocContratoProfPK() {
		return sgctDocContratoProfPK;
	}

	public void setSgctDocContratoProfPK(ContratoProfesorPK sgctDocContratoProfPK) {
		this.sgctDocContratoProfPK = sgctDocContratoProfPK;
	}

	public String getActividades() {
		return actividades;
	}

	public void setActividades(String actividades) {
		this.actividades = actividades;
	}

	public Date getFechaCartaPago() {
		return fechaCartaPago;
	}

	public void setFechaCartaPago(Date fechaCartaPago) {
		this.fechaCartaPago = fechaCartaPago;
	}

	public String getNumeroMemo() {
		return numeroMemo;
	}

	public void setNumeroMemo(String numeroMemo) {
		this.numeroMemo = numeroMemo;
	}

	public BigDecimal getValorHoraClase() {
		return valorHoraClase;
	}

	public void setValorHoraClase(BigDecimal valorHoraClase) {
		this.valorHoraClase = valorHoraClase;
	}

	public String getTipoRelacionLaboral() {
		return tipoRelacionLaboral;
	}

	public void setTipoRelacionLaboral(String tipoRelacionLaboral) {
		this.tipoRelacionLaboral = tipoRelacionLaboral;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getNumeroCertificacion() {
		return numeroCertificacion;
	}

	public void setNumeroCertificacion(String numeroCertificacion) {
		this.numeroCertificacion = numeroCertificacion;
	}

	public Date getFechaCertificacion() {
		return fechaCertificacion;
	}

	public void setFechaCertificacion(Date fechaCertificacion) {
		this.fechaCertificacion = fechaCertificacion;
	}

	
}