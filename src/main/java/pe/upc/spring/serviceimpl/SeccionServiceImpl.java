package pe.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.spring.model.Seccion;
import pe.upc.spring.repository.ISeccionDAO;
import pe.upc.spring.service.ISeccionService;

@Service
public class SeccionServiceImpl implements ISeccionService{

	@Autowired
	private ISeccionDAO dSeccion;
	
	@Override
	@Transactional
	public boolean insertar(Seccion seccion){
		Seccion objSeccion=dSeccion.save(seccion);
		if(objSeccion==null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public boolean modificar(Seccion seccion){
		boolean flag=false;
		try {
			dSeccion.save(seccion);
			flag=true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return flag;
	}
	
	@Override
	@Transactional
	public void eliminar(int idSeccion){
		dSeccion.deleteById(idSeccion);
	}
	@Override
	public Optional<Seccion> buscarId(int idSeccion){
		return dSeccion.findById(idSeccion);
	}
	@Override
	public Optional<Seccion> listarId(int idSeccion){
		return dSeccion.findById(idSeccion);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public List<Seccion> listar(){
		return dSeccion.findAll();
	}
	
	@Override
	@Transactional
	public List<Seccion> buscarCurso(String codigoCurso){
		return dSeccion.buscarCurso(codigoCurso);
	}
	@Override
	@Transactional
	public List<Seccion> buscarProfesor(String codigoProfesor){
		return dSeccion.buscarProfesor(codigoProfesor);
	}
}
