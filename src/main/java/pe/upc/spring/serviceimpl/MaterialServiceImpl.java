package pe.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.spring.model.Material;
import pe.upc.spring.repository.IMaterialDAO;
import pe.upc.spring.service.IMaterialService;

@Service
public class MaterialServiceImpl implements IMaterialService{
	@Autowired
	private IMaterialDAO dMaterial;
	
	@Override
	@Transactional
	public boolean insertar(Material Material){
		Material objMaterial=dMaterial.save(Material);
		if(objMaterial==null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public boolean modificar(Material Material){
		boolean flag=false;
		try {
			dMaterial.save(Material);
			flag=true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return flag;
	}
	
	@Override
	@Transactional
	public void eliminar(int idMaterial){
		dMaterial.deleteById(idMaterial);
	}
	@Override
	public Optional<Material> buscarId(int idMaterial){
		return dMaterial.findById(idMaterial);
	}
	@Override
	public Optional<Material> listarId(int idMaterial){
		return dMaterial.findById(idMaterial);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public List<Material> listar(){
		return dMaterial.findAll();
	}
	
	@Override
	@Transactional
	public List<Material> buscarMaterial(String nombreMaterial){
		return dMaterial.buscarMaterial(nombreMaterial);
	}
}
