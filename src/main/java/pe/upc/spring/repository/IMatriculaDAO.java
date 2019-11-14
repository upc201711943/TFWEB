package pe.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.upc.spring.model.Matricula;

@Repository
public interface IMatriculaDAO extends JpaRepository<Matricula, Integer>{

	
	@Query("from Matricula m where m.alumno.codigoAlumno like %:codigoAlumno%")
	public List<Matricula> buscarAlumno(@Param("codigoAlumno")String codigoAlumno);
	
	@Query("from Matricula m where m.alumno.idAlumno like %:idAlumno%")
	public List<Matricula> listarAlumno(@Param("idAlumno")int idAlumno);
	/*
	@Query("from Matricula m where m.seccion.profesor.codigoProfesor = %:codigoProfesor%")
	public List<Matricula> buscarProfesor(@Param("codigoProfesor")String codigoProfesor);
	*/
}
