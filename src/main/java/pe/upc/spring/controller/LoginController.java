package pe.upc.spring.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
/*
	@Autowired
	IUsuarioDao dUsuario;
	
	private String link="/inicio/bienvenido";	
	
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	*/
	@GetMapping(value= {"/login","/"})
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, Principal principal,
			RedirectAttributes flash) {
		/*Usuario user=dUsuario.findByUsername(usuario.getUsername());
		link="/inicio/bienvenido";	

		
		if(user.getRol()=="ADMIN")
		{
			flash.addFlashAttribute("info", "Ya ha inciado sesión anteriormente");
			link="/inicio/bienvenido";
		}
		else 
			{
			if(user.getRol()=="ALUMNO")
			{
				flash.addFlashAttribute("info", "Ya ha inciado sesión anteriormente");
				link="/inicio/bienvenidoAlumno";
			
			}
			else
			{
				flash.addFlashAttribute("info", "Ya ha inciado sesión anteriormente");
				link="/inicio/bienvenidoProfesor";
			}
			
			}
			, @ModelAttribute Usuario usuario*/
		if (principal != null) {

			
	/*		if(user.getRol()=="ADMIN")
			{
				flash.addFlashAttribute("info", "Ya ha inciado sesión anteriormente");
				return "redirect:/inicio/bienvenido";
			}
			else 
				{
				if(user.getRol()=="ALUMNO")
				{
					flash.addFlashAttribute("info", "Ya ha inciado sesión anteriormente");
					return "redirect:/inicio/bienvenidoAlumno";
				
				}
				else
				{
					flash.addFlashAttribute("info", "Ya ha inciado sesión anteriormente");
					return "redirect:/inicio/bienvenidoProfesor";
				}
				
				}
				
		*/
			flash.addFlashAttribute("info", "Ya ha inciado sesión anteriormente");
			return "redirect:/inicio/bienvenido";
		
		}

		if (error != null) {
			model.addAttribute("error",
					"Error en el login: Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo!");
			return "error_404";
		}

		if (logout != null) {
			model.addAttribute("success", "Ha cerrado sesión con éxito!");
			return "login";
		}
		
		return "login";

	}
	@GetMapping("/loginAlumno")
	public String loginAlumno(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, Principal principal,
			RedirectAttributes flash) {
		
		if (principal != null) {
			flash.addFlashAttribute("info", "Ya ha inciado sesión anteriormente");
			return "redirect:/inicio/bienvenidoAlumno";
		}

		if (error != null) {
			model.addAttribute("error",
					"Error en el login: Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo!");
			return "loginAlumno";
		}

		if (logout != null) {
			model.addAttribute("success", "Ha cerrado sesión con éxito!");
			return "loginAlumno";
		}
		
		return "loginAlumno";

	}
	@GetMapping("/loginProfesor")
	public String loginProfesor(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, Principal principal,
			RedirectAttributes flash) {
		
		if (principal != null) {
			flash.addFlashAttribute("info", "Ya ha inciado sesión anteriormente");
			return "redirect:/inicio/bienvenido";
		}

		if (error != null) {
			model.addAttribute("error",
					"Error en el login: Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo!");
			return "loginProfesor";
		}

		if (logout != null) {
			model.addAttribute("success", "Ha cerrado sesión con éxito!");
			return "loginProfesor";
		}
		
		return "loginProfesor";

	}

}
