package com.project1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.project1.domain.Categoria;
import com.project1.dto.CategoriaDTO;
import com.project1.repositories.CategoriaRepository;
import com.project1.services.exception.DataIntegrityException;
import com.project1.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repositorioCategoria;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> objetoCategoria = repositorioCategoria.findById(id);
		return objetoCategoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: "+ id +", Tipo do Objeto: "+ Categoria.class.getName()));
	}
	
	public List<Categoria> buscarTodos() {
		return repositorioCategoria.findAll();
	}
	
	public Page<Categoria> buscarPaginacao(Integer pagina, Integer registroPorPagina, String ordenacao, String buscarPelaColuna){
		PageRequest pageRequest = PageRequest.of(pagina, registroPorPagina, Direction.valueOf(ordenacao), buscarPelaColuna);
		return repositorioCategoria.findAll(pageRequest);
	}

	
	public Categoria insert(Categoria objetoCategoria) {
		objetoCategoria.setId(null);
		return repositorioCategoria.save(objetoCategoria);
	}
	
	public Categoria update(Categoria objetoCategoria) {
		buscar(objetoCategoria.getId());
		return repositorioCategoria.save(objetoCategoria);
	}
	
	public void delete(Integer id) {
		buscar(id);
		try {
			repositorioCategoria.deleteById(id); 
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos associados");
		}
	}
	
	public Categoria fromDTO(CategoriaDTO objetoCategoriaDTO) {
		return new Categoria(objetoCategoriaDTO.getId(), objetoCategoriaDTO.getNome());
	}
}
