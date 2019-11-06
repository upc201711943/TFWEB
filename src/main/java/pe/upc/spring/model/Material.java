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

@Entity
@Table(name="Material")
public class Material implements Serializable{

	private static final long serialVersionUID=8L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idMaterial",nullable=false)
	private int idMaterial;

	@NotEmpty(message="No puedo estar vacío")
	@NotBlank(message="No puedo estar en blanco")
	@Column(name="nombreMaterial", nullable=false)
	private String nombreMaterial;

	@NotEmpty(message="No puedo estar vacío")
	@NotBlank(message="No puedo estar en blanco")
	@Column(name="contenidoMaterial", nullable=false)
	private String contenidoMaterial;

	@NotEmpty(message="No puedo estar vacío")
	@NotBlank(message="No puedo estar en blanco")
	@Column(name="disponibilidadMaterial", nullable=false)
	private String disponibilidadMaterial;
	
	@ManyToOne
	@JoinColumn(name="idTipoMaterial",nullable=false)
	private TipoMaterial tipoMaterial;
	
	@ManyToOne
	@JoinColumn(name="idSeccion",nullable=false)
	private Seccion seccion;

	public Material() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Material(int idMaterial,String nombreMaterial,String contenidoMaterial,String disponibilidadMaterial,
			TipoMaterial tipoMaterial, Seccion seccion) {
		super();
		this.idMaterial = idMaterial;
		this.nombreMaterial = nombreMaterial;
		this.contenidoMaterial = contenidoMaterial;
		this.disponibilidadMaterial = disponibilidadMaterial;
		this.tipoMaterial = tipoMaterial;
		this.seccion = seccion;
	}

	public int getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(int idMaterial) {
		this.idMaterial = idMaterial;
	}

	public String getNombreMaterial() {
		return nombreMaterial;
	}

	public void setNombreMaterial(String nombreMaterial) {
		this.nombreMaterial = nombreMaterial;
	}

	public String getContenidoMaterial() {
		return contenidoMaterial;
	}

	public void setContenidoMaterial(String contenidoMaterial) {
		this.contenidoMaterial = contenidoMaterial;
	}




	public String getDisponibilidadMaterial() {
		return disponibilidadMaterial;
	}

	public void setDisponibilidadMaterial(String disponibilidadMaterial) {
		this.disponibilidadMaterial = disponibilidadMaterial;
	}

	public TipoMaterial getTipoMaterial() {
		return tipoMaterial;
	}

	public void setTipoMaterial(TipoMaterial tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}

	public Seccion getSeccion() {
		return seccion;
	}

	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}
	
	
}
