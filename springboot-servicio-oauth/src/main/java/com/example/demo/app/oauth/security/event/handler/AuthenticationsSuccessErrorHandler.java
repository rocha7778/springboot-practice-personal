package com.example.demo.app.oauth.security.event.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.demo.app.oauth.service.IUserService;
import com.example.demo.app.usuarios.commons.models.entity.Usuario;

import brave.Tracer;

@Component
public class AuthenticationsSuccessErrorHandler implements AuthenticationEventPublisher {

	private Logger logger = LoggerFactory.getLogger(AuthenticationsSuccessErrorHandler.class);
	private String message;

	@Autowired
	private IUserService usuerService;

	@Autowired
	private Tracer tracer;

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		UserDetails user = (UserDetails) authentication.getPrincipal();
		message = "Succes login " + user.getUsername();
		System.out.println(message);
		logger.info(message);

		Usuario usuario = usuerService.findByUsername(user.getUsername());

		if (usuario.getIntentosLogin() >= 0) {
			usuario.setIntentosLogin(0);
			usuerService.updateUser(usuario, usuario.getId());
		}

	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		message = "Error  login " + exception.getMessage();
		System.out.println(message);
		logger.info(message);

		try {
			StringBuilder error = new StringBuilder();
			
			error.append(" - "+message);
			
			Usuario usuario = usuerService.findByUsername(authentication.getName());
			usuario.setIntentosLogin(usuario.getIntentosLogin() + 1);

			if (usuario.getIntentosLogin() >= 3) {
				usuario.setEnabled(false);
				error.append(" - Usuario Deshabilitado por maximos intentos de login : intentos:"+usuario.getIntentosLogin());
			}
			
			tracer.currentSpan().tag("error.message", error.toString());

			usuerService.updateUser(usuario, usuario.getId());
		} catch (Exception e) {
			
			logger.info(
					String.format("publishAuthenticationFailure El usuario %s no existe", authentication.getName()));
			logger.info("publishAuthenticationFailure error ", e.getMessage());
		}

	}

}
