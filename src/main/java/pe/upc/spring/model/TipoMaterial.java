package pe.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="TipoMaterial")
public class TipoMaterial implements Serializable{

private static final long serialVersionUID=7L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idTipoMaterial",nullable=false)
	private int idTipoMaterial;
	
	@NotEmpty(message="No puedo estar vacío")
	@NotBlank(message="No puedo estar en blanco")
	@Column(name="nombreTipoMaterial", nullable=false)
	@Pattern(regexp="[ a-zA-Z0-9À-ÿ\\\\u00f1\\\\u00d1]{1,50}")
	private String nombreTipoMaterial;

	@Column(name="precioTipoMaterial",nullable=false)
	private double precioTipoMaterial;

	public TipoMaterial() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoMaterial(int idTipoMaterial,String nombreTipoMaterial,double precioTipoMaterial) {
		super();
		this.idTipoMaterial = idTipoMaterial;
		this.nombreTipoMaterial = nombreTipoMaterial;
		this.precioTipoMaterial = precioTipoMaterial;
	}

	public int getIdTipoMaterial() {
		return idTipoMaterial;
	}

	public void setIdTipoMaterial(int idTipoMaterial) {
		this.idTipoMaterial = idTipoMaterial;
	}

	public String getNombreTipoMaterial() {
		return nombreTipoMaterial;
	}

	public void setNombreTipoMaterial(String nombreTipoMaterial) {
		this.nombreTipoMaterial = nombreTipoMaterial;
	}

	public double getPrecioTipoMaterial() {
		return precioTipoMaterial;
	}

	public void setPrecioTipoMaterial(double precioTipoMaterial) {
		this.precioTipoMaterial = precioTipoMaterial;
	}

	
	
}
