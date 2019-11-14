package pe.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.upc.spring.model.Role;

public interface IRoleService {

	public boolean insertar(Role Role);
	public boolean modificar(Role Role);
	public void eliminar(Long id);
	public Optional<Role> buscarId(Long id);
	public Optional<Role> listarId(Long id);
	public List<Role> listar();
	public List<Role> obtenerRole(String authority);
}
