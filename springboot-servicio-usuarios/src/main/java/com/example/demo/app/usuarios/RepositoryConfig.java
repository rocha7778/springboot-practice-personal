package com.example.demo.app.usuarios;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;


import  com.example.demo.app.usuarios.commons.models.entity.Usuario;
import  com.example.demo.app.usuarios.commons.models.entity.Role;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		
		config.exposeIdsFor(Usuario.class,Role.class);
	}
	
	

}
