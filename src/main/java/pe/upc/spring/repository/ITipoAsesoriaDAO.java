package pe.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.upc.spring.model.TipoAsesoria;

@Repository
public interface ITipoAsesoriaDAO extends JpaRepository<TipoAsesoria, Integer>{
	@Query("from TipoAsesoria c where c.nombreTipoAsesoria like %:nombreTipoAsesoria%")
	List<TipoAsesoria>buscarTipoAsesoria(@Param("nombreTipoAsesoria")String nombreTipoAsesoria);

}
