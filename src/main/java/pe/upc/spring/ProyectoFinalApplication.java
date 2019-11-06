package pe.upc.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProyectoFinalApplication implements CommandLineRunner{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception{
		
		String padmin="admin";
		String palumno="alumno";
		String pdocente="docente";
		
		for(int i=0;i<2;i++) {
			String bcryptPassword =passwordEncoder.encode(padmin);
			System.out.println(bcryptPassword);
		}
		for(int i=0;i<2;i++) {
			String bcryptPassword2 =passwordEncoder.encode(palumno);
			System.out.println(bcryptPassword2);
		}
		for(int i=0;i<2;i++) {
			String bcryptPassword3 =passwordEncoder.encode(pdocente);
			System.out.println(bcryptPassword3);
		}
		
		
	} 

}
