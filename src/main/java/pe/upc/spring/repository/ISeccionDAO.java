package pe.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.upc.spring.model.Seccion;

@Repository
public interface ISeccionDAO extends JpaRepository<Seccion, Integer>{

	@Query("from Seccion s where s.curso.codigoCurso like %:codigoCurso%")
	List<Seccion>buscarCurso(@Param("codigoCurso")String codigoCurso);
	
	@Query("from Seccion s where s.profesor.codigoProfesor like %:codigoProfesor%")
	List<Seccion>buscarProfesor(@Param("codigoProfesor")String codigoProfesor);
	
}
