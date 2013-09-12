/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.espe.sigec.model.entities;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author roberto
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sgct_rh_persona")
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p")})
public class Persona implements Serializable {
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "per_id_persona")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="persona_seq")
    @SequenceGenerator(name="persona_seq", sequenceName="persona_seq", allocationSize = 1)
    private Integer idPersona;
    @Size(max = 10)
    @Column(name = "per_cedula")
    private String cedula;
    @Size(max = 50)
    @Column(name = "per_primer_nombre")
    private String primerNombre;
    @Size(max = 50)
    @Column(name = "per_segundo_nombre")
    private String segundoNombre;
    @Size(max = 50)
    @Column(name = "per_primer_apellido")
    private String primerApellido;
    @Size(max = 50)
    @Column(name = "per_segundo_apellido")
    private String segundoApellido;
    @Size(max = 50)
    @Column(name = "per_nacionalidad")
    private String nacionalidad;
    @Column(name = "per_lugar_nacimiento")
    private String lugarNacimiento;
    @Column(name = "per_fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Size(max = 250)
    @Column(name = "per_direccion")
    private String direccion;
    @Size(max = 250)
    @Column(name = "per_mail")
    private String mail;
//    @Size(max = 20)
    @Column(name = "per_telefono_celular")
    private String telefonoCelular;
//    @Size(max = 20)
    @Column(name = "per_telefono_convencional")
    private String telefonoConvencional;
    @Column(name = "per_foto")
    private byte[] foto;
    @Column(name = "per_referencia")
    private String referencia;
    @Column(name = "per_profesion")
    private String profesion;
    @Column(name = "per_es_contacto")
    private String esContacto;
    @Column(name = "per_estado_civil")
    private String estadoCivil;
    @Transient
    private String nombreCompleto;
    
    @PostLoad
    private void llenarNombreCompleto(){
    	StringBuilder nombreCompleto = new StringBuilder();
    	if(this.primerNombre !=null){
    		nombreCompleto.append(this.primerNombre + " ");
    	}
    	if(this.segundoNombre !=null){
    		nombreCompleto.append(this.segundoNombre + " ");
    	}
    	if(this.primerApellido !=null){
    		nombreCompleto.append(this.primerApellido+ " ");
    	}
    	if(this.segundoApellido !=null){
    		nombreCompleto.append(this.segundoApellido);
    	}
    	setNombreCompleto(nombreCompleto.toString());
    }
    
   
   
    @JoinColumn(name = "usr_id_usuario", referencedColumnName = "usr_id_usuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "persona", fetch = FetchType.LAZY)
    private EducacionFormacion educacionFormacion;
    
    public Persona() {
    }

    
    public EducacionFormacion getEducacionFormacion() {
		return educacionFormacion;
	}


	public void setEducacionFormacion(EducacionFormacion educacionFormacion) {
		this.educacionFormacion = educacionFormacion;
	}


	public Persona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public String getTelefonoConvencional() {
        return telefonoConvencional;
    }

    public void setTelefonoConvencional(String telefonoConvencional) {
        this.telefonoConvencional = telefonoConvencional;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.espe.sigec.model.entites.Persona[ idPersona=" + idPersona + " ]";
    }

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getEsContacto() {
		return esContacto;
	}

	public void setEsContacto(String esContacto) {
		this.esContacto = esContacto;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public String getLugarNacimiento() {
		return lugarNacimiento;
	}

	public void setLugarNacimiento(String lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}


	public String getNombreCompleto() {
		return nombreCompleto;
	}


	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
}
