package br.fatec.prty.api.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fatec.prty.domain.service.UsuarioService;
import br.fatec.prty.security.JWTUtil;
import br.fatec.prty.security.UserDetailsImpl;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private JWTUtil jwtUtil;
	
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserDetailsImpl user = UsuarioService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.ok().build();
	}
}
