package pe.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.spring.model.Curso;
import pe.upc.spring.repository.ICursoDAO;
import pe.upc.spring.service.ICursoService;

@Service
public class CursoServiceImpl implements ICursoService{
	
	@Autowired
	private ICursoDAO dCurso;
	
	@Override
	@Transactional
	public boolean insertar(Curso curso){
		Curso objCurso=dCurso.save(curso);
		if(objCurso==null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public boolean modificar(Curso curso){
		boolean flag=false;
		try {
			dCurso.save(curso);
			flag=true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return flag;
	}
	
	@Override
	@Transactional
	public void eliminar(int idCurso){
		dCurso.deleteById(idCurso);
	}
	@Override
	public Optional<Curso> buscarId(int idCurso){
		return dCurso.findById(idCurso);
	}
	@Override
	public Optional<Curso> listarId(int idCurso){
		return dCurso.findById(idCurso);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public List<Curso> listar(){
		return dCurso.findAll();
	}
	
	@Override
	@Transactional
	public List<Curso> buscarCurso(String nombreCurso){
		return dCurso.buscarCurso(nombreCurso);
	}
}
