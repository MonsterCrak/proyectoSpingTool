package com.dawiproy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dawiproy.entity.Producto;

public interface Productorepository extends JpaRepository<Producto, Integer> {

}
