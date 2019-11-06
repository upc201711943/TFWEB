package pe.upc.spring.controller;

import java.text.ParseException;
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

import pe.upc.spring.model.Matricula;
import pe.upc.spring.service.IAlumnoService;
import pe.upc.spring.service.IMatriculaService;
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
			boolean flag=mService.insertar(Matricula);
			if(flag)
				return "redirect:/matricula/listar";
			else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/matricula/irRegistrar";
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
	@RequestMapping("/listar")
	public String listar(Map<String, Object>model) {
		model.put("listaMatriculas", mService.listar());
		return "listMatriculas";
	}
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object>model, @ModelAttribute Matricula matricula)
	throws ParseException{
		mService.listarId(matricula.getIdMatricula());
		return "listMatriculas";
	}
	
/*	@RequestMapping("/buscar")
	public String buscar(Map<String, Object>model, @ModelAttribute Matricula matricula)
	throws ParseException{
		List<Matricula>listaMatriculas;
		matricula.setSeccion(matricula.getSeccion());
		listaMatriculas=mService.buscarCurso(matricula.getSeccion().getCurso().getNombreCurso());
		
		if(listaMatriculas.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaMascotas", listaMatriculas);
		return "buscar";
	}*/
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("pet",new Matricula());
		return "buscar";
	}
}
