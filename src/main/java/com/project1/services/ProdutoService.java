package com.project1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.project1.domain.Categoria;
import com.project1.domain.Produto;
import com.project1.repositories.CategoriaRepository;
import com.project1.repositories.ProdutoRepository;
import com.project1.services.exception.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository repositorioProduto;
	
	@Autowired
	CategoriaRepository repositorioCategoria;
	
	public Produto buscar(Integer id) {
		Optional<Produto> objetoProduto = repositorioProduto.findById(id);
		return objetoProduto.orElseThrow(() -> new ObjectNotFoundException(
				"Nenhum objeto encontrado! id: "+ id+", Tipo do objeto "+ Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer pagina, Integer registroPorPagina, String ordenacao, String buscarPelaColuna){
		PageRequest pageRequest = PageRequest.of(pagina, registroPorPagina, Direction.valueOf(ordenacao), buscarPelaColuna);
		List<Categoria> categorias  = repositorioCategoria.findAllById(ids);
		return repositorioProduto.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
	
}
