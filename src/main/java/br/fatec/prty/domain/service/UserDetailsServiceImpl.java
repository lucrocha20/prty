package br.fatec.prty.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.fatec.prty.domain.exception.NegocioException;
import br.fatec.prty.domain.model.Usuario;
import br.fatec.prty.domain.repository.UsuarioRepository;
import br.fatec.prty.security.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepo.findByEmail(email);
		if (usuario == null) {
			throw new NegocioException("Usuário não encontrado!");
		}
		return new UserDetailsImpl(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getPerfis()) ;
	}

}
