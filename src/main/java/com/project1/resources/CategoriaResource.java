package com.project1.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project1.domain.Categoria;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@RequestMapping(method = RequestMethod.GET)
	private List<Categoria> listar() {
		
		Categoria cat1 = new Categoria(1,"Informática");
		Categoria cat2 = new Categoria(2,"Escritório");
		
		List<Categoria> listaDeCategoria = new ArrayList<Categoria>();
		
		listaDeCategoria.add(cat1);
		listaDeCategoria.add(cat2);
		
		return listaDeCategoria;
		
	}
	
}
