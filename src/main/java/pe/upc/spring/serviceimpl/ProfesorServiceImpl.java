package pe.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.spring.model.Profesor;
import pe.upc.spring.repository.IProfesorDAO;
import pe.upc.spring.service.IProfesorService;

@Service
public class ProfesorServiceImpl implements IProfesorService{

	@Autowired
	private IProfesorDAO dProfesor;
	
	@Override
	@Transactional
	public boolean insertar(Profesor profesor){
		Profesor objProfesor=dProfesor.save(profesor);
		if(objProfesor==null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public boolean modificar(Profesor profesor){
		boolean flag=false;
		try {
			dProfesor.save(profesor);
			flag=true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return flag;
	}
	
	@Override
	@Transactional
	public void eliminar(int idProfesor){
		dProfesor.deleteById(idProfesor);
	}
	@Override
	public Optional<Profesor> buscarId(int idProfesor){
		return dProfesor.findById(idProfesor);
	}
	@Override
	public Optional<Profesor> listarId(int idProfesor){
		return dProfesor.findById(idProfesor);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public List<Profesor> listar(){
		return dProfesor.findAll();
	}
	
	@Override
	@Transactional
	public List<Profesor> buscarProfesor(String nombreProfesor){
		return dProfesor.buscarProfesor(nombreProfesor);
	}
}
