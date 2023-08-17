package com.alura.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Producto;

public class ProductoDAO {

	final private Connection con;

	public ProductoDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Producto producto) {
		try(con){

			final PreparedStatement statement = con.prepareStatement("insert into producto (nombre, descripcion, cantidad, categoria_id)"
					+ "values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			try(statement){
				ejecutarRegistro(producto, statement);
			}
		}
		catch (SQLException e){
			throw new RuntimeException(e);
		}
	}

	private void ejecutarRegistro(Producto producto, PreparedStatement statement)
			throws SQLException {

		statement.setString(1, producto.getNombre());
		statement.setString(2, producto.getDescripcion());
		statement.setInt(3, producto.getCantidad());
		statement.setInt(4, producto.getCategoriaId());

		statement.execute();

		final ResultSet resultSet = statement.getGeneratedKeys(); 

		try(resultSet){
			while(resultSet.next()) {
				producto.setId(resultSet.getInt(1));
				System.out.println(String.format("fue insertado el producto %s", 
						producto));
			}			
		}
	}

	public List<Producto> listar() {
		final Connection con = new ConnectionFactory().recuperarConexion();

		try(con){
			final PreparedStatement statement = con.prepareStatement("SELECT id, nombre, descripcion, cantidad FROM producto");

			try(statement){
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				List<Producto> resultado = new ArrayList<>();

				try(resultSet){
					while( resultSet.next()) {
						Producto fila = new Producto(
								resultSet.getInt("id"), 
								resultSet.getString("nombre"), 
								resultSet.getString("descripcion"),
								resultSet.getInt("cantidad"));

						resultado.add(fila);
					}
				}
				return resultado;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int modificar(Producto producto) {
		try(con){
			final PreparedStatement statement = con.prepareStatement("update producto set "
					+ " nombre = ?"
					+ ", descripcion = ?"
					+ ", cantidad = ?"
					+ " where id = ?");
			try(statement){

				statement.setString(1, producto.getNombre());
				statement.setString(2, producto.getDescripcion());
				statement.setInt(3, producto.getCantidad());
				statement.setInt(4, producto.getId());

				statement.execute();

				int updateCount = statement.getUpdateCount();

				return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int eliminar(int id) {
		try(con){
			final PreparedStatement statement = con.prepareStatement("delete from producto where id = ?");
			try(statement){
				statement.setInt(1, id);

				statement.execute();

				int updateCount = statement.getUpdateCount();


				return updateCount;
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Producto> listar(int categoriaId) {
		final Connection con = new ConnectionFactory().recuperarConexion();

		try(con){
			var querySelected = "SELECT id, nombre, descripcion, cantidad FROM producto where categoria_id = ?";
			System.out.println(querySelected);
			
			final PreparedStatement statement = con.prepareStatement(querySelected);

			try(statement){
				statement.setInt(1, categoriaId);
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				List<Producto> resultado = new ArrayList<>();

				try(resultSet){
					while( resultSet.next()) {
						Producto fila = new Producto(
								resultSet.getInt("id"), 
								resultSet.getString("nombre"), 
								resultSet.getString("descripcion"),
								resultSet.getInt("cantidad"));

						resultado.add(fila);
					}
				}
				return resultado;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
