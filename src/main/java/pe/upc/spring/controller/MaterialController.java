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

import pe.upc.spring.model.Material;
import pe.upc.spring.service.IMaterialService;
import pe.upc.spring.service.ISeccionService;
import pe.upc.spring.service.ITipoMaterialService;

@Controller
@RequestMapping("/material")
public class MaterialController {
	
	@Autowired
	private IMaterialService mService;
	@Autowired
	private ISeccionService sService;
	@Autowired
	private ITipoMaterialService tmService;
	
	@RequestMapping("/")
	public String irMaterial(Map<String, Object>model) {
		model.put("listaMaterial", mService.listar());
		return "listMaterial";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaTipoMaterial",tmService.listar());
		model.addAttribute("listaSeccion", sService.listar());
		model.addAttribute("material", new Material());
		return "material";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Material objMaterial, BindingResult binRes, Model model)
	throws ParseException{
		if(binRes.hasErrors())
		{
			model.addAttribute("listaTipoMaterial",tmService.listar());
			model.addAttribute("listaSeccion", sService.listar());
			return "material";}
		else{
			boolean flag=mService.insertar(objMaterial);
			if(flag) {
				return "redirect:/material/listar";
			}
			else {
				model.addAttribute("mensaje ", "Ocurrió un error");
				return "redirect:/material/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Material objMaterial, BindingResult binRes, Model model, RedirectAttributes objRedir)
	throws ParseException{
		if(binRes.hasErrors())
			return "redirect:/material/listar";
		else{
			boolean flag=mService.modificar(objMaterial);
			if(flag) {
				objRedir.addFlashAttribute("mensaje","Se actualizó correctamente");
				return "redirect:/material/listar";
			}
			else
			{
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/material/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
	throws ParseException{
		Optional<Material>objMaterial=mService.buscarId(id);
		if(objMaterial==null)
		{
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/material/listar";
		}
		else {
			model.addAttribute("listaTipoMaterial",tmService.listar());
			model.addAttribute("listaSeccion", sService.listar());
			if(objMaterial.isPresent())
			objMaterial.ifPresent(o-> model.addAttribute("material",o));
			return "material";
		}
		
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object>model, @RequestParam(value="id")Integer id) {
		try {
			if(id!=null&&id>0)
			{
				mService.eliminar(id);
				model.put("listaMaterial", mService.listar());
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "Ocurrió un error");
			model.put("listaMaterial", mService.listar());
		}
		return "listMaterial";
	}
	@RequestMapping("/listar")
	public String listar(Map<String, Object>model) {
		model.put("listaMaterial", mService.listar());
		return "listMaterial";
	}
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object>model, @ModelAttribute Material Material)
	throws ParseException{
		mService.listarId(Material.getIdMaterial());
		return "listMaterial";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object>model, @ModelAttribute Material Material)
	throws ParseException{
		List<Material>listaMaterials;
		Material.setNombreMaterial(Material.getNombreMaterial());
		listaMaterials=mService.buscarMaterial(Material.getNombreMaterial());
		
		if(listaMaterials.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaMaterial", listaMaterials);
		return "buscar";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("material",new Material());
		return "buscar";
	}
}
