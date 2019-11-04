package pe.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.upc.spring.model.Seccion;

public interface ISeccionService {

	public boolean insertar(Seccion seccion);
	public boolean modificar(Seccion seccion);
	public void eliminar(int idSeccion);
	public Optional<Seccion> listarId(int idSeccion);
	public Optional<Seccion> buscarId(int idSeccion);
	public List<Seccion>listar();
	
	public List<Seccion>buscarCurso(String codigoCurso);
	public List<Seccion>buscarProfesor(String codigoProfesor);
}
