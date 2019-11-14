package pe.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.spring.model.Rol;
import pe.upc.spring.repository.IRolDAO;
import pe.upc.spring.service.IRolService;

@Service
public class RolServiceImpl implements IRolService{

	@Autowired
	private IRolDAO dRol;
	
	@Override
	@Transactional
	public boolean insertar(Rol Rol)
	{
		Rol objRol=dRol.save(Rol);
		if(objRol==null)
			return false;
		else
			return true;
			}
	
	@Override
	@Transactional
	public boolean modificar(Rol Rol)
	{
		boolean flag=false;
		try {
			dRol.save(Rol);
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
	public void eliminar(int id) {
		dRol.deleteById(id);
	}
	
	@Override
	public Optional<Rol>buscarId(int id){
		return dRol.findById(id);
	}
	
	@Override
	public Optional<Rol>listarId(int id){
		return dRol.findById(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Rol>listar(){
		return dRol.findAll();
	}	
}
	