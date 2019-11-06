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

import pe.upc.spring.model.TipoMaterial;
import pe.upc.spring.service.ITipoMaterialService;

@Controller
@RequestMapping("/tipoMaterial")
public class TipoMaterialController {
	
	@Autowired
	private ITipoMaterialService tmService;
	
	@RequestMapping("/")
	public String irTipoMaterial(Map<String, Object>model) {
		model.put("listaTipoMaterial", tmService.listar());
		return "listTipoMaterial";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("tipoMaterial", new TipoMaterial());
		return "tipoMaterial";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid TipoMaterial objTipoMaterial, BindingResult binRes, Model model)
	throws ParseException{
		if(binRes.hasErrors())
			return "tipoMaterial";
		else{
			boolean flag=tmService.insertar(objTipoMaterial);
			if(flag) {
				return "redirect:/tipoMaterial/listar";
			}
			else {
				model.addAttribute("mensaje ", "Ocurrió un error");
				return "redirect:/tipoMaterial/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid TipoMaterial objTipoMaterial, BindingResult binRes, Model model, RedirectAttributes objRedir)
	throws ParseException{
		if(binRes.hasErrors())
			return "redirect:/tipoMaterial/listar";
		else{
			boolean flag=tmService.modificar(objTipoMaterial);
			if(flag) {
				objRedir.addFlashAttribute("mensaje","Se actualizó correctamente");
				return "redirect:/tipoMaterial/listar";
			}
			else
			{
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/tipoMaterial/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
	throws ParseException{
		Optional<TipoMaterial>objTipoMaterial=tmService.buscarId(id);
		if(objTipoMaterial==null)
		{
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/tipoMaterial/listar";
		}
		else {
			model.addAttribute("tipoMaterial",objTipoMaterial);
			return "tipoMaterial";
		}
		
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object>model, @RequestParam(value="id")Integer id) {
		try {
			if(id!=null&&id>0)
			{
				tmService.eliminar(id);
				model.put("listaTipoMaterial", tmService.listar());
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "Ocurrió un error");
			model.put("listaTipoMaterial", tmService.listar());
		}
		return "listTipoMaterial";
	}
	@RequestMapping("/listar")
	public String listar(Map<String, Object>model) {
		model.put("listaTipoMaterial", tmService.listar());
		return "listTipoMaterial";
	}
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object>model, @ModelAttribute TipoMaterial TipoMaterial)
	throws ParseException{
		tmService.listarId(TipoMaterial.getIdTipoMaterial());
		return "listTipoMaterial";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object>model, @ModelAttribute TipoMaterial TipoMaterial)
	throws ParseException{
		List<TipoMaterial>listaTipoMaterials;
		TipoMaterial.setNombreTipoMaterial(TipoMaterial.getNombreTipoMaterial());
		listaTipoMaterials=tmService.buscarTipoMaterial(TipoMaterial.getNombreTipoMaterial());
		
		if(listaTipoMaterials.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaTipoMaterial", listaTipoMaterials);
		return "buscar";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("tipoMaterial",new TipoMaterial());
		return "buscar";
	}
}
