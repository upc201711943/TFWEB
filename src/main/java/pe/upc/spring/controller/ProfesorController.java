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

import pe.upc.spring.model.Profesor;
import pe.upc.spring.service.IProfesorService;

@Controller
@RequestMapping("/profesor")
public class ProfesorController {

	@Autowired
	private IProfesorService pService;
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object>model) {
		model.put("listaProfesor", pService.listar());
		return "listProfesor";		
	}
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object>model, @ModelAttribute Profesor profesor)
	{
		pService.listarId(profesor.getIdProfesor());
		return "listProfesor";
	}
	
	

	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model)
	{
		model.addAttribute("profesor", new Profesor());
		return "profesor";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Profesor objProfesor, BindingResult binRes, Model model)
	throws ParseException
	{
		if(binRes.hasErrors())
			return "profesor";
		else {
				boolean flag=pService.insertar(objProfesor);
				if(flag)
					return "redirect:/profesor/listar";
				else {
					model.addAttribute("mensaje", "Ocurrió un error");
					return "redirect:/profesor/irRegistrar";

				}
							
			}			
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Profesor objProfesor, BindingResult binRes, Model model, RedirectAttributes objRedir)
	throws ParseException
	{
		if(binRes.hasErrors())
			return "redirect://profesor/listar";
		else {
				boolean flag=pService.modificar(objProfesor);
				if(flag) {
					objRedir.addFlashAttribute("mensaje", "Se modificó correctamente");
					return "redirect:/profesor/listar";
					}
				else {
					objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
					return "redirect:/profesor/irRegistrar";

				}
							
			}			
	}
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
	throws ParseException{
		
		Optional<Profesor>objProfesor=pService.buscarId(id);
		if(objProfesor==null)
		{
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error al cargar");
			return "redirect://profesor/listar";
			
		}
		else
		{
			model.addAttribute("profesor", objProfesor);
			return "profesor";
		}
	}	
	
	
	@RequestMapping("/eliminar")
	public String eliminar (Map<String, Object>model, @RequestParam(value="id")Integer id){
		try {
			if(id!=null&&id>0){
				pService.eliminar(id);
				model.put("listaProfesor", pService.listar());
			}
		}catch(Exception e)
		{
			model.put("mensaje","Ocurrió un error");
			System.out.println(e.getMessage());
			model.put("listaProfesor", pService.listar());
		}
		return "listProfesor";
	}
}
