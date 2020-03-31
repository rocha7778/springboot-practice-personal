package com.example.demo.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.app.commons.entity.models.Producto;

@FeignClient(name = "servicio-producto")
public interface ProductoClienteRestFeing {
	
	@GetMapping("/productos")
	public List<Producto> findAll();
	
	@GetMapping("/productos/{id}")
	public Producto getProductoById(@PathVariable Long id);
	
	@PostMapping("/productos")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto save(@RequestBody Producto producto);
	
	@DeleteMapping("/productos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id);
	
	@PutMapping("/productos/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Producto upDateProducto(@PathVariable Long id,@RequestBody Producto producto);

}
