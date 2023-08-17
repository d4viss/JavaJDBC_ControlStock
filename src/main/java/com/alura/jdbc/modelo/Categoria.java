package com.alura.jdbc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

	private int id;
	private String nombre;
	
	private List<Producto> productos;
	
	public Categoria(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}

	public void add(Producto producto) {
		if(productos == null) {
			productos = new ArrayList<>();
		}
		
		productos.add(producto);
	}

	public List<Producto> getProductos() {
		return this.productos;
	}
}
