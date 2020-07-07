package br.fatec.prty.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fatec.prty.domain.model.Usuario;
import br.fatec.prty.domain.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController implements ControllerInterface<Usuario> {

	@Autowired
	private UsuarioService usuarios;
	
	public UsuarioController(UsuarioService usuarios) {
		this.usuarios = usuarios;
	}
	
	@Override
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<Usuario>> get() {
		return ResponseEntity.ok(usuarios.findAll());
	}

	@Override
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		Usuario _usuario = usuarios.findById(id);
		if (_usuario != null) {
			return ResponseEntity.ok(_usuario);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@PostMapping
	public ResponseEntity<Usuario> post(@Valid @RequestBody Usuario obj) {
		usuarios.create(obj);
		return ResponseEntity.ok(obj);
	}

	@Override
	@PutMapping
	public ResponseEntity<?> put(@Valid @RequestBody Usuario obj) {
		if (usuarios.update(obj)) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if (usuarios.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
