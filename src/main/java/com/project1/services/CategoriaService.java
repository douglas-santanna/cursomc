package com.project1.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.project1.domain.Categoria;
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
}
