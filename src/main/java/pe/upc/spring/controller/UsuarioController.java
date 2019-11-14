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

import pe.upc.spring.ProyectoFinalApplication;
import pe.upc.spring.model.Role;
import pe.upc.spring.model.Usuario;
import pe.upc.spring.service.IRolService;
import pe.upc.spring.service.IRoleService;
import pe.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private ProyectoFinalApplication pFinal;
	
	@Autowired
	private IUsuarioService uService;

	@Autowired
	private IRoleService rService;
	@Autowired
	private IRolService rlService;

	@RequestMapping("/listar")
	public String listar(Map<String, Object>model)
	{
		model.put("listaUsuarios", uService.listar());
		return "listUsuarios";
	}
	@RequestMapping("/crear")
	public String crearUsuario(Map<String, Object>model)
	{
		return "crear";
	}
	
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object>model, @ModelAttribute Usuario usuario)
	{
		uService.listarId(usuario.getId());
		return "listUsuarios";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model)
	{
		model.addAttribute("listaRol", rlService.listar());
		model.addAttribute("usuario", new Usuario());
		return "usuario";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Usuario objUsuario, BindingResult binRes, Model model)
	throws ParseException
	{
		if(binRes.hasErrors())
			return "usuario";
		else {
				objUsuario.setPassword(pFinal.encriptar(objUsuario.getPassword()));
				boolean flag=uService.insertar(objUsuario);
				if(flag)
				{
					return insertarRole(objUsuario);
				
				}
				else {
					model.addAttribute("mensaje", "Ocurrió un error");
					return "redirect:/usuario/irRegistrar";

				}
							
			}			
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Usuario objUsuario, BindingResult binRes, Model model, RedirectAttributes objRedir)
	throws ParseException
	{
		if(binRes.hasErrors())
			return "redirect://usuario/listar";
		else {
				boolean flag=uService.modificar(objUsuario);
				if(flag) {
					objRedir.addFlashAttribute("mensaje", "Se modificó correctamente");
					return "redirect:/usuario/listar";
					}
				else {
					objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
					return "redirect:/usuario/irRegistrar";

				}
							
			}			
	}
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable long id, Model model, RedirectAttributes objRedir)
	throws ParseException{
		
		Optional<Usuario>objUsuario=uService.buscarId(id);
		if(objUsuario==null)
		{
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error al cargar");
			return "redirect://usuario/listar";
			
		}
		else
		{
			model.addAttribute("usuario", objUsuario);
			return "usuario";
		}
	}	
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String,Object>model, @RequestParam(value="id")Long id){
		try {
			if(id!=null&&id>0) {
				uService.eliminar(id);
				model.put("listaUsuarios", uService.listar());
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "Sucedio un error");
			model.put("listaUsuarios", uService.listar());
		}
		return "listUsuarios";
	}
	
	public String insertarRole(Usuario objUsuario) {

		List<Role>roles;
		String authority="";
		if(objUsuario.getRol()=="ALUMNO")
			{
				authority="ROLE_ALUMNO";
				roles=rService.obtenerRole(authority);
				objUsuario.setRoles(roles);
			}
		else 
		{			
			if(objUsuario.getRol()=="PROFESOR"){
			authority="ROLE_ALUMNO";
			roles=rService.obtenerRole(authority);
			objUsuario.setRoles(roles);
			}
		
			else {
				authority="ROLE_ADMIN";
				roles=rService.obtenerRole(authority);
				objUsuario.setRoles(roles);
			}
		}
		return "redirect:/usuario/listar";


	}
}

