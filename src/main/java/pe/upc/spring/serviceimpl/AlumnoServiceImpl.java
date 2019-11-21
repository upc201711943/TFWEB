package pe.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.spring.model.Alumno;
import pe.upc.spring.repository.IAlumnoDAO;
import pe.upc.spring.service.IAlumnoService;

@Service
public class AlumnoServiceImpl implements IAlumnoService{

	@Autowired
	private IAlumnoDAO dAlumno;
	
	@Override
	@Transactional
	public boolean insertar(Alumno alumno)
	{
		Alumno objAlumno=dAlumno.save(alumno);
		if(objAlumno==null)
			return false;
		else
			return true;
			}
	
	@Override
	@Transactional
	public boolean modificar(Alumno alumno)
	{
		boolean flag=false;
		try {
			dAlumno.save(alumno);
			flag=true;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return flag;			
	}
	
	@Override
	@Transactional
	public void eliminar(int idAlumno) {
		dAlumno.deleteById(idAlumno);
	}
	
	@Override
	public Optional<Alumno>buscarId(int idAlumno){
		return dAlumno.findById(idAlumno);
	}
	
	@Override
	public Optional<Alumno>listarId(int idAlumno){
		return dAlumno.findById(idAlumno);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Alumno>listar(){
		return dAlumno.findAll();
	}
	
	@Override
	@Transactional
	public List<Alumno>buscarAlumno(String codigoAlumno){
		return dAlumno.buscarAlumno(codigoAlumno);
	}
	
}
