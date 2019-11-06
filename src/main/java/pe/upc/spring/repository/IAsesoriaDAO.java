package pe.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.upc.spring.model.Asesoria;

@Repository
public interface IAsesoriaDAO extends JpaRepository<Asesoria, Integer>{

	@Query("from Asesoria c where c.nombreAsesoria like %:nombreAsesoria%")
	List<Asesoria>buscarAsesoria(@Param("nombreAsesoria")String nombreAsesoria);
}
