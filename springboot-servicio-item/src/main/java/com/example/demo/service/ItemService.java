package com.example.demo.service;

import java.util.List;

import com.example.demo.app.commons.entity.models.Producto;
import com.example.demo.model.Item;

public interface ItemService {
	
	public List<Item> findAll();
	public Item findById (Long id, Integer cantidad);
	public Producto save(Producto producto);
	public void deleteById(Long id);
	public Producto update(Producto producto,Long id);

}
