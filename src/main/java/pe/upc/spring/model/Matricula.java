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
@Table(name = "Matricula")
public class Matricula implements Serializable {

	private static final long serialVersionUID=3L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idMatricula", nullable=false)
	private int idMatricula;
	
	@ManyToOne
	@JoinColumn(name="idAlumno",nullable=false)
	private Alumno alumno;
	
	@ManyToOne
	@JoinColumn(name="idSeccion",nullable=false)
	private Seccion seccion;

	@Column(name="calificacion",nullable=true)
	private int calificacion;

	
	public Matricula() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Matricula(int idMatricula, Alumno alumno, Seccion seccion, int calificacion) {
		super();
		this.idMatricula = idMatricula;
		this.alumno = alumno;
		this.seccion = seccion;
		this.calificacion = calificacion;
	}

	public int getIdMatricula() {
		return idMatricula;
	}

	public void setIdMatricula(int idMatricula) {
		this.idMatricula = idMatricula;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Seccion getSeccion() {
		return seccion;
	}

	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
}
