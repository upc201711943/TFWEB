package pe.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.upc.spring.model.Matricula;

public interface IMatriculaService {
	public boolean insertar(Matricula matricula);
	public boolean modificar(Matricula matricula);
	public void eliminar(int idMatricula);
	public Optional<Matricula> listarId(int idMatricula);
	public Optional<Matricula> buscarId(int idMatricula);
	public List<Matricula>listar();
	/*public List<Matricula>buscarCurso(String nombreCurso);
	public List<Matricula>buscarProfesor(String nombreProfesor);*/
}
