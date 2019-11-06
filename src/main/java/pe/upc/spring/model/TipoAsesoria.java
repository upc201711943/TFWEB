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

@Entity
@Table(name="TipoAsesoria")
public class TipoAsesoria implements Serializable{

	private static final long serialVersionUID=6L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idTipoAsesoria",nullable=false)
	private int idTipoAsesoria;
	
	@NotEmpty(message="No puedo estar vacío")
	@NotBlank(message="No puedo estar en blanco")
	@Column(name="nombreTipoAsesoria", nullable=false)
	private String nombreTipoAsesoria;


	@Column(name="precioTipoAsesoria",nullable=false)
	private double precioTipoAsesoria;

	public TipoAsesoria() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoAsesoria(int idTipoAsesoria,String nombreTipoAsesoria,double precioTipoAsesoria) {
		super();
		this.idTipoAsesoria = idTipoAsesoria;
		this.nombreTipoAsesoria = nombreTipoAsesoria;
		this.precioTipoAsesoria = precioTipoAsesoria;
	}

	public int getIdTipoAsesoria() {
		return idTipoAsesoria;
	}

	public void setIdTipoAsesoria(int idTipoAsesoria) {
		this.idTipoAsesoria = idTipoAsesoria;
	}

	public String getNombreTipoAsesoria() {
		return nombreTipoAsesoria;
	}

	public void setNombreTipoAsesoria(String nombreTipoAsesoria) {
		this.nombreTipoAsesoria = nombreTipoAsesoria;
	}

	public double getPrecioTipoAsesoria() {
		return precioTipoAsesoria;
	}

	public void setPrecioTipoAsesoria(double precioTipoAsesoria) {
		this.precioTipoAsesoria = precioTipoAsesoria;
	}

}
