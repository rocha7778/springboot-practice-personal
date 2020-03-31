package com.example.demo.models.service;

import java.util.List;

import com.example.demo.app.commons.entity.models.Producto;

public interface IProductoService {
	public List<Producto>findAll();
	public Producto findById(Long id);
	public Producto save(Producto producto);
	public void deleteById(Long id);
}
