package pe.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.upc.spring.model.Usuario;

public interface IUsuarioService {


	public boolean insertar(Usuario Usuario);
	public boolean modificar(Usuario Usuario);
	public void eliminar(Long id);
	public Optional<Usuario> buscarId(Long id);
	public Optional<Usuario> listarId(Long id);
	public List<Usuario> listar();
	
}
