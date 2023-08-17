package com.alura.jdbc.controller;

import java.util.List;

import com.alura.jdbc.dao.CategoriaDAO;
import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Categoria;

public class CategoriaController {

	private CategoriaDAO categoriaDAO;
	
	public CategoriaController() {
		this.categoriaDAO = new CategoriaDAO(new ConnectionFactory().recuperarConexion());
	}
	
	public List<Categoria> listar() {
		return categoriaDAO.listar();
	}

    public List<Categoria> cargaReporte() {
        return categoriaDAO.listarConProductos();
    }

}
