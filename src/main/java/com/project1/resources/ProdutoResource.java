package com.project1.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project1.domain.Produto;
import com.project1.dto.ProdutoDTO;
import com.project1.resources.utils.URL;
import com.project1.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService servicoProduto;
	
	@RequestMapping(value= "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Produto> buscar(@PathVariable Integer id){
		Produto objetoProduto = servicoProduto.buscar(id);
		return ResponseEntity.ok().body(objetoProduto);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> buscarPaginacao(
			@RequestParam(value="nome", defaultValue="") String nome,
			@RequestParam(value="categorias", defaultValue="") String categorias, 
			@RequestParam(value="pagina", defaultValue="0") Integer pagina,
			@RequestParam(value="registroPorPagina", defaultValue="24") Integer registroPorPagina,
			@RequestParam(value="ordenacao", defaultValue="ASC") String ordenacao,
			@RequestParam(value="buscarPelaColuna", defaultValue="nome") String buscarPelaColuna)
		{
			String nomeFormatado = URL.formatarStringBusca(nome);
			List<Integer> ids = URL.convertStringToListInteger(categorias);
			Page<Produto> list = servicoProduto.search(nomeFormatado, ids, pagina, registroPorPagina, ordenacao, buscarPelaColuna);
			Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
			return ResponseEntity.ok().body(listDto);
		}

	
}
