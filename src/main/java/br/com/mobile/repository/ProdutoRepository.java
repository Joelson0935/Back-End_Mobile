package br.com.mobile.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.mobile.domain.Categoria;
import br.com.mobile.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

//	@Query("Select Distinct produto From Produto produto InnerJoin produto.categorias categoria Where produto.nome Like %:nome% And categoria In :categorias")
//	Page<Produto> buscar(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageable);
	
}
