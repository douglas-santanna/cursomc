package com.project1.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project1.domain.Categoria;
import com.project1.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService servicoCategoria;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	private ResponseEntity<?> buscar(@PathVariable Integer id) {
		Categoria objetoCategoria = servicoCategoria.buscar(id);
		return ResponseEntity.ok().body(objetoCategoria);
	}
	
}
