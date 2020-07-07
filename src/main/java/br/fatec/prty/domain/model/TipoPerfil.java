package br.fatec.prty.domain.model;

public enum TipoPerfil {
	ADMIN(1, "ROLE_ADMIN"),
	USUARIO(2, "ROLE_USUARIO");
	
	private Integer codigo;
	private String descricao;
	
	private TipoPerfil(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoPerfil toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		for (TipoPerfil x : TipoPerfil.values()) {
			if (codigo.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Código do perfil inválido: " + codigo);
	}

}
