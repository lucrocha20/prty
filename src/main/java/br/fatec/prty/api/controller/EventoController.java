package br.fatec.prty.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fatec.prty.domain.model.Evento;
import br.fatec.prty.domain.service.EventoService;

@RestController
@RequestMapping("/eventos")
public class EventoController implements ControllerInterface<Evento> {

	@Autowired
	private EventoService eventos;
	
	public EventoController(EventoService eventos) {
		this.eventos = eventos;
	}
	
	@Override
	@GetMapping
	@CrossOrigin(origins = "*")
	public ResponseEntity<List<Evento>> get() {
		return ResponseEntity.ok(eventos.findAll());
	}

	@Override
	@GetMapping(value = "/{id}")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		Evento _evento = eventos.findById(id);
		if (_evento != null) {
			return ResponseEntity.ok(_evento);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@PostMapping
	@CrossOrigin(origins = "*")
	public ResponseEntity<Evento> post(@RequestBody Evento obj) {
		eventos.create(obj);
		return ResponseEntity.ok(obj);
	}

	@Override
	@PutMapping
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> put(@RequestBody Evento obj) {
		if (eventos.update(obj)) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@DeleteMapping(value = "/{id}")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if (eventos.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
