package pe.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.spring.model.TipoAsesoria;
import pe.upc.spring.repository.ITipoAsesoriaDAO;
import pe.upc.spring.service.ITipoAsesoriaService;

@Service
public class TipoAsesoriaServiceImpl implements ITipoAsesoriaService{

	@Autowired
	private ITipoAsesoriaDAO dTipoAsesoria;
	
	@Override
	@Transactional
	public boolean insertar(TipoAsesoria TipoAsesoria){
		TipoAsesoria objTipoAsesoria=dTipoAsesoria.save(TipoAsesoria);
		if(objTipoAsesoria==null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public boolean modificar(TipoAsesoria TipoAsesoria){
		boolean flag=false;
		try {
			dTipoAsesoria.save(TipoAsesoria);
			flag=true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return flag;
	}
	
	@Override
	@Transactional
	public void eliminar(int idTipoAsesoria){
		dTipoAsesoria.deleteById(idTipoAsesoria);
	}
	@Override
	public Optional<TipoAsesoria> buscarId(int idTipoAsesoria){
		return dTipoAsesoria.findById(idTipoAsesoria);
	}
	@Override
	public Optional<TipoAsesoria> listarId(int idTipoAsesoria){
		return dTipoAsesoria.findById(idTipoAsesoria);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public List<TipoAsesoria> listar(){
		return dTipoAsesoria.findAll();
	}
	
	@Override
	@Transactional
	public List<TipoAsesoria> buscarTipoAsesoria(String nombreTipoAsesoria){
		return dTipoAsesoria.buscarTipoAsesoria(nombreTipoAsesoria);
	}
}
