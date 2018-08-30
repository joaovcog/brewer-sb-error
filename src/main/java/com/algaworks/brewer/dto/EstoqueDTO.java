package com.algaworks.brewer.dto;

import java.math.BigDecimal;

public class EstoqueDTO {

	private BigDecimal valorItens;
	private Long quantidadeItens;

	public EstoqueDTO() {

	}

	public EstoqueDTO(BigDecimal valorItens, Long quantidadeItens) {
		this.valorItens = valorItens;
		this.quantidadeItens = quantidadeItens;
	}

	public BigDecimal getValorItens() {
		if (valorItens == null) {
			valorItens = BigDecimal.ZERO;
		}
		return valorItens;
	}

	public void setValorItens(BigDecimal valorItens) {
		this.valorItens = valorItens;
	}

	public Long getQuantidadeItens() {
		if (quantidadeItens == null) {
			quantidadeItens = 0L;
		}
		
		return quantidadeItens;
	}

	public void setQuantidadeItens(Long quantidadeItens) {
		this.quantidadeItens = quantidadeItens;
	}

}