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

import pe.upc.spring.model.TipoAsesoria;
import pe.upc.spring.service.ITipoAsesoriaService;

@Controller
@RequestMapping("/tipoAsesoria")
public class TipoAsesoriaController {
	
	@Autowired
	private ITipoAsesoriaService taService;
	
	@RequestMapping("/")
	public String irTipoAsesoria(Map<String, Object>model) {
		model.put("listaTipoAsesorias", taService.listar());
		return "listTipoAsesorias";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("tipoAsesoria", new TipoAsesoria());
		return "tipoAsesoria";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid TipoAsesoria objTipoAsesoria, BindingResult binRes, Model model)
	throws ParseException{
		if(binRes.hasErrors())
			return "tipoAsesoria";
		else{
			boolean flag=taService.insertar(objTipoAsesoria);
			if(flag) {
				return "redirect:/tipoAsesoria/listar";
			}
			else {
				model.addAttribute("mensaje ", "Ocurrió un error");
				return "redirect:/tipoAsesoria/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid TipoAsesoria objTipoAsesoria, BindingResult binRes, Model model, RedirectAttributes objRedir)
	throws ParseException{
		if(binRes.hasErrors())
			return "redirect:/tipoAsesoria/listar";
		else{
			boolean flag=taService.modificar(objTipoAsesoria);
			if(flag) {
				objRedir.addFlashAttribute("mensaje","Se actualizó correctamente");
				return "redirect:/tipoAsesoria/listar";
			}
			else
			{
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/tipoAsesoria/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
	throws ParseException{
		Optional<TipoAsesoria>objTipoAsesoria=taService.buscarId(id);
		if(objTipoAsesoria==null)
		{
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/tipoAsesoria/listar";
		}
		else {
			model.addAttribute("tipoAsesoria",objTipoAsesoria);
			return "tipoAsesoria";
		}
		
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object>model, @RequestParam(value="id")Integer id) {
		try {
			if(id!=null&&id>0)
			{
				taService.eliminar(id);
				model.put("listaTipoAsesorias", taService.listar());
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "Ocurrió un error");
			model.put("listaTipoAsesorias", taService.listar());
		}
		return "listTipoAsesorias";
	}
	@RequestMapping("/listar")
	public String listar(Map<String, Object>model) {
		model.put("listaTipoAsesorias", taService.listar());
		return "listTipoAsesorias";
	}
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object>model, @ModelAttribute TipoAsesoria TipoAsesoria)
	throws ParseException{
		taService.listarId(TipoAsesoria.getIdTipoAsesoria());
		return "listTipoAsesoria";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object>model, @ModelAttribute TipoAsesoria TipoAsesoria)
	throws ParseException{
		List<TipoAsesoria>listaTipoAsesorias;
		TipoAsesoria.setNombreTipoAsesoria(TipoAsesoria.getNombreTipoAsesoria());
		listaTipoAsesorias=taService.buscarTipoAsesoria(TipoAsesoria.getNombreTipoAsesoria());
		
		if(listaTipoAsesorias.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaTipoAsesorias", listaTipoAsesorias);
		return "buscar";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("tipoAsesoria",new TipoAsesoria());
		return "buscar";
	}
}
