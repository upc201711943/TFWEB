package pe.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.upc.spring.model.Role;

@Repository
public interface IRoleDAO extends JpaRepository<Role, Long>{

	@Query("from Role r where r.authority like %:authority%")
	List<Role> obtenerRole(@Param("authority") String authority);
}
