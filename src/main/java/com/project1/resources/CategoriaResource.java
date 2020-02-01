package com.project1.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@RequestMapping(method = RequestMethod.GET)
	private String listar() {
		return "Listado";
	}
	
}
