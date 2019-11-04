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

import pe.upc.spring.model.Curso;
import pe.upc.spring.service.ICursoService;

@Controller
@RequestMapping("/curso")
public class CursoController {

	@Autowired
	private ICursoService cService;
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model)
	{
		model.put("listaCursos", cService.listar());
		return "listCursos";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("curso", new Curso());
		return "curso";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Curso objCurso, BindingResult binRes, Model model)
	throws ParseException{
		if(binRes.hasErrors())
			return "curso";
		else {
			boolean flag=cService.insertar(objCurso);
			if(flag)
				return "redirect:/curso/listar";
			else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/curso/irRegistrar";
			}
		}
	}
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Curso objCurso, BindingResult binRes, Model model, RedirectAttributes objRedir)
	throws ParseException{
		if(binRes.hasErrors())
			return "redirect://curso/listar";
		else {
			boolean flag=cService.modificar(objCurso);
			if(flag) {
				objRedir.addFlashAttribute("mensaje","Se modificó correctamente");
				return "redirect:/curso/listar";
			}
			else
			{
				objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
				return "redirect:/curso/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
	throws ParseException{
		Optional<Curso>objCurso=cService.buscarId(id);
		if(objCurso==null)
		{
			objRedir.addFlashAttribute("mensaje","Ocurrió un error al cargar");
			return "redirect://curso/listar";
		}
		else {
			model.addAttribute("curso",objCurso);
			return "curso";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object>model, @RequestParam(value="id")Integer id) {
		try {
			if(id!=null&&id>0){
				cService.eliminar(id);
				model.put("listaCursos", cService.listar());
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "Sucedió un error");
			model.put("listaCursos", cService.listar());
		}
		return "listCursos";
	}
	
}
