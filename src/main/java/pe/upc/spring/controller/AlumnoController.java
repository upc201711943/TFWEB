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

import pe.upc.spring.model.Alumno;
import pe.upc.spring.service.IAlumnoService;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {

	@Autowired
	private IAlumnoService aService;
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object>model)
	{
		model.put("listaAlumnos", aService.listar());
		return "listAlumnos";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object>model, @ModelAttribute Alumno alumno)
	{
		aService.listarId(alumno.getIdAlumno());
		return "listAlumnos";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model)
	{
		model.addAttribute("alumno", new Alumno());
		return "alumno";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Alumno objAlumno, BindingResult binRes, Model model)
	throws ParseException
	{
		if(binRes.hasErrors())
			return "alumno";
		else {
				boolean flag=aService.insertar(objAlumno);
				if(flag)
					return "redirect:/alumno/listar";
				else {
					model.addAttribute("mensaje", "Ocurrió un error");
					return "redirect:/alumno/irRegistrar";

				}
							
			}			
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Alumno objAlumno, BindingResult binRes, Model model, RedirectAttributes objRedir)
	throws ParseException
	{
		if(binRes.hasErrors())
			return "redirect://alumno/listar";
		else {
				boolean flag=aService.modificar(objAlumno);
				if(flag) {
					objRedir.addFlashAttribute("mensaje", "Se modificó correctamente");
					return "redirect:/alumno/listar";
					}
				else {
					objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
					return "redirect:/alumno/irRegistrar";

				}
							
			}			
	}
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
	throws ParseException{
		
		Optional<Alumno>objAlumno=aService.buscarId(id);
		if(objAlumno==null)
		{
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error al cargar");
			return "redirect://alumno/listar";
			
		}
		else
		{
			model.addAttribute("alumno", objAlumno);
			return "alumno";
		}
	}	
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String,Object>model, @RequestParam(value="id")Integer id){
		try {
			if(id!=null&&id>0) {
				aService.eliminar(id);
				model.put("listaAlumnos", aService.listar());
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "Sucedio un error");
			model.put("listaAlumnos", aService.listar());
		}
		return "listAlumnos";
	}
}

