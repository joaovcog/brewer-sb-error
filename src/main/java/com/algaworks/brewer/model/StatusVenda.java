package com.algaworks.brewer.model;

public enum StatusVenda {

	ORCAMENTO("Orçamento"), EMITIDA("Emitida"), CANCELADA("Cancelada");

	private final String descricao;

	private StatusVenda(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}