package com.dawiproy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import com.dawiproy.security.Security;




//archivo de configuración
@Configuration
//habilitar seguridad
@EnableWebSecurity
//habilitar método para validar clave
@EnableMethodSecurity
public class SecurityConfig{

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().requestMatchers("/resources/img/**").permitAll().and().
		csrf().disable().authorizeHttpRequests().
		requestMatchers("/validar/**").permitAll().
		and().authorizeHttpRequests().
		requestMatchers("/producto/**","/empleado/**","/sucursal/**","/cliente/**","/boleta/**").authenticated().
		and().formLogin().loginPage("/validar/usuario").
		defaultSuccessUrl("/validar/intranet");

		return http.build();
	}


	
	@Bean
	public UserDetailsService userDetailsService() {
		return new Security();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider dao=new DaoAuthenticationProvider();
		dao.setUserDetailsService(userDetailsService());
		dao.setPasswordEncoder(password());
		return dao;
	}
	
	@Bean
	public BCryptPasswordEncoder password(){
		return new BCryptPasswordEncoder();
	}
}



