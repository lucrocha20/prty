package br.fatec.prty.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.fatec.prty.domain.exception.AuthorizationException;
import br.fatec.prty.domain.exception.NegocioException;
import br.fatec.prty.domain.model.TipoPerfil;
import br.fatec.prty.domain.model.Usuario;
import br.fatec.prty.domain.repository.UsuarioRepository;
import br.fatec.prty.security.UserDetailsImpl;

@Service
public class UsuarioService implements ServiceInterface<Usuario> {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Usuario create(Usuario obj) {
		Usuario usuario = usuarioRepo.findByEmail(obj.getEmail());
		
		if (usuario != null) {
			throw new NegocioException("Já existe um cliente cadastrado com esse e-mail!");
		}
		obj.setSenha(passwordEncoder.encode(obj.getSenha()));
		return usuarioRepo.save(obj);
	}

	@Override
	public Usuario findById(Long id) {
		UserDetailsImpl usuario = UsuarioService.authenticated();
		if (usuario == null || (!usuario.hasRole(TipoPerfil.ADMIN)) && !id.equals(usuario.getId())) {
			throw new AuthorizationException("Acesso negado!");
		}
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
	
	public static UserDetailsImpl authenticated() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			return (UserDetailsImpl) auth.getPrincipal();
		}
		return null;
	}

}
