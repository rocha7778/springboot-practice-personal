package com.example.demo.app.oauth.service;

import com.example.demo.app.usuarios.commons.models.entity.Usuario;

public interface IUserService {

	public Usuario findByUsername(String username);

	public Usuario updateUser(Usuario usuario, Long id);

}
