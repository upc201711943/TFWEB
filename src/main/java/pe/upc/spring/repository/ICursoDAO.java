package pe.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.upc.spring.model.Curso;

@Repository
public interface ICursoDAO extends JpaRepository<Curso, Integer>{

	@Query("from Curso c where c.nombreCurso like %:nombreCurso%")
	List<Curso>buscarCurso(@Param("nombreCurso")String nombreCurso);
	
}
