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
@Table(name="Curso")
public class Curso implements Serializable{

	private static final long serialVersionUID=2L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idCurso",nullable=false)
	private int idCurso;
	
	@NotEmpty(message="No puedo estar vacío")
	@NotBlank(message="No puedo estar en blanco")
	@Column(name="codigoCurso", nullable=false)
	private String codigoCurso;
	
	@NotEmpty(message="No puedo estar vacío")
	@NotBlank(message="No puedo estar en blanco")
	@Column(name="nombreCurso", nullable=false)
	private String nombreCurso;

	
	
	public Curso() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Curso(int idCurso,String codigoCurso,String nombreCurso) {
		super();
		this.idCurso = idCurso;
		this.codigoCurso = codigoCurso;
		this.nombreCurso = nombreCurso;
	}



	public int getIdCurso() {
		return idCurso;
	}



	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}



	public String getCodigoCurso() {
		return codigoCurso;
	}



	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}



	public String getNombreCurso() {
		return nombreCurso;
	}



	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}
	
	
}
