package pe.upc.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pe.upc.spring.auth.handler.LoginSuccessHandler;
import pe.upc.spring.serviceimpl.JpaUserDetailsService;;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration

public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private JpaUserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private LoginSuccessHandler successHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
			
		try {
			http.authorizeRequests()

					.antMatchers("/alumno/listar").access("hasRole('ROLE_ADMIN') ")
					.antMatchers("/profesor/listar").access("hasRole('ROLE_ADMIN')")
					.antMatchers("/tipoMaterial/**").access("hasRole('ROLE_ADMIN') ")
					.antMatchers("/tipoAsesoria/**").access("hasRole('ROLE_ADMIN')")
					.antMatchers("/seccion/**").access("hasRole('ROLE_ADMIN') ")
					.antMatchers("/curso/**").access("hasRole('ROLE_ADMIN')")
					.antMatchers("/asesoria/**").access("hasRole('ROLE_ADMIN') ")
					.antMatchers("/material/**").access("hasRole('ROLE_ADMIN')")
					.antMatchers("/matricula/**").access("hasRole('ROLE_ADMIN') ")
					
					.antMatchers("/inicio/bienvenido").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_ALUMNO')").and()
					.formLogin().successHandler(successHandler).loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/inicio/bienvenido")
					.permitAll().and().logout().logoutSuccessUrl("/login").permitAll().and().exceptionHandling().accessDeniedPage("/error_403");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

	}
}
