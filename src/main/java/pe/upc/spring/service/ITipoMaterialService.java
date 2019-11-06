package pe.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.upc.spring.model.TipoMaterial;

public interface ITipoMaterialService {
	public boolean insertar(TipoMaterial TipoMaterial);
	public boolean modificar(TipoMaterial TipoMaterial);
	public void eliminar(int idTipoMaterial);
	public Optional<TipoMaterial> listarId(int idTipoMaterial);
	public Optional<TipoMaterial> buscarId(int idTipoMaterial);
	public List<TipoMaterial>listar();
	public List<TipoMaterial>buscarTipoMaterial(String nombreTipoMaterial);
}
