package com.alura.jdbc.modelo;

public class Producto {

	private Integer id, cantidad;
	private String descripcion, nombre;
	private int categoriaId;

	public Producto(String nombre, String descripcion, Integer cantidad) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
	}
	
	public Producto(int id, String nombre, String descripcion, Integer cantidad) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
	}

	public Producto(int id, String nombre, int cantidad) {
		this.id = id;
		this.nombre = nombre;
		this.cantidad = cantidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getNombre() {
		return nombre;
	}
	
	public int getCategoriaId() {
		return this.categoriaId;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setCategoriaID(int categoriaId) {
		this.categoriaId = categoriaId;
	}
	
	@Override
	public String toString(){
		return String.format("{id: %s, nombre: %s, descripcion: %s, cantidad: %s}", 
				this.id, this.nombre, this.descripcion, this.cantidad);
	}

	public int getId() {
		return this.id;
	}
}
