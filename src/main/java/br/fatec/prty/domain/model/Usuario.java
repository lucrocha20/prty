package br.fatec.prty.domain.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tb_usuario")
public class Usuario extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Size(max = 60)
	@Column(name = "nm_nome", length = 60)
	private String nome;
	
	@Size(max = 15)
	@Column(name = "ds_cpf", length = 15)
	private String cpf;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_nascimento")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date nascimento;
	
	@Size(max = 60)
	@Column(name = "nm_cidade", length = 60)
	private String cidade;
	
	@Size(max = 20)
	@Column(name = "ds_celular", length = 20)
	private String celular;

	@Size(max = 60)
	@Email
	@Column(name = "nm_email", length = 60)
	private String email;
	
	@Column(name = "nm_senha")
	private String senha;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "tb_perfil")
	private Set<Integer> perfis = new HashSet<>();
	
	@OneToMany(mappedBy = "usuario")
	private Set<Evento> eventos;
	
	public Usuario() {
		addPerfil(TipoPerfil.USUARIO);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	public String getSenha() {
		return senha;
	}

	@JsonProperty
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Set<TipoPerfil> getPerfis() {
		Set<TipoPerfil> p = new HashSet<>();
		for (Integer tp : perfis) {
			p.add(TipoPerfil.toEnum(tp));
		}
		return p;
	}

	public void addPerfil(TipoPerfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}

}
