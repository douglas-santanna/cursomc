package com.project1.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project1.domain.Categoria;
import com.project1.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repositorioCategoria;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> objetoCategoria = repositorioCategoria.findById(id);
		return objetoCategoria.orElse(null);
	}
}
