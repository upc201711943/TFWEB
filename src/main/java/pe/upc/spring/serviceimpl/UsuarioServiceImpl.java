package pe.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.spring.model.Usuario;
import pe.upc.spring.repository.IUsuarioDao;
import pe.upc.spring.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	private IUsuarioDao dUsuario;
	
	@Override
	@Transactional
	public boolean insertar(Usuario Usuario)
	{
		Usuario objUsuario=dUsuario.save(Usuario);
		if(objUsuario==null)
			return false;
		else
			return true;
			}
	
	@Override
	@Transactional
	public boolean modificar(Usuario Usuario)
	{
		boolean flag=false;
		try {
			dUsuario.save(Usuario);
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
		dUsuario.deleteById(id);
	}
	
	@Override
	public Optional<Usuario>buscarId(Long id){
		return dUsuario.findById(id);
	}
	
	@Override
	public Optional<Usuario>listarId(Long id){
		return dUsuario.findById(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Usuario>listar(){
		return dUsuario.findAll();
	}	
}
	