package pe.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.upc.spring.model.Rol;

public interface IRolService {
	public boolean insertar(Rol Rol);
	public boolean modificar(Rol Rol);
	public void eliminar(int id);
	public Optional<Rol> buscarId(int id);
	public Optional<Rol> listarId(int id);
	public List<Rol> listar();

}
