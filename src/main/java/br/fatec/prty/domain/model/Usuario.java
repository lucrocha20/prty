package br.fatec.prty.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_usuario")
public class Usuario extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "nm_nome", length = 60)
	private String nome;

	@Column(name = "nm_email", length = 60)
	private String email;
	
	@Column(name = "ds_senha")
	private String senha;
	
	protected Usuario() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
