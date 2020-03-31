package com.example.demo.app.usuarios.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.example.demo.app.usuarios.commons.models.entity.Usuario;

@RepositoryRestResource(path="usuarios")
public interface UserDao extends PagingAndSortingRepository<Usuario,Long>{
	
	@RestResource(path="/q")
	public Usuario findByUsername(String username);
	
	@Query("select u from Usuario u where u.username=?1")
	public Usuario getUser(String username);

}
