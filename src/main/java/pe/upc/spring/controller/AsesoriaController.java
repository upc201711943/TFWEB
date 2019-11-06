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

import pe.upc.spring.model.Asesoria;
import pe.upc.spring.service.IAsesoriaService;
import pe.upc.spring.service.ISeccionService;
import pe.upc.spring.service.ITipoAsesoriaService;

@Controller
@RequestMapping("/asesoria")
public class AsesoriaController {
	
	@Autowired
	private IAsesoriaService aService;
	@Autowired
	private ISeccionService sService;
	@Autowired
	private ITipoAsesoriaService taService;
	
	@RequestMapping("/bienvenido")
	public String irAsesoriaBienvenido() {
		
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irAsesoria(Map<String, Object>model) {
		model.put("listaAsesorias", aService.listar());
		return "listAsesorias";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaTipoAsesorias",taService.listar());
		model.addAttribute("listaSeccion", sService.listar());
		model.addAttribute("asesoria", new Asesoria());
		return "asesoria";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Asesoria objAsesoria, BindingResult binRes, Model model)
	throws ParseException{
		if(binRes.hasErrors()){
			model.addAttribute("listaTipoAsesorias",taService.listar());
			model.addAttribute("listaSeccion", sService.listar());
			return "asesoria";}
		else{
			boolean flag=aService.insertar(objAsesoria);
			if(flag) {
				return "redirect:/asesoria/listar";
			}
			else {
				model.addAttribute("mensaje ", "Ocurrió un error");
				return "redirect:/asesoria/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Asesoria objAsesoria, BindingResult binRes, Model model, RedirectAttributes objRedir)
	throws ParseException{
		if(binRes.hasErrors())
			return "redirect:/asesoria/listar";
		else{
			boolean flag=aService.modificar(objAsesoria);
			if(flag) {
				objRedir.addFlashAttribute("mensaje","Se actualizó correctamente");
				return "redirect:/asesoria/listar";
			}
			else
			{
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/asesoria/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
	throws ParseException{
		Optional<Asesoria>objAsesoria=aService.buscarId(id);
		if(objAsesoria==null)
		{
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/asesoria/listar";
		}
		else {
			model.addAttribute("listaTipoAsesorias",taService.listar());
			model.addAttribute("listaSeccion", sService.listar());
			if(objAsesoria.isPresent())
			objAsesoria.ifPresent(o-> model.addAttribute("asesoria",o));
			return "asesoria";
		}
		
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object>model, @RequestParam(value="id")Integer id) {
		try {
			if(id!=null&&id>0)
			{
				aService.eliminar(id);
				model.put("listaAsesorias", aService.listar());
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "Ocurrió un error");
			model.put("listaAsesorias", aService.listar());
		}
		return "listAsesorias";
	}
	@RequestMapping("/listar")
	public String listar(Map<String, Object>model) {
		model.put("listaAsesorias", aService.listar());
		return "listAsesorias";
	}
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object>model, @ModelAttribute Asesoria Asesoria)
	throws ParseException{
		aService.listarId(Asesoria.getIdAsesoria());
		return "listAsesorias";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object>model, @ModelAttribute Asesoria Asesoria)
	throws ParseException{
		List<Asesoria>listaAsesorias;
		Asesoria.setNombreAsesoria(Asesoria.getNombreAsesoria());
		listaAsesorias=aService.buscarAsesoria(Asesoria.getNombreAsesoria());
		
		if(listaAsesorias.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaAsesorias", listaAsesorias);
		return "buscar";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("Asesoria",new Asesoria());
		return "buscar";
	}
}
