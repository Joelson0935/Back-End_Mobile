package br.com.mobile.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mobile.domain.Categoria;
import br.com.mobile.domain.dto.CategoriaDTO;
import br.com.mobile.service.CategoriaService;

@RestController
@RequestMapping("/Categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaServico;

	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> buscarTodos() {
		List<Categoria> categorias = categoriaServico.buscarTodos();
		List<CategoriaDTO> catDTO = categorias.stream().map(cat -> new CategoriaDTO(cat)).collect(Collectors.toList());
		return ResponseEntity.ok().body(catDTO);
	}

	@GetMapping("/Page")
	public ResponseEntity<Page<CategoriaDTO>> buscaPaginada(
			@PageableDefault(page = 0, size = 10, sort = "nome", direction = Direction.ASC) Pageable pageable) {
		Page<Categoria> categorias = categoriaServico.buscarPorPagina(pageable);
		Page<CategoriaDTO> catDTO = categorias.map(cat -> new CategoriaDTO(cat));
		return ResponseEntity.ok().body(catDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
		Categoria categoria = categoriaServico.buscarPorId(id);
		if (categoria != null) {
			return ResponseEntity.ok(categoria);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Categoria> adicionar(@Valid @RequestBody CategoriaDTO categoriaDto) {
		Categoria categoria = categoriaServico.paraDto(categoriaDto);
		categoria = categoriaServico.adicionar(categoria);
		return new ResponseEntity<Categoria>(categoria, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Categoria> atualizar(@Valid @PathVariable Long id, @RequestBody CategoriaDTO categoriaDto) {
		Categoria cat = categoriaServico.buscarPorId(id);
		cat = categoriaServico.paraDto(categoriaDto);
		if (cat == null) {
			return ResponseEntity.notFound().build();
		}
		cat.setId(id);
		cat = categoriaServico.adicionar(cat);
		return new ResponseEntity<Categoria>(cat, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {

		if (buscarPorId(id) == null) {
			return ResponseEntity.notFound().build();
		}
		categoriaServico.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
