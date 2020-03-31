package com.example.demo.app.oauth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.app.usuarios.commons.models.entity.Usuario;

@FeignClient(name = "servicio-usuario")
public interface UsuarioFeingClient {

	@GetMapping("usuarios/search/q")
	public Usuario findUsuarioByUserName(@RequestParam String username);

	@PutMapping("usuarios/{id}")
	public Usuario updateUser(@RequestBody Usuario usuario, @RequestParam Long id);

}
