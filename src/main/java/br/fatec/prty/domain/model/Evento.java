package br.fatec.prty.domain.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "tb_evento")
@Entity
public class Evento extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name="nm_nome", length=60)
	private String nome;
	
	@Column(name="nm_local", length=60)
	private String local;
	
	@Column(name="vl_valor")
	private BigDecimal valor;
	
	@Column(name="ds_descricao", length=200)
	private String descricao;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dt_data")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date data;

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
