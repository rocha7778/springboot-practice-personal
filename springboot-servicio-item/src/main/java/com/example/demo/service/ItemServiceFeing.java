package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.clientes.ProductoClienteRestFeing;
import com.example.demo.model.Item;
import com.example.demo.app.commons.entity.models.Producto;

@Service("itemServiceFeing")
@Primary
public class ItemServiceFeing implements ItemService {
	
	@Autowired
	private ProductoClienteRestFeing clientFeignService;

	@Override
	public List<Item> findAll() {
		return clientFeignService.findAll().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		return new Item(clientFeignService.getProductoById(id),cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		return clientFeignService.save(producto);
	}

	@Override
	public void deleteById(Long id) {
		clientFeignService.deleteById(id);
	}

	@Override
	public Producto update(Producto producto,Long id) {
		
		Producto productoBd = clientFeignService.getProductoById(id);
		productoBd.setNombre(producto.getNombre());
		productoBd.setPrecio(producto.getPrecio());
		
		return clientFeignService.save(productoBd);
	}

}
