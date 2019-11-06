package pe.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.upc.spring.model.Asesoria;

public interface IAsesoriaService {
	public boolean insertar(Asesoria Asesoria);
	public boolean modificar(Asesoria Asesoria);
	public void eliminar(int idAsesoria);
	public Optional<Asesoria> listarId(int idAsesoria);
	public Optional<Asesoria> buscarId(int idAsesoria);
	public List<Asesoria>listar();
	public List<Asesoria>buscarAsesoria(String nombreAsesoria);
}
