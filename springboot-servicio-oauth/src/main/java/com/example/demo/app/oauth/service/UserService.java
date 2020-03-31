package com.example.demo.app.oauth.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.app.oauth.client.UsuarioFeingClient;
import com.example.demo.app.usuarios.commons.models.entity.Usuario;

import brave.Tracer;

@Service
public class UserService implements UserDetailsService, IUserService {

	private Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UsuarioFeingClient users;
	
	@Autowired
	private Tracer tracer;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {

			Usuario usuario = users.findUsuarioByUserName(username);

			if (null == usuario) {
				log.error("Usuario no existe: " + username);
				throw new UsernameNotFoundException("Usuario no existe: " + username);
			}

			List<GrantedAuthority> authorities = usuario.getRoles().stream()
					.peek(role -> System.out.println(role.getNombre()))
					.map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());

			log.info("Usuario...");
			log.info("Usuario..");
			log.info("Usuario.");
			log.info("Usuario  existe: " + usuario.toString());

			return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true,
					authorities);

		} catch (Exception e) {
			String error = "Usuario no existe: " + username; 
			log.error(error);
			tracer.currentSpan().tag("error.menssage", error + " : "+ e.getMessage());
			throw new UsernameNotFoundException(error);

		}
	}

	@Override
	public Usuario findByUsername(String username) {
		return users.findUsuarioByUserName(username);
	}

	@Override
	public Usuario updateUser(Usuario usuario, Long id) {
		return users.updateUser(usuario, id);
	}

}
