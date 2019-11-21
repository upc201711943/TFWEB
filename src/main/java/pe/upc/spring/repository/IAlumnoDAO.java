package pe.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.upc.spring.model.Alumno;

@Repository
public interface IAlumnoDAO extends JpaRepository<Alumno, Integer>{

	@Query("from Alumno c where c.codigoAlumno like %:codigoAlumno%")
	List<Alumno>buscarAlumno(@Param("codigoAlumno")String codigoAlumno);

}
