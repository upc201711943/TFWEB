package pe.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.upc.spring.model.Material;

public interface IMaterialService {
	public boolean insertar(Material Material);
	public boolean modificar(Material Material);
	public void eliminar(int idMaterial);
	public Optional<Material> listarId(int idMaterial);
	public Optional<Material> buscarId(int idMaterial);
	public List<Material>listar();
	public List<Material>buscarMaterial(String nombreMaterial);
}
