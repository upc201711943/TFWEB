package pe.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.upc.spring.model.TipoAsesoria;

public interface ITipoAsesoriaService {
	public boolean insertar(TipoAsesoria TipoAsesoria);
	public boolean modificar(TipoAsesoria TipoAsesoria);
	public void eliminar(int idTipoAsesoria);
	public Optional<TipoAsesoria> listarId(int idTipoAsesoria);
	public Optional<TipoAsesoria> buscarId(int idTipoAsesoria);
	public List<TipoAsesoria>listar();
	public List<TipoAsesoria>buscarTipoAsesoria(String nombreTipoAsesoria);
}
