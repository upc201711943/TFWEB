package pe.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.upc.spring.model.Material;

@Repository
public interface IMaterialDAO extends JpaRepository<Material, Integer>{
	@Query("from Material c where c.nombreMaterial like %:nombreMaterial%")
	List<Material>buscarMaterial(@Param("nombreMaterial")String nombreMaterial);

}
