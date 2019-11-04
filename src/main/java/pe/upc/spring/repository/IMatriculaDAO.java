package pe.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.upc.spring.model.Matricula;

@Repository
public interface IMatriculaDAO extends JpaRepository<Matricula, Integer>{
	/*@Query("from Matricula m where m.seccion.curso.codigoCurso = %:codigoCurso%")
	public List<Matricula> buscarCurso(@Param("codigoCurso")String codigoCurso);
	
	@Query("from Matricula m where m.seccion.profesor.codigoProfesor = %:codigoProfesor%")
	public List<Matricula> buscarProfesor(@Param("codigoProfesor")String codigoProfesor);
	*/
}
