package com.alura.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alura.jdbc.modelo.Categoria;
import com.alura.jdbc.modelo.Producto;

public class CategoriaDAO {

	final private Connection con; 

	public CategoriaDAO(Connection con) {
		this.con = con; 
	}

	public List<Categoria> listar(){
		List<Categoria> resultado = new ArrayList<>();

		try(con){
			var querySelected = "select id, nombre from categoria";
			System.out.println(querySelected);
			
			final PreparedStatement statement = con.prepareStatement(querySelected);

			try(statement){
				final ResultSet resultSet = statement.executeQuery();

				try(resultSet){
					while(resultSet.next()) {
						resultado.add(new Categoria(resultSet.getInt("id"), resultSet.getString("nombre")));
					}
				}
			}
			return resultado;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Categoria> listarConProductos() {
		List<Categoria> resultado = new ArrayList<>();

		try(con){
			var querySelected = "select c.id, c.nombre, p.id, p.nombre, p.cantidad"
					+ " from categoria c"
					+ " inner join producto p on c.id = p.categoria_id";
			System.out.println(querySelected);
			
			final PreparedStatement statement = con.prepareStatement(querySelected);

			try(statement){
				final ResultSet resultSet = statement.executeQuery();

				try(resultSet){
					while(resultSet.next()) {
						Integer categoriaId = resultSet.getInt("id");
						String categoriaNombre = resultSet.getString("nombre");
						
						Categoria categoria = resultado
								.stream()
								.filter(cat -> cat.getId().equals(categoriaId))
								.findAny().orElseGet(() -> {
									Categoria cat = new Categoria(categoriaId, categoriaNombre);
									resultado.add(cat);
									return cat;
								});
						
						var producto = new Producto(resultSet.getInt("p.id"), 
								resultSet.getString("p.nombre"),
								resultSet.getInt("p.cantidad"));
						
						categoria.add(producto);
								
					}
				}
			}
			return resultado;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
