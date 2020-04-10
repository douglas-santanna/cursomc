package com.project1.resources;



import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project1.domain.Categoria;
import com.project1.dto.CategoriaDTO;
import com.project1.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService servicoCategoria;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> buscar(@PathVariable Integer id){
		Categoria objetoCategoria = servicoCategoria.buscar(id);
		return ResponseEntity.ok().body(objetoCategoria);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> buscarTodos(){
		List<Categoria> ListaObjetosCategoria = servicoCategoria.buscarTodos();
		List<CategoriaDTO> ListaDTOObjetosCategoria = ListaObjetosCategoria.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(ListaDTOObjetosCategoria);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)  
	public ResponseEntity<Page<CategoriaDTO>> buscarPaginacao(
			@RequestParam(value="pagina", defaultValue="0") Integer pagina, 
			@RequestParam(value="registroPorPagina", defaultValue="24") Integer registroPorPagina, 
			@RequestParam(value="ordenacao", defaultValue="ASC") String ordenacao,
			@RequestParam(value="coluna", defaultValue="nome") String coluna){
		Page<Categoria> listaObjetosCategoria = servicoCategoria.buscarPaginacao(pagina, registroPorPagina, ordenacao, coluna);
		Page<CategoriaDTO> listaDTOObjetoCategoria = listaObjetosCategoria.map( obj -> new CategoriaDTO(obj) ); 
		return ResponseEntity.ok().body(listaDTOObjetoCategoria);		
	}

	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria objetoCategoria){
		objetoCategoria = servicoCategoria.insert(objetoCategoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objetoCategoria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria objetoCategoria, @PathVariable Integer id){
		objetoCategoria.setId(id);
		objetoCategoria = servicoCategoria.update(objetoCategoria);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		servicoCategoria.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
