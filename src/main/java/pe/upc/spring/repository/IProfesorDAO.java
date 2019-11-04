package pe.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.upc.spring.model.Profesor;

@Repository
public interface IProfesorDAO extends JpaRepository<Profesor, Integer>{

	@Query("from Profesor p where p.nombreProfesor like %:nombreProfesor%")
	List<Profesor> buscarProfesor(@Param("nombreProfesor") String nombreProfesor);
}
