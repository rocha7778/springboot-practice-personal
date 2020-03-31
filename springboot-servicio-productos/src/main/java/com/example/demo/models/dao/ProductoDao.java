package com.example.demo.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.app.commons.entity.models.Producto;

public interface ProductoDao extends CrudRepository<Producto, Long>{

}
