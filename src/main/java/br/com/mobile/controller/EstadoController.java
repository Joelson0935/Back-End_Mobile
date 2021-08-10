package br.com.mobile.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.mobile.domain.Estado;
import br.com.mobile.domain.dto.EstadoDTO;
import br.com.mobile.service.EstadoService;

@RestController
@RequestMapping("/Estados")
public class EstadoController {

	@Autowired
	private EstadoService estadoServico;

	@GetMapping
	public ResponseEntity<List<EstadoDTO>> buscarTodos() {
		List<Estado> estados = estadoServico.buscarTodos();
		List<EstadoDTO> estDTO = estados.stream().map(estado -> new EstadoDTO(estado)).collect(Collectors.toList());
		return ResponseEntity.ok().body(estDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Estado> buscarPorId(@PathVariable Long id) {
		Estado estado = estadoServico.buscarPorId(id);
		if (estado != null) {
			return ResponseEntity.ok(estado);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Estado> adicionar(@Valid @RequestBody EstadoDTO estadoDto) {
		Estado estado = estadoServico.adicionarDto(estadoDto);
		estado = estadoServico.adicionar(estado);
		return new ResponseEntity<Estado>(estado, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Estado> atualizar(@Valid @PathVariable Long id, @RequestBody EstadoDTO estadoDto) {
		Estado est = estadoServico.buscarPorId(id);
		est = estadoServico.adicionarDto(estadoDto);
		if (est == null) {
			return ResponseEntity.notFound().build();
		}
		est.setId(id);
		estadoServico.adicionar(est);
		return new ResponseEntity<Estado>(est, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {

		if (buscarPorId(id) == null) {
			return ResponseEntity.notFound().build();
		}
		estadoServico.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
