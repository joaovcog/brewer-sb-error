package com.algaworks.brewer.model;

public enum Sabor {

	ADOCICADA("Adocicada"), AMARGA("Amarga"), FORTE("Forte"), FRUTADA("Frutada"), SUAVE("Suave");

	private final String descricao;

	private Sabor(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
