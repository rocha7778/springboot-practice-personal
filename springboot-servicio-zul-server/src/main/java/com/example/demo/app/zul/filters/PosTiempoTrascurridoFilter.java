package com.example.demo.app.zul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PosTiempoTrascurridoFilter extends ZuulFilter {
	
	private static Logger log = LoggerFactory.getLogger(PosTiempoTrascurridoFilter.class);
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		
		log.info("Entrando a post ...");
		Long tiempoInicial = (Long) request.getAttribute("tiempoInicio");
		Long tiempoFinal = (Long) System.currentTimeMillis();
		Long tiempoTotal = tiempoFinal-tiempoInicial;
		log.info(String.format("Tiempo total en segundos %s",tiempoTotal.doubleValue()/1000.00));
		log.info(String.format("Tiempo total en mili segundos %s",tiempoTotal));
		
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
