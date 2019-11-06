package pe.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.spring.model.TipoMaterial;
import pe.upc.spring.repository.ITipoMaterialDAO;
import pe.upc.spring.service.ITipoMaterialService;

@Service
public class TipoMaterialServiceImpl implements ITipoMaterialService{

	@Autowired
	private ITipoMaterialDAO dTipoMaterial;
	
	@Override
	@Transactional
	public boolean insertar(TipoMaterial TipoMaterial){
		TipoMaterial objTipoMaterial=dTipoMaterial.save(TipoMaterial);
		if(objTipoMaterial==null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public boolean modificar(TipoMaterial TipoMaterial){
		boolean flag=false;
		try {
			dTipoMaterial.save(TipoMaterial);
			flag=true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return flag;
	}
	
	@Override
	@Transactional
	public void eliminar(int idTipoMaterial){
		dTipoMaterial.deleteById(idTipoMaterial);
	}
	@Override
	public Optional<TipoMaterial> buscarId(int idTipoMaterial){
		return dTipoMaterial.findById(idTipoMaterial);
	}
	@Override
	public Optional<TipoMaterial> listarId(int idTipoMaterial){
		return dTipoMaterial.findById(idTipoMaterial);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public List<TipoMaterial> listar(){
		return dTipoMaterial.findAll();
	}
	
	@Override
	@Transactional
	public List<TipoMaterial> buscarTipoMaterial(String nombreTipoMaterial){
		return dTipoMaterial.buscarTipoMaterial(nombreTipoMaterial);
	}
}
