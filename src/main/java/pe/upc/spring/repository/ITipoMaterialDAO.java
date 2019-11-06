package pe.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.upc.spring.model.TipoMaterial;
 
@Repository
public interface ITipoMaterialDAO extends JpaRepository<TipoMaterial, Integer>{
	@Query("from TipoMaterial c where c.nombreTipoMaterial like %:nombreTipoMaterial%")
	List<TipoMaterial>buscarTipoMaterial(@Param("nombreTipoMaterial")String nombreTipoMaterial);

}
