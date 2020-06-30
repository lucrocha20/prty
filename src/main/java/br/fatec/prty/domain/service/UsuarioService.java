package br.fatec.prty.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fatec.prty.domain.exception.NegocioException;
import br.fatec.prty.domain.model.Usuario;
import br.fatec.prty.domain.repository.UsuarioRepository;

@Service
public class UsuarioService implements ServiceInterface<Usuario> {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Override
	public Usuario create(Usuario obj) {
		Usuario _usuario = usuarioRepo.findByEmail(obj.getEmail());
		
		if (_usuario != null) {
			throw new NegocioException("Já existe um cliente cadastrado com esse e-mail!");
		}
		return usuarioRepo.save(obj);
	}

	@Override
	public Usuario findById(long id) {
		Optional<Usuario> _usuario = usuarioRepo.findById(id);
		return _usuario.orElse(null);
	}

	@Override
	public List<Usuario> findAll() {
		return usuarioRepo.findAll();
	}

	@Override
	public boolean update(Usuario obj) {
		if (usuarioRepo.existsById(obj.getId())) {
			usuarioRepo.save(obj);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		Optional<Usuario> _usuario = usuarioRepo.findById(id);
		if (_usuario.isPresent()) {
			usuarioRepo.delete(_usuario.get());
			return true;
		}
		return false;
	}

}
