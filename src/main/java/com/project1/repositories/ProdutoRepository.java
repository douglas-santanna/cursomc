package com.project1.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project1.domain.Categoria;
import com.project1.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>  {
	
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias categoria WHERE obj.nome LIKE %:nome% AND categoria IN :idsCategorias")
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(@Param("nome") String nome, @Param("idsCategorias") List<Categoria> idsCategorias, Pageable pageRequest);
	
}
