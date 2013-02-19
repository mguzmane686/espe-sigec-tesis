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
import javax.persistence.Table;

/**
 * @author Roberto
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sgct_std_encuesta")
public class Encuesta implements Serializable{
//	private String empresa;
//	private String facilitador;
//	private String nombreCurso;
//	private String lugar;
//	private Date fecha;
//	private String edad;
//	private String actividad;
//	
	
	
	
	@EmbeddedId
    protected EncuestaPK encuestaPK;
    @Column(name = "ctn_cumplimiento")
    private Integer ctnCumplimiento;
    @Column(name = "ctn_temas")
    private Integer ctnTemas;
    @Column(name = "ctn_espectativas")
    private Integer ctnEspectativas;
    @Column(name = "ctn_conocimiento")
    private Integer ctnConocimiento;
    @Column(name = "fct_contenido")
    private Integer fctContenido;
    @Column(name = "fct_conocimiento")
    private Integer fctConocimiento;
    @Column(name = "fct_concepto")
    private Integer fctConcepto;
    @Column(name = "fct_participativo")
    private Integer fctParticipativo;
    @Column(name = "fct_aprendizaje")
    private Integer fctAprendizaje;
    @Column(name = "fct_cordialidad")
    private Integer fctCordialidad;
    @Column(name = "fct_respuesta")
    private Integer fctRespuesta;
    @Column(name = "fct_recursos")
    private Integer fctRecursos;
    @Column(name = "fct_puntualidad")
    private Integer fctPuntualidad;
    @Column(name = "fct_inquietud")
    private Integer fctInquietud;
    @Column(name = "fct_actitud")
    private Integer fctActitud;
    @Column(name = "mta_material")
    private Integer mtaMaterial;
    @Column(name = "mta_claridad")
    private Integer mtaClaridad;
    @Column(name = "odp_satisfaccion")
    private Integer odpSatisfaccion;
    @Column(name = "odp_disponibilidad")
    private Integer odpDisponibilidad;
    @Column(name = "odp_logistica")
    private Integer odpLogistica;
    @Column(name = "odp_puntualidad")
    private Integer odpPuntualidad;
    @Column(name = "odp_servicio")
    private Integer odpServicio;
    @Column(name = "recomendar")
    private String recomendar;
    
    @Column(name = "ect_institucion")
    private String institucion;
    
    @Column(name = "rec_motivo")
    private String motivoRecomendacion;
    
    @Column(name = "ect_fecha")
    private Date fechaEncuesta;
    
    @JoinColumns({
        @JoinColumn(name = "prg_id", referencedColumnName = "prg_id", insertable = false, updatable = false),
        @JoinColumn(name = "id_curso_periodo", referencedColumnName = "id_curso_periodo", insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ProgramaCurso programaCurso;
    
    @JoinColumns({
        @JoinColumn(name = "est_id_estudiante", referencedColumnName = "est_id_estudiante", insertable = false, updatable = false),
        @JoinColumn(name = "id_curso_periodo", referencedColumnName = "id_curso_periodo", insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CursoEstudiante cursoEstudiante;
    
    public Encuesta() {
    }

    public Encuesta(EncuestaPK encuestaPK) {
        this.encuestaPK = encuestaPK;
    }

    public Encuesta(int prgId, BigInteger idCursoPeriodo, int estIdEstudiante) {
        this.encuestaPK = new EncuestaPK(prgId, idCursoPeriodo, estIdEstudiante);
    }

    public EncuestaPK getEncuestaPK() {
		return encuestaPK;
	}

	public void setEncuestaPK(EncuestaPK encuestaPK) {
		this.encuestaPK = encuestaPK;
	}

	public Integer getCtnCumplimiento() {
        return ctnCumplimiento;
    }

    public void setCtnCumplimiento(Integer ctnCumplimiento) {
        this.ctnCumplimiento = ctnCumplimiento;
    }

    public Integer getCtnTemas() {
        return ctnTemas;
    }

    public void setCtnTemas(Integer ctnTemas) {
        this.ctnTemas = ctnTemas;
    }

    public Integer getCtnEspectativas() {
        return ctnEspectativas;
    }

    public void setCtnEspectativas(Integer ctnEspectativas) {
        this.ctnEspectativas = ctnEspectativas;
    }

    public Integer getCtnConocimiento() {
        return ctnConocimiento;
    }

    public void setCtnConocimiento(Integer ctnConocimiento) {
        this.ctnConocimiento = ctnConocimiento;
    }

    public Integer getFctContenido() {
        return fctContenido;
    }

    public void setFctContenido(Integer fctContenido) {
        this.fctContenido = fctContenido;
    }

    public Integer getFctConocimiento() {
        return fctConocimiento;
    }

    public void setFctConocimiento(Integer fctConocimiento) {
        this.fctConocimiento = fctConocimiento;
    }

    public Integer getFctConcepto() {
        return fctConcepto;
    }

    public void setFctConcepto(Integer fctConcepto) {
        this.fctConcepto = fctConcepto;
    }

    public Integer getFctParticipativo() {
        return fctParticipativo;
    }

    public void setFctParticipativo(Integer fctParticipativo) {
        this.fctParticipativo = fctParticipativo;
    }

    public Integer getFctAprendizaje() {
        return fctAprendizaje;
    }

    public void setFctAprendizaje(Integer fctAprendizaje) {
        this.fctAprendizaje = fctAprendizaje;
    }

    public Integer getFctCordialidad() {
        return fctCordialidad;
    }

    public void setFctCordialidad(Integer fctCordialidad) {
        this.fctCordialidad = fctCordialidad;
    }

    public Integer getFctRespuesta() {
        return fctRespuesta;
    }

    public void setFctRespuesta(Integer fctRespuesta) {
        this.fctRespuesta = fctRespuesta;
    }

    public Integer getFctRecursos() {
        return fctRecursos;
    }

    public void setFctRecursos(Integer fctRecursos) {
        this.fctRecursos = fctRecursos;
    }

    public Integer getFctPuntualidad() {
        return fctPuntualidad;
    }

    public void setFctPuntualidad(Integer fctPuntualidad) {
        this.fctPuntualidad = fctPuntualidad;
    }

    public Integer getFctInquietud() {
        return fctInquietud;
    }

    public void setFctInquietud(Integer fctInquietud) {
        this.fctInquietud = fctInquietud;
    }

    public Integer getFctActitud() {
        return fctActitud;
    }

    public void setFctActitud(Integer fctActitud) {
        this.fctActitud = fctActitud;
    }

    public Integer getMtaMaterial() {
        return mtaMaterial;
    }

    public void setMtaMaterial(Integer mtaMaterial) {
        this.mtaMaterial = mtaMaterial;
    }

    public Integer getMtaClaridad() {
        return mtaClaridad;
    }

    public void setMtaClaridad(Integer mtaClaridad) {
        this.mtaClaridad = mtaClaridad;
    }

    public Integer getOdpSatisfaccion() {
        return odpSatisfaccion;
    }

    public void setOdpSatisfaccion(Integer odpSatisfaccion) {
        this.odpSatisfaccion = odpSatisfaccion;
    }

    public Integer getOdpDisponibilidad() {
        return odpDisponibilidad;
    }

    public void setOdpDisponibilidad(Integer odpDisponibilidad) {
        this.odpDisponibilidad = odpDisponibilidad;
    }

    public Integer getOdpLogistica() {
        return odpLogistica;
    }

    public void setOdpLogistica(Integer odpLogistica) {
        this.odpLogistica = odpLogistica;
    }

    public Integer getOdpPuntualidad() {
        return odpPuntualidad;
    }

    public void setOdpPuntualidad(Integer odpPuntualidad) {
        this.odpPuntualidad = odpPuntualidad;
    }

    public Integer getOdpServicio() {
        return odpServicio;
    }

    public void setOdpServicio(Integer odpServicio) {
        this.odpServicio = odpServicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (encuestaPK != null ? encuestaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Encuesta)) {
            return false;
        }
        Encuesta other = (Encuesta) object;
        if ((this.encuestaPK == null && other.encuestaPK != null) || (this.encuestaPK != null && !this.encuestaPK.equals(other.encuestaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entities.SgctStdEncuesta[ encuestaPK=" + encuestaPK + " ]";
    }

	public Date getFechaEncuesta() {
		return fechaEncuesta;
	}

	public void setFechaEncuesta(Date fechaEncuesta) {
		this.fechaEncuesta = fechaEncuesta;
	}

	public String getRecomendar() {
		return recomendar;
	}

	public void setRecomendar(String recomendar) {
		this.recomendar = recomendar;
	}

	public String getMotivoRecomendacion() {
		return motivoRecomendacion;
	}

	public void setMotivoRecomendacion(String motivoRecomendacion) {
		this.motivoRecomendacion = motivoRecomendacion;
	}

	public ProgramaCurso getProgramaCurso() {
		return programaCurso;
	}

	public void setProgramaCurso(ProgramaCurso programaCurso) {
		this.programaCurso = programaCurso;
	}

	public CursoEstudiante getCursoEstudiante() {
		return cursoEstudiante;
	}

	public void setCursoEstudiante(CursoEstudiante cursoEstudiante) {
		this.cursoEstudiante = cursoEstudiante;
	}

	public String getInstitucion() {
		return institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}
	
}
