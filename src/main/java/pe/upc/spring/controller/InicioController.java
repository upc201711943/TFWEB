package pe.upc.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/inicio")
public class InicioController {
	@Autowired
	private AlumnoController cAlumno;
	
	@Autowired
	private ProfesorController cProfesor;
	
	@RequestMapping("/bienvenido")
	public String irBienvenido() {
		
		return "bienvenido";
	}
	@RequestMapping("/bienvenidoAlumno")
	public String irBienvenidoAlumno() {
		
		return "bienvenidoAlumno";
	}
	@RequestMapping("/bienvenidoProfesor")
	public String irBienvenidoProfesor() {
		
		return "bienvenidoProfesor";
	}
	
	@RequestMapping("/eleccion")
	public String irEleccion() {
		
		return "eleccion";
	}
	@RequestMapping("/index")
	public String irIndex() {
		
		return "index";
	}
	@RequestMapping("/login")
	public String irLogin() {
		
		return "login";
	}
	@RequestMapping("/error_404")
	public String irError() {
		
		return "error_404";
	}

	@RequestMapping("/logout")
	 public String irLogOut() {
			
			return "logout";
		} 
	@RequestMapping("/logoutAlumno")
	 public String irLogOutAlumno() {
			
			return "logoutAlumno";
		} 
	@RequestMapping("/logoutProfesor")
	 public String irLogOutProfesor() {
			
			return "logoutProfesor";
		} 
	@RequestMapping("/loginAlumno")
	 public String irLoginAlumno() {
			
			return "logoutAlumno";
		} 
	@RequestMapping("/loginProfesor")
	 public String irLoginProfesor() {
			
			return "loginProfesor";
		} 
	@RequestMapping("/alumno")
	public String irAlumno(Model model) {
		return cAlumno.irRegistrar(model);
	}
	
	@RequestMapping("/profesor")
	public String irProfesor(Model model) {
		
		return cProfesor.irRegistrar(model);
	}
	
		
	
}
