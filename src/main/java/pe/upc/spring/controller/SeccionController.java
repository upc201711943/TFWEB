package pe.upc.spring.controller;

import java.text.ParseException;
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

import pe.upc.spring.model.Seccion;
import pe.upc.spring.service.ICursoService;
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
	public String buscar(Map<String, Object>model, @ModelAttribute Seccion seccion)
	throws ParseException{
		List<Seccion>listaSeccion;
		seccion.setCurso(seccion.getCurso());
		listaSeccion=sService.buscarCurso(seccion.getCurso().getCodigoCurso());
		
		if(listaSeccion.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaSeccion", listaSeccion);
		return "buscar";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("seccion",new Seccion());
		return "buscar";
	}
}
