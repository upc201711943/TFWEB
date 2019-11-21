package pe.upc.spring.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.upc.spring.model.Alumno;
import pe.upc.spring.model.Asesoria;
import pe.upc.spring.model.Material;
import pe.upc.spring.model.Matricula;
import pe.upc.spring.model.Profesor;
import pe.upc.spring.model.Seccion;
import pe.upc.spring.service.IAlumnoService;
import pe.upc.spring.service.IAsesoriaService;
import pe.upc.spring.service.IMaterialService;
import pe.upc.spring.service.IMatriculaService;
import pe.upc.spring.service.IProfesorService;
import pe.upc.spring.service.ISeccionService;

@Controller
@RequestMapping("/matricula")
public class MatriculaController {

	@Autowired
	private IMatriculaService mService;
	@Autowired
	private ISeccionService sService;
	@Autowired
	private IAlumnoService aService;
	@Autowired
	private IAsesoriaService asService;
	@Autowired
	private IMaterialService maService;
	@Autowired
	private IProfesorService pService;
	
	
	@RequestMapping("/")
	public String irRegistrar(Map<String, Object>model) {
		model.put("listaMatriculas", mService.listar());
		return "listMatriculas";
	}

	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaSeccion", sService.listar());
		model.addAttribute("listaAlumnos", aService.listar());
		model.addAttribute("matricula", new Matricula());
		return "matricula";
	}
	@RequestMapping("/alumnoIrRegistrar")
	public String alumnoIrRegistrar(Model model) {
		model.addAttribute("listaSeccion", sService.listar());
		model.addAttribute("listaAlumnos", aService.listar());
		model.addAttribute("matricula", new Matricula());
		return "matricula";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Matricula Matricula, BindingResult binRes, Model model) 
	throws ParseException{
		if(binRes.hasErrors())
		{
			model.addAttribute("listaSeccion", sService.listar());
			model.addAttribute("listaAlumnos", aService.listar());
			return "matricula";
			}
		else {
			boolean flag=validarMatricula(Matricula);
			
			if(flag)
				{
				flag=mService.insertar(Matricula);
				if(flag)				
					return "redirect:/matricula/listar";
				else {
					model.addAttribute("mensaje", "Ya se encuentra registrado");
					return "redirect:/matricula/irRegistrar";
				}

				}
			else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/matricula/irRegistrar";
			}
		}
	}
	@RequestMapping("/alumnoRegistrar")
	public String alumnoRegistrar(@ModelAttribute @Valid Matricula Matricula, BindingResult binRes, Model model) 
	throws ParseException{
		if(binRes.hasErrors())
		{
			model.addAttribute("listaSeccion", sService.listar());
			model.addAttribute("listaAlumnos", aService.listar());
			return "matricula";
			}
		else {
			boolean flag=validarMatricula(Matricula);
			
			if(flag)
				{
				flag=mService.insertar(Matricula);
				if(flag)				
					return "redirect:/matricula/perfil";
				else {
					model.addAttribute("mensaje", "Ya se encuentra registrado");
					return "redirect:/seccion/cursos";
				}

				}
			else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/seccion/cursos";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Matricula Matricula, BindingResult binRes, Model model, RedirectAttributes objRedir)
	throws ParseException{
		if(binRes.hasErrors())
			return "redirect://matricula/listar";
		else {
			boolean flag=mService.modificar(Matricula);
			if(flag) {
				objRedir.addFlashAttribute("mensaje", "Se modificó correctamente");
				return "redirect:/matricula/listar";
				
			}
			else {
				objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
				return "redirect:/matricula/irRegistrar";
			}
		}
	}
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id,Model model, RedirectAttributes objRedir) 
	throws ParseException{
		Optional<Matricula>objMatricula=mService.buscarId(id);
		if(objMatricula==null)
			{
			objRedir.addFlashAttribute("mensaje","Ocurrió un error");
			return "redirect://matricula/listar";
			}
		else {
			model.addAttribute("listaSeccion", sService.listar());
			model.addAttribute("listaAlumnos", aService.listar());
			if(objMatricula.isPresent())
				objMatricula.ifPresent(o -> model.addAttribute("matricula", o)) ;
			
			return "matricula";
		}
	}
	@RequestMapping("/añadir/{id}")
	public String añadirSeccion(@PathVariable int id,Model model, RedirectAttributes objRedir) 
	throws ParseException{
		Optional<Seccion>objSeccion=sService.buscarId(id);
		List<Alumno> listaAlumno=aService.buscarAlumno("U201711943");
		if(objSeccion==null)
			{
			objRedir.addFlashAttribute("mensaje","Ocurrió un error");
			return "redirect://seccion/cursos";
			}
		else {
			model.addAttribute("listaSeccion", objSeccion);
			model.addAttribute("listaAlumnos", listaAlumno);
			model.addAttribute("matricula", new Matricula());
			if(objSeccion.isPresent())
			 objSeccion.ifPresent( o-> model.addAttribute("listaSeccion", o));
			
			return "alumnoMatricula";
		}
	}
	@RequestMapping("/añadirMatricula/{id}")
	public String añadirMatricula(@PathVariable int id,Model model, RedirectAttributes objRedir) 
	throws ParseException{
		Optional<Seccion>objSeccion=sService.buscarId(id);
		List<Alumno> listaAlumno=aService.buscarAlumno("U201711943");
		Matricula matricula=new Matricula();
		if(objSeccion==null)
			{
			objRedir.addFlashAttribute("mensaje","Ocurrió un error");
			return "redirect://seccion/cursos";
			}
		else {
	/*		model.addAttribute("listaSeccion", objSeccion);
			model.addAttribute("listaAlumnos", listaAlumno);
			model.addAttribute("matricula", new Matricula());
			if(objSeccion.isPresent())
			 objSeccion.ifPresent( o-> model.addAttribute("listaSeccion", o));*/
			matricula.getSeccion().setIdSeccion(objSeccion.get().getIdSeccion());
			matricula.getAlumno().setIdAlumno(obtenerAlumno(listaAlumno).getIdAlumno());
			boolean flag=validarMatricula(matricula);
			
			if(flag)
				{
				flag=mService.insertar(matricula);
				if(flag)				
					return "redirect:/matricula/perfil";
				else {
					model.addAttribute("mensaje", "Ya se encuentra registrado");
					return "redirect:/seccion/cursos";
				}

				}
			else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/seccion/cursos";
			}
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object>model, @RequestParam(value="id")Integer id) {
		try {
			if(id!=null&&id>0)
			{
				mService.eliminar(id);
				model.put("listaMatriculas", mService.listar());
			}
		}catch(Exception e) {
			model.put("mensaje", "Ocurrió un error");
			System.out.println(e.getMessage());
			model.put("listaMatriculas", mService.listar());
		}
		
		return "listMatriculas";
	}
	@RequestMapping("/alumnoEliminar")
	public String alumnoEliminar(Map<String, Object>model, @RequestParam(value="id")Integer id) {
		try {
			if(id!=null&&id>0)
			{
				mService.eliminar(id);
				model.put("listaMatriculas", mService.listar());
			}
		}catch(Exception e) {
			model.put("mensaje", "Ocurrió un error");
			System.out.println(e.getMessage());
			model.put("listaMatriculas", mService.listar());
		}
		
		return "alumnoListMatriculas";
	}
	
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object>model) {
		model.put("listaMatriculas", mService.listar());
		return "listMatriculas";
	}
	@RequestMapping("/perfil")
	public String listarMatriculasxAlumno(Map<String, Object>model,@ModelAttribute Alumno alumno) {
		List<Matricula>listaMatriculas;
		//matricula.setAlumno(matricula.getAlumno());
		listaMatriculas=mService.buscarAlumno("U201711943");
		
		if(listaMatriculas.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaMatriculas", listaMatriculas);
		return "alumnoListMatriculas";

	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object>model, @ModelAttribute Matricula matricula)
	throws ParseException{
		mService.listarId(matricula.getIdMatricula());
		return "listMatriculas";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object>model, @ModelAttribute Alumno alumno)
	throws ParseException{
		List<Matricula>listaMatriculas;
		//matricula.setAlumno(matricula.getAlumno());
		listaMatriculas=mService.buscarAlumno(alumno.getCodigoAlumno());
		
		if(listaMatriculas.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaMatriculas", listaMatriculas);
		return "buscar";
	}

	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("alumno",new Alumno());
		model.addAttribute("listaAlumnos",aService.listar());
		return "buscar";
	}
	
	@RequestMapping("/irPerfil")
	public String irPerfil(Model model) {
		model.addAttribute("alumno",new Alumno());
		model.addAttribute("listaAlumnos",aService.listar());
		return "alumnoListMatriculas";
	}
	
	@RequestMapping("/buscarMaterial/{id}")
	public String buscarMaterial(@PathVariable int id,Model model, RedirectAttributes objRedir) 
	throws ParseException{
		Optional<Matricula>objMatricula=mService.buscarId(id);
		Seccion seccion=new Seccion();
		if(objMatricula==null)
			{
			objRedir.addFlashAttribute("mensaje","Ocurrió un error");
			return "redirect://matricula/irPerfil";
			}
		else {
			seccion=verSeccion(objMatricula.get());
			model.addAttribute("matricula", objMatricula.get());
			model.addAttribute("seccion", seccion);
			model.addAttribute("listaMaterial", listarMaterial(seccion));
			return "alumnoListMaterial";
		}
	}
	@RequestMapping("/buscarAsesoria/{id}")
	public String buscarAsesoria(@PathVariable int id,Model model, RedirectAttributes objRedir) 
	throws ParseException{
		Optional<Matricula>objMatricula=mService.buscarId(id);
		Seccion seccion=new Seccion();
		if(objMatricula==null)
			{
			objRedir.addFlashAttribute("mensaje","Ocurrió un error");
			return "redirect://matricula/irPerfil";
			}
		else {
			seccion=verSeccion(objMatricula.get());
			model.addAttribute("matricula", objMatricula.get());
			model.addAttribute("seccion", seccion);
			model.addAttribute("listaAsesorias", listarAsesoria(seccion));
			return "alumnoListAsesorias";
		}
	}
	
	@RequestMapping("/boletaMaterial/{id}")
	public String boletaMaterial(@PathVariable int id,Model model, RedirectAttributes objRedir) 
	throws ParseException{
		Optional<Matricula>objMatricula=mService.buscarId(id);
		Seccion seccion=new Seccion();
		double total=0;
		double totalHabilitado=0;
		double totalNoHabilitado=0;
		if(objMatricula==null)
			{
			objRedir.addFlashAttribute("mensaje","Ocurrió un error");
			return "redirect://matricula/irPerfil";
			}
		else {

			seccion=verSeccion(objMatricula.get());
			total=obtenerTotalMaterial(listarMaterial(seccion));
			totalHabilitado=obtenerTotalHabilitadoMaterial(listarMaterial(seccion));
			totalNoHabilitado=obtenerTotalNoHabilitadoMaterial(listarMaterial(seccion));
			model.addAttribute("total", total);
			model.addAttribute("totalHabilitado", totalHabilitado);
			model.addAttribute("totalNoHabilitado", totalNoHabilitado);
			model.addAttribute("matricula", objMatricula.get());
			model.addAttribute("seccion", seccion);
			model.addAttribute("listaMaterial", listarMaterial(seccion));
			return "alumnoBoletaMaterial";
		}
	}
	@RequestMapping("/boletaAsesoria/{id}")
	public String boletaAsesoria(@PathVariable int id,Model model, RedirectAttributes objRedir) 
	throws ParseException{
		Optional<Matricula>objMatricula=mService.buscarId(id);
		Seccion seccion=new Seccion();
		double total=0;
		double totalHabilitado=0;
		double totalNoHabilitado=0;
		if(objMatricula==null)
			{
			objRedir.addFlashAttribute("mensaje","Ocurrió un error");
			return "redirect://matricula/irPerfil";
			}
		else {

			seccion=verSeccion(objMatricula.get());
			total=obtenerTotalAsesoria(listarAsesoria(seccion));
			totalHabilitado=obtenerTotalHabilitadoAsesoria(listarAsesoria(seccion));
			totalNoHabilitado=obtenerTotalNoHabilitadoAsesoria(listarAsesoria(seccion));
			model.addAttribute("total", total);
			model.addAttribute("totalHabilitado", totalHabilitado);
			model.addAttribute("totalNoHabilitado", totalNoHabilitado);
			model.addAttribute("matricula", objMatricula.get());
			model.addAttribute("seccion", seccion);
			model.addAttribute("listaAsesoria", listarAsesoria(seccion));
			return "alumnoBoletaAsesoria";
		}
	}
	
	@RequestMapping("/total/{id}")
	public String total(@PathVariable int id,Model model, RedirectAttributes objRedir) 
	throws ParseException{
		Optional<Matricula>objMatricula=mService.buscarId(id);
		Seccion seccion=new Seccion();
		double total=0;
		double totalHabilitado=0;
		double totalNoHabilitado=0;
		if(objMatricula==null)
			{
			objRedir.addFlashAttribute("mensaje","Ocurrió un error");
			return "redirect://matricula/irPerfil";
			}
		else {

			seccion=verSeccion(objMatricula.get());
			total=obtenerTotalMaterial(listarMaterial(seccion));
			totalHabilitado=obtenerTotalHabilitadoMaterial(listarMaterial(seccion));
			totalNoHabilitado=obtenerTotalNoHabilitadoMaterial(listarMaterial(seccion));
			model.addAttribute("total", total);
			model.addAttribute("totalHabilitado", totalHabilitado);
			model.addAttribute("totalNoHabilitado", totalNoHabilitado);
			model.addAttribute("matricula", objMatricula.get());
			model.addAttribute("seccion", seccion);
			model.addAttribute("listaMaterial", listarMaterial(seccion));
			return "alumnoListMaterial";
		}
	}
	@RequestMapping("/buscarProfesor/{id}")
	public String buscarProfesor(@PathVariable int id,Model model, RedirectAttributes objRedir) 
	throws ParseException{
		Optional<Matricula>objMatricula=mService.buscarId(id);
		Seccion seccion=new Seccion();
		List<Seccion>listaSeccion;
		
		if(objMatricula==null)
			{
			objRedir.addFlashAttribute("mensaje","Ocurrió un error");
			return "redirect://matricula/irPerfil";
			}
		else {
			seccion=verSeccion(objMatricula.get().getSeccion());
			listaSeccion=sService.buscarProfesor(seccion.getProfesor().getCodigoProfesor());
			if(listaSeccion.isEmpty()) {
				model.addAttribute("mensaje", "No se encontró");
				return "redirect://matricula/irPerfil";
			}
			else {
				model.addAttribute("listaSeccion", listaSeccion);
				model.addAttribute("matricula",verMatricula(objMatricula.get()));
				model.addAttribute("profesor", seccion.getProfesor());
				model.addAttribute("seccion", seccion);
				return "alumnoProfesorPerfil";
			}
		}
	}
	@RequestMapping("/alumnoCalificar")
	public String alumnoCalificar(@ModelAttribute @Valid Matricula Matricula, BindingResult binRes, Model model) 
	throws ParseException{
		Matricula objMatricula=verMatricula(Matricula);
		if(objMatricula==null)
			{
			model.addAttribute("mensaje", "No se encuentra esa matrícula");
			return "redirect://matricula/buscarProfesor/"+Matricula.getIdMatricula();
			}
	
		if(binRes.hasErrors())
		{
			model.addAttribute("listaSeccion", sService.listar());
			model.addAttribute("listaAlumnos", aService.listar());
			return "redirect://matricula/buscarProfesor/"+Matricula.getIdMatricula();
			}
		else {

			objMatricula.setCalificacion(Matricula.getCalificacion());
				boolean flag=mService.insertar(objMatricula);
				if(flag) {
					flag=actualizarCalificacionProfesor(objMatricula);
					if(flag) {
					
					model.addAttribute("mensaje", "Gracias por calificar al profesor "+objMatricula.getSeccion().getProfesor().getNombreProfesor());
					return "redirect:/matricula/buscarProfesor/"+objMatricula.getIdMatricula();
					}
					else {
						model.addAttribute("mensaje", "Ocurrió un error al actualizar profesor");
						return "redirect:/matricula/buscarProfesor/"+objMatricula.getIdMatricula();
					}

					}
				else {
					model.addAttribute("mensaje", "Ocurrió un error al calificar al profesor");
					return "redirect:/matricula/buscarProfesor/"+objMatricula.getIdMatricula();
				}

		}
	}
	
	//Funciones Extra
	public double obtenerTotalMaterial(List<Material> lista) {
		double suma=0;
		for(int i=0;i<lista.size();i++)
			{
				suma+=lista.get(i).getTipoMaterial().getPrecioTipoMaterial();
			}
		return suma;
	}
	public double obtenerTotalHabilitadoMaterial(List<Material> lista) {
		double suma=0;
		for(int i=0;i<lista.size();i++)
			{
			if(lista.get(i).getDisponibilidadMaterial().equals("HABILITADO"))
				suma+=lista.get(i).getTipoMaterial().getPrecioTipoMaterial();
			}
		return suma;
	}
	public double obtenerTotalNoHabilitadoMaterial(List<Material> lista) {
		double suma=0;
		for(int i=0;i<lista.size();i++)
			{
			if(lista.get(i).getDisponibilidadMaterial().equals("DESHABILITADO"))
				suma+=lista.get(i).getTipoMaterial().getPrecioTipoMaterial();
			}
		return suma;
	}
	public double obtenerTotalAsesoria(List<Asesoria> lista) {
		double suma=0;
		for(int i=0;i<lista.size();i++)
			{
				suma+=lista.get(i).getTipoAsesoria().getPrecioTipoAsesoria();
			}
		return suma;
	}
	public double obtenerTotalHabilitadoAsesoria(List<Asesoria> lista) {
		double suma=0;
		for(int i=0;i<lista.size();i++)
			{
			if(lista.get(i).getDisponibilidadAsesoria().equals("HABILITADO"))
				suma+=lista.get(i).getTipoAsesoria().getPrecioTipoAsesoria();
			}
		return suma;
	}
	public double obtenerTotalNoHabilitadoAsesoria(List<Asesoria> lista) {
		double suma=0;
		for(int i=0;i<lista.size();i++)
			{
			if(lista.get(i).getDisponibilidadAsesoria().equals("DESHABILITADO"))
				suma+=lista.get(i).getTipoAsesoria().getPrecioTipoAsesoria();
			}
		return suma;
	}
	
	
	
	public boolean validarMatricula(Matricula matricula) {
		boolean flag=true;
		List<Matricula>lista=mService.listar();
		if(lista.isEmpty()!=true)
		for(int i=0;i<lista.size();i++)
		{
			if(lista.get(i).getSeccion().getIdSeccion()==matricula.getSeccion().getIdSeccion())
			{
				if(lista.get(i).getAlumno().getIdAlumno()==matricula.getAlumno().getIdAlumno())
					flag=false;
			}
		}
		
		return flag;
	}
	public boolean validarCalificacion(Matricula matricula) {
		boolean flag=true;
		List<Matricula>lista=mService.listar();
		if(lista.isEmpty()!=true)
		for(int i=0;i<lista.size();i++)
		{
			if(lista.get(i).getSeccion().getIdSeccion()==matricula.getSeccion().getIdSeccion())
			{
				if(lista.get(i).getAlumno().getIdAlumno()==matricula.getAlumno().getIdAlumno())
					if(lista.get(i).getCalificacion()>0)
						flag=false;
			}
		}
		
		return flag;
	}
	public Matricula verMatricula(Matricula matricula) {
		List<Matricula>lista=mService.listar();
		Matricula objMatricula=new Matricula();
		if(lista.isEmpty()!=true)
		for(int i=0;i<lista.size();i++)
		{
			if(lista.get(i).getIdMatricula()==matricula.getIdMatricula())
			{
				objMatricula=lista.get(i);

			}
		}
		return objMatricula;		
	}
	public Seccion verSeccion(Matricula matricula) {
		List<Seccion> lista=sService.listar();
		Seccion seccion=new Seccion();
		if(lista.isEmpty()!=true)
		for(int i=0;i<lista.size();i++)
		{
			if(lista.get(i).getIdSeccion()==matricula.getSeccion().getIdSeccion())
			{
				seccion=lista.get(i);
			}
		}
		
		return seccion;
	}
	public List<Material> listarMaterial(Seccion seccion) {
		List<Material> lista=maService.listar();
		List<Material> listaMaterial=new ArrayList<Material>();
		
		for(int i=0;i<lista.size();i++)
		{
			if(lista.get(i).getSeccion().getIdSeccion()==seccion.getIdSeccion())
			{
				if(lista.get(i).getDisponibilidadMaterial().equals("DESHABILITADO"))
					lista.get(i).setUrlMaterial("COMUNÍCATE CON EL PROFESOR PARA OBTENER ESTE MATERIAL");
				listaMaterial.add(lista.get(i));
			
			}
		}
		
		return listaMaterial;
	}
	public List<Asesoria> listarAsesoria(Seccion seccion) {
		List<Asesoria> lista=asService.listar();
		List<Asesoria> listaAsesoria=new ArrayList<Asesoria>();
		
		for(int i=0;i<lista.size();i++)
		{
			if(lista.get(i).getSeccion().getIdSeccion()==seccion.getIdSeccion())
			{
				if(lista.get(i).getDisponibilidadAsesoria().equals("DESHABILITADO"))
					lista.get(i).setUrlAsesoria("COMUNÍCATE CON EL PROFESOR PARA OBTENER ESTA ASESORÍA");
				listaAsesoria.add(lista.get(i));
				
			}
		}
		
		return listaAsesoria;
	}

	public Alumno obtenerAlumno(List<Alumno> alumnos) {
		
		Alumno alumno=new Alumno();
		
		for(int i=0;i<alumnos.size();i++)
			alumno.setIdAlumno(alumnos.get(i).getIdAlumno());
		return alumno;
	}
	public Seccion verSeccion(Seccion objSeccion) {
		List<Seccion> lista=sService.listar();
		Seccion seccion=new Seccion();
		if(lista.isEmpty()!=true)
		for(int i=0;i<lista.size();i++)
		{
			if(lista.get(i).getCodigoSeccion()==objSeccion.getCodigoSeccion())
			{
				seccion=lista.get(i);
			}
		}
		
		return seccion;
	}
	public boolean actualizarCalificacionProfesor(Matricula matricula) {
		List<Matricula>listaM=mService.listar();
		double promedio=0;
		int cont=0;
		Profesor profesor=new Profesor();
		boolean flag=true;
		for(int i=0;i<listaM.size();i++)
		{
			if(listaM.get(i).getSeccion().getProfesor().getIdProfesor()==matricula.getSeccion().getProfesor().getIdProfesor())
			{
				if(listaM.get(i).getCalificacion()>0&&listaM.get(i).getCalificacion()<=100)
				{
				cont++;
				promedio+=listaM.get(i).getCalificacion();
			}
			}
		}
		if(cont!=0)
		{

			promedio=promedio/cont;
			profesor=matricula.getSeccion().getProfesor();
			profesor.setCalificacionProfesor(promedio);
			flag=pService.insertar(profesor);
				
		}
		else
			flag=false;
		
		return flag;		
		
	}
}
