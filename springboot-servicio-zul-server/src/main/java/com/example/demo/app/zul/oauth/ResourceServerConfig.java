package com.example.demo.app.zul.oauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@RefreshScope
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Value("${config.security.oauth.jwt.key}")
	private String singSecret;
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
	
		resources.tokenStore(tokenStore());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().
		antMatchers(HttpMethod.POST,"/api/security/oauth/**").permitAll().
		antMatchers(HttpMethod.GET,"/api/productos/productos/{id}").authenticated().
		antMatchers(HttpMethod.GET,"/api/usuarios/usuarios/{id}").authenticated().
		antMatchers(HttpMethod.GET,"/api/productos/**","/api/items/**","/api/usuarios/**").permitAll().
		antMatchers("/api/productos/**","/api/items/**","/api/usuarios/**").authenticated().
		anyRequest().authenticated();
	
		
		

		
		
		

	}
	
	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public  JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(singSecret);
		return tokenConverter;
	}
	
	
	

}
