package br.fatec.prty.domain.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_evento")
public class Evento extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@NotBlank
	@Size(max = 60)
	@Column(name="nm_nome", length=60)
	private String nome;
	
	@Size(max = 60)
	@Column(name="nm_local", length=60)
	private String local;
	
	@Column(name = "qt_lotacao")
	private Long lotacao;
	
	@Column(name="vl_valor")
	private BigDecimal valor;
	
	@Size(max = 200)
	@Column(name="ds_descricao", length=200)
	private String descricao;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dt_data")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date data;
	
	public Evento() {}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Long getLotacao() {
		return lotacao;
	}

	public void setLotacao(Long lotacao) {
		this.lotacao = lotacao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
