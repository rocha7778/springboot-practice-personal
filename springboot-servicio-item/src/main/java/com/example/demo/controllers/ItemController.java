package com.example.demo.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.app.commons.entity.models.Producto;
import com.example.demo.model.Item;
import com.example.demo.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ItemController {
	
	private static Logger log = LoggerFactory.getLogger(ItemController.class);
	
	@Value("${configuracion.texto}")
	private String texto;

	@Autowired
	@Qualifier("itemServiceFeing")
	private ItemService itemservice;

	@GetMapping("/items")
	public List<Item> getItems() {
		return itemservice.findAll();
	}

	@HystrixCommand(fallbackMethod = "circuitBreakerGetItem")
	@GetMapping("/items/{id}/cantidad/{cantidad}")
	public Item getItem(@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemservice.findById(id, cantidad);
	}

	public Item circuitBreakerGetItem(Long id, Integer cantidad) {

		Item item = new Item();
		Producto producto = new Producto();
		producto.setId(id);
		producto.setFechaCreacion(new Date());
		producto.setNombre("Patineta electrica - oferta del dia");
		producto.setPrecio(1000.00);
		item.setCantidad(cantidad);
		item.setProducto(producto);
		
		return item;

	}
	
	@GetMapping("/obtener-config")
	public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String port){
		
		log.info("Puerto configurado en repositorio git"+port);
		log.info("Texto configurado en repositorio git"+texto);
		
		Map<String,String> json = new HashMap<>();	
		json.put("texto", texto);
		json.put("port",port);
		return new ResponseEntity<Map<String,String>>(json,HttpStatus.OK);
	}
	
	@PostMapping("items/productos")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto saveProducto(@RequestBody Producto producto) {
		return itemservice.save(producto);
	}
	
	@DeleteMapping("items/productos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delteProductoById(@PathVariable Long id) {
		itemservice.deleteById(id);
		
	}

}
