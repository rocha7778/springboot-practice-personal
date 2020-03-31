package com.example.demo.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.app.commons.entity.models.Producto;
import com.example.demo.model.Item;

@Service("itemServiceRestTemplate")
public class ItemServiceImpl implements ItemService {

	@Autowired
	private RestTemplate clienteRest;

	@Override
	public List<Item> findAll() {
		// TODO Auto-generated method stub

		List<Producto> productos = Arrays
				.asList(clienteRest.getForObject("http://localhost:8001/productos", Producto[].class));

		List<Item> items = productos.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());

		return items;
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		// TODO Auto-generated method stub
		Map<String, String> pathVariables = new HashMap<String, String>();

		pathVariables.put("id", id.toString());

		Producto producto = clienteRest.getForObject("http://localhost:8001/productos/{id}", Producto.class,
				pathVariables);

		return new Item(producto, cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Producto update(Producto producto, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
