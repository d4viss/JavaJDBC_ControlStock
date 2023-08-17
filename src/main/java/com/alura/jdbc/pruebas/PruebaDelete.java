package com.alura.jdbc.pruebas;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.alura.jdbc.factory.ConnectionFactory;

public class PruebaDelete {

	public static void main(String[] args) throws SQLException {
		Connection con = new ConnectionFactory().recuperarConexion();

		Statement statement = con.createStatement();

		statement.execute("delete from producto where id = 99");
		
		System.out.println(statement.getUpdateCount());
	}
}
