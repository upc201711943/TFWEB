package pe.upc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.upc.spring.model.Usuario;


@Repository
public interface IUsuarioDao extends JpaRepository<Usuario, Long> {

	public Usuario findByUsername(String username);

}
