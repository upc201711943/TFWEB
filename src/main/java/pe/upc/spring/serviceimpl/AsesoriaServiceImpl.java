package pe.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.spring.model.Asesoria;
import pe.upc.spring.repository.IAsesoriaDAO;
import pe.upc.spring.service.IAsesoriaService;

@Service
public class AsesoriaServiceImpl implements IAsesoriaService {
	@Autowired
	private IAsesoriaDAO dAsesoria;
	
	@Override
	@Transactional
	public boolean insertar(Asesoria Asesoria){
		Asesoria objAsesoria=dAsesoria.save(Asesoria);
		if(objAsesoria==null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public boolean modificar(Asesoria Asesoria){
		boolean flag=false;
		try {
			dAsesoria.save(Asesoria);
			flag=true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return flag;
	}
	
	@Override
	@Transactional
	public void eliminar(int idAsesoria){
		dAsesoria.deleteById(idAsesoria);
	}
	@Override
	public Optional<Asesoria> buscarId(int idAsesoria){
		return dAsesoria.findById(idAsesoria);
	}
	@Override
	public Optional<Asesoria> listarId(int idAsesoria){
		return dAsesoria.findById(idAsesoria);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public List<Asesoria> listar(){
		return dAsesoria.findAll();
	}
	
	@Override
	@Transactional
	public List<Asesoria> buscarAsesoria(String nombreAsesoria){
		return dAsesoria.buscarAsesoria(nombreAsesoria);
	}
}
