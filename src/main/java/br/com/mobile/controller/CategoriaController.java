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
import br.com.mobile.repository.CategoriaRepository;
import br.com.mobile.service.CategoriaService;

@RestController
@RequestMapping("/Categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaServico;
	@Autowired
	private CategoriaRepository categoriaRepositorio;

	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> buscarTodos() {
		List<Categoria> categorias = categoriaServico.buscarTodos();
		List<CategoriaDTO> catDTO = categorias.stream().map(cat -> new CategoriaDTO(cat)).collect(Collectors.toList());
		return ResponseEntity.ok().body(catDTO);
	}

//	@GetMapping(value = "/Pagina")
//	public ResponseEntity<Page<CategoriaDTO>> buscarPagina(
//			@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
//			@RequestParam(value = "buscaPorPagina", defaultValue = "24") Integer buscaPorPagina,
//			@RequestParam(value = "direcao", defaultValue = "ASC") String direcao,
//			@RequestParam(value = "ordenarPor", defaultValue = "id") String ordenarPor) {
//		Page<Categoria> categorias = categoriaServico.buscarPagina(pagina, buscaPorPagina, direcao, ordenarPor);
//		Page<CategoriaDTO> catDTO = categorias.map(cat -> new CategoriaDTO(cat));
//		return ResponseEntity.ok().body(catDTO);
//	}

	@GetMapping(value = "/Pagina") // caso eu queira ordenar por mais de um campo : sort = {"campo1", "campo2"}
	public ResponseEntity<Page<CategoriaDTO>> buscarPagina(
			@PageableDefault(page = 0, size = 6, sort = "id", direction = Direction.ASC) Pageable pageable) {
		Page<Categoria> categorias = categoriaRepositorio.findAll(pageable);
		Page<CategoriaDTO> catDTO = categorias.map(cat -> new CategoriaDTO(cat));
		if (categorias.isEmpty()) {
			return new ResponseEntity<Page<CategoriaDTO>>(HttpStatus.NOT_FOUND);
		}
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

	@PostMapping // POST USANDO O PADRÃO DTO
	public ResponseEntity<Categoria> adicionar(@Valid @RequestBody CategoriaDTO categoriaDto) {
		Categoria categoria = categoriaServico.adicionarDto(categoriaDto);
		categoria = categoriaServico.adicionar(categoria);
		return new ResponseEntity<Categoria>(categoria, HttpStatus.CREATED);
	}

	@PutMapping("/{id}") // UPDATE USANDO O PADRÃO DTO
	public ResponseEntity<Categoria> atualizar(@Valid @PathVariable Long id, @RequestBody CategoriaDTO catDto) {
		Categoria categoria = categoriaServico.buscarPorId(id);
		categoria = categoriaServico.adicionarDto(catDto);
		if (categoria == null) {
			return ResponseEntity.notFound().build();
		}
		categoria.setId(id);
		categoriaServico.adicionar(categoria);
		return new ResponseEntity<Categoria>(categoria, HttpStatus.OK);
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
