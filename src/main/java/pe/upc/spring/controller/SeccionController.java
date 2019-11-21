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

import pe.upc.spring.model.Asesoria;
import pe.upc.spring.model.Curso;
import pe.upc.spring.model.Material;
import pe.upc.spring.model.Matricula;
import pe.upc.spring.model.Profesor;
import pe.upc.spring.model.Seccion;
import pe.upc.spring.service.IAsesoriaService;
import pe.upc.spring.service.ICursoService;
import pe.upc.spring.service.IMaterialService;
import pe.upc.spring.service.IProfesorService;
import pe.upc.spring.service.ISeccionService;

@Controller
@RequestMapping("/seccion")
public class SeccionController {
	
	@Autowired
	private ISeccionService sService;
	@Autowired
	private ICursoService cService;
	@Autowired
	private IProfesorService pService;
	@Autowired
	private IMaterialService maService;
	@Autowired
	private IAsesoriaService asService;
	
	@RequestMapping("/")
	public String irSeccion(Map<String, Object>model) {
		model.put("listaSeccion", sService.listar());
		return "listSeccion";
	}

	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaCursos", cService.listar());
		model.addAttribute("listaProfesor", pService.listar());
		model.addAttribute("seccion", new Seccion());
		return "seccion";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Seccion seccion, BindingResult binRes, Model model) 
	throws ParseException{
		if(binRes.hasErrors())
			return "seccion";
		else {
			boolean flag=sService.insertar(seccion);
			if(flag)
				return "redirect:/seccion/listar";
			else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/seccion/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Seccion seccion, BindingResult binRes, Model model, RedirectAttributes objRedir)
	throws ParseException{
		if(binRes.hasErrors())
			return "redirect://seccion/listar";
		else {
			boolean flag=sService.modificar(seccion);
			if(flag) {
				objRedir.addFlashAttribute("mensaje", "Se modificó correctamente");
				return "redirect:/seccion/listar";
				
			}
			else {
				objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
				return "redirect:/seccion/irRegistrar";
			}
		}
	}
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id,Model model, RedirectAttributes objRedir) 
	throws ParseException{
		Optional<Seccion>objSeccion=sService.buscarId(id);
		if(objSeccion==null)
			{
			objRedir.addFlashAttribute("mensaje","Ocurrió un error");
			return "redirect://seccion/listar";
			}
		else {
			model.addAttribute("listaCursos", cService.listar());
			model.addAttribute("listaProfesor", pService.listar());
			if(objSeccion.isPresent())
			 objSeccion.ifPresent( o-> model.addAttribute("seccion", o));
			return "seccion";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object>model, @RequestParam(value="id")Integer id) {
		try {
			if(id!=null&&id>0)
			{
				sService.eliminar(id);
				model.put("listaSeccion", sService.listar());
			}
		}catch(Exception e) {
			model.put("mensaje", "Ocurrió un error");
			System.out.println(e.getMessage());
			model.put("listaSeccion", sService.listar());
		}
		
		return "listSeccion";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object>model) {
		model.put("listaSeccion", sService.listar());
		return "listSeccion";
	}
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object>model, @ModelAttribute Seccion seccion)
	throws ParseException{
		sService.listarId(seccion.getIdSeccion());
		return "listSeccion";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object>model, @ModelAttribute Curso curso)
	throws ParseException{
		List<Curso>listaCurso=new ArrayList<>();
		listaCurso=cService.buscarCurso(curso.getNombreCurso().toUpperCase());
		if(listaCurso.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaCursos", listaCurso);
		return "profesorBuscarCurso";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("curso",new Curso());
		return "profesorBuscarCurso";
	}
	
	@RequestMapping("/cursos")
	public String cursos(Map<String, Object>model) {
		model.put("listaSeccion", sService.listar());
		return "alumnoListSeccion";
	}
	@RequestMapping("/irPerfil")
	public String irPerfil(Model model) {
		model.addAttribute("profesor",new Profesor());
		model.addAttribute("listaProfesor",pService.listar());
		return "profesorListMatriculas";
	}
	@RequestMapping("/perfil")
	public String listarSeccionxProfesor(Map<String, Object>model,@ModelAttribute Profesor profesor) {
		List<Seccion>listaSeccion;
		listaSeccion=sService.buscarProfesor("pcsieoca");
		
		if(listaSeccion.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaSeccion", listaSeccion);
		return "profesorListSeccion";

	}
	@RequestMapping("/buscarMaterial/{id}")
	public String buscarMaterial(@PathVariable int id,Model model, RedirectAttributes objRedir) 
	throws ParseException{
		Optional<Seccion>objSeccion=sService.buscarId(id);
		Seccion seccion=new Seccion();
		if(objSeccion==null)
			{
			objRedir.addFlashAttribute("mensaje","Ocurrió un error");
			return "redirect://seccion/irPerfil";
			}
		else {
			seccion=verSeccion(objSeccion.get());
			model.addAttribute("seccion", seccion);
			model.addAttribute("listaMaterial", listarMaterial(seccion));
			return "profesorListMaterial";
		}
	}
	@RequestMapping("/buscarAsesoria/{id}")
	public String buscarAsesoria(@PathVariable int id,Model model, RedirectAttributes objRedir) 
	throws ParseException{
		Optional<Seccion>objSeccion=sService.buscarId(id);
		Seccion seccion=new Seccion();
		if(objSeccion==null)
			{
			objRedir.addFlashAttribute("mensaje","Ocurrió un error");
			return "redirect://seccion/irPerfil";
			}
		else {
			seccion=verSeccion(objSeccion.get());
			model.addAttribute("seccion", seccion);
			model.addAttribute("listaAsesorias", listarAsesoria(seccion));
			return "profesorListAsesorias";
		}
	}
	@RequestMapping("/profesorEliminar")
	public String profesorEliminar(Map<String, Object>model, @RequestParam(value="id")Integer id) {
		try {
			if(id!=null&&id>0)
			{
				sService.eliminar(id);
				model.put("listaSeccion", sService.listar());
			}
		}catch(Exception e) {
			model.put("mensaje", "Ocurrió un error");
			System.out.println(e.getMessage());
			model.put("listaSeccion", sService.listar());
		}
		
		return "redirect:/seccion/perfil";
	}
	@RequestMapping("/añadir/{id}")
	public String añadirCurso(@PathVariable int id,Model model, RedirectAttributes objRedir) 
	throws ParseException{
		Optional<Curso>objCurso=cService.buscarId(id);
		List<Profesor> listaProfesor=pService.buscarCodigo("pcsieoca");
		if(objCurso==null)
			{
			objRedir.addFlashAttribute("mensaje","Ocurrió un error");
			return "redirect://seccion/cursos";
			}
		else {
			model.addAttribute("listaCursos", objCurso);
			model.addAttribute("listaProfesor", listaProfesor);
			model.addAttribute("seccion", new Seccion());
			if(objCurso.isPresent())
			 objCurso.ifPresent( o-> model.addAttribute("listaCursos", o));
			
			return "profesorSeccion";
		}
		
	}
	@RequestMapping("/profesorIrRegistrar")
	public String profesorIrRegistrar(Model model) {
		model.addAttribute("listaCursos", cService.listar());
		model.addAttribute("listaProfesor", pService.listar());
		model.addAttribute("seccion", new Matricula());
		return "seccion";
	}
	@RequestMapping("/profesorRegistrar")
	public String profesorRegistrar(@ModelAttribute @Valid Seccion seccion, BindingResult binRes, Model model) 
	throws ParseException{
		if(binRes.hasErrors())
		{
			model.addAttribute("listaCursos", cService.listar());
			model.addAttribute("listaProfesor", pService.listar());
			return "seccion";
			}
		else {
			boolean flag=validarSeccion(seccion);
			
			if(flag)
				{
				flag=sService.insertar(seccion);
				if(flag)				
					return "redirect:/seccion/perfil";
				else {
					model.addAttribute("mensaje", "Ya se encuentra registrado");
					return "redirect:/curso/cursos";
				}

				}
			else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/curso/cursos";
			}
		}
	}
	
	public boolean validarSeccion(Seccion seccion) {
		boolean flag=true;
		List<Seccion>lista=sService.listar();
		
		for(int i=0;i<lista.size();i++)
		{
			if(lista.get(i).getCodigoSeccion().equals(seccion.getCodigoSeccion()))
			{
				//if(lista.get(i).getCurso().getIdCurso()==seccion.getCurso().getIdCurso())
					flag=false;
			}
		}
		
		return flag;
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
	public List<Material> listarMaterial(Seccion seccion) {
		List<Material> lista=maService.listar();
		List<Material> listaMaterial=new ArrayList<Material>();
		
		for(int i=0;i<lista.size();i++)
		{
			if(lista.get(i).getSeccion().getIdSeccion()==seccion.getIdSeccion())
			{
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
				listaAsesoria.add(lista.get(i));
			}
		}
		
		return listaAsesoria;
	}

	public Profesor obtenerProfesor(List<Profesor> profesores) {
		
		Profesor profesor=new Profesor();
		
		for(int i=0;i<profesores.size();i++)
			profesor.setIdProfesor(profesores.get(i).getIdProfesor());
		return profesor;
	}
}
