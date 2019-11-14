package pe.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.spring.model.Role;
import pe.upc.spring.repository.IRoleDAO;
import pe.upc.spring.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService{

	@Autowired
	private IRoleDAO dRole;
	
	@Override
	@Transactional
	public boolean insertar(Role Role)
	{
		Role objRole=dRole.save(Role);
		if(objRole==null)
			return false;
		else
			return true;
			}
	
	@Override
	@Transactional
	public boolean modificar(Role Role)
	{
		boolean flag=false;
		try {
			dRole.save(Role);
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
	public void eliminar(Long id) {
		dRole.deleteById(id);
	}
	
	@Override
	public Optional<Role>buscarId(Long id){
		return dRole.findById(id);
	}
	
	@Override
	public Optional<Role>listarId(Long id){
		return dRole.findById(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Role>listar(){
		return dRole.findAll();
	}	
	
	@Override
	public List<Role> obtenerRole(String authority) {
		return dRole.obtenerRole(authority);
	}
	
	
}
	