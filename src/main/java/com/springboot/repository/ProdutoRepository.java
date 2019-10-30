package com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.model.Categoria;
import com.springboot.model.Produto;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	
	List<Produto> findDistinctByNomeContainingAndCategoriasIn(
			String nome,
			List<Categoria> categorias
	);
	
}
