package pe.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="Asesoria")
public class Asesoria implements Serializable{

	private static final long serialVersionUID=9L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idAsesoria",nullable=false)
	private int idAsesoria;

	@NotEmpty(message="No puedo estar vacío")
	@NotBlank(message="No puedo estar en blanco")
	@Column(name="nombreAsesoria", nullable=false)
	@Pattern(regexp="[ a-zA-Z0-9À-ÿÀ-ÿ\\u00f1\\u00d1]{1,50}")
	private String nombreAsesoria;
	
	@NotEmpty(message="No puedo estar vacío")
	@NotBlank(message="No puedo estar en blanco")
	@Column(name="urlAsesoria", nullable=false)
	private String urlAsesoria;
	
	@NotEmpty(message="No puedo estar vacío")
	@NotBlank(message="No puedo estar en blanco")
	@Column(name="disponibilidadAsesoria", nullable=false)
	private String disponibilidadAsesoria;
	
	@ManyToOne
	@JoinColumn(name="idTipoAsesoria",nullable=false)
	private TipoAsesoria tipoAsesoria;
	
	@ManyToOne
	@JoinColumn(name="idSeccion",nullable=false)
	private Seccion seccion;

	public Asesoria() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	

	public Asesoria(int idAsesoria,String nombreAsesoria,String urlAsesoria,String disponibilidadAsesoria,
			TipoAsesoria tipoAsesoria, Seccion seccion) {
		super();
		this.idAsesoria = idAsesoria;
		this.nombreAsesoria = nombreAsesoria;
		this.urlAsesoria = urlAsesoria;
		this.disponibilidadAsesoria = disponibilidadAsesoria;
		this.tipoAsesoria = tipoAsesoria;
		this.seccion = seccion;
	}





	public String getDisponibilidadAsesoria() {
		return disponibilidadAsesoria;
	}





	public void setDisponibilidadAsesoria(String disponibilidadAsesoria) {
		this.disponibilidadAsesoria = disponibilidadAsesoria;
	}





	public String getUrlAsesoria() {
		return urlAsesoria;
	}



	public void setUrlAsesoria(String urlAsesoria) {
		this.urlAsesoria = urlAsesoria;
	}



	public int getIdAsesoria() {
		return idAsesoria;
	}

	public void setIdAsesoria(int idAsesoria) {
		this.idAsesoria = idAsesoria;
	}

	public String getNombreAsesoria() {
		return nombreAsesoria;
	}

	public void setNombreAsesoria(String nombreAsesoria) {
		this.nombreAsesoria = nombreAsesoria;
	}

	public TipoAsesoria getTipoAsesoria() {
		return tipoAsesoria;
	}

	public void setTipoAsesoria(TipoAsesoria tipoAsesoria) {
		this.tipoAsesoria = tipoAsesoria;
	}

	public Seccion getSeccion() {
		return seccion;
	}

	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}
	
	
	
	
}
