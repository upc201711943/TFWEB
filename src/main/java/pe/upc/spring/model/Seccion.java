package pe.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Seccion")
public class Seccion implements Serializable{
	
	private static final long serialVersionUID=5L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idSeccion",nullable=false)
	private int idSeccion;
	
	@ManyToOne
	@JoinColumn(name="idCurso",nullable=false)
	private Curso curso;
	
	@ManyToOne
	@JoinColumn(name="idProfesor",nullable=false)
	private Profesor profesor;


	public Seccion() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Seccion(int idSeccion, Curso curso, Profesor profesor) {
		super();
		this.idSeccion = idSeccion;
		this.curso = curso;
		this.profesor = profesor;
	}


	public int getIdSeccion() {
		return idSeccion;
	}

	public void setIdSeccion(int idSeccion) {
		this.idSeccion = idSeccion;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
}