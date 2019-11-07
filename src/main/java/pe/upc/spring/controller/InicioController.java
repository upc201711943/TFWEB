package pe.upc.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@RequestMapping("/eleccion")
	public String irEleccion() {
		
		return "eleccion";
	}
	@RequestMapping("/index")
	public String irIndex() {
		
		return "index";
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
