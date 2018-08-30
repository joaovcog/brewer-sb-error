package com.algaworks.brewer.service.event.venda;

import com.algaworks.brewer.model.Venda;

public class VendaEmitidaEvent {
	
	private Venda venda;
	
	public VendaEmitidaEvent(Venda venda) {
		this.venda = venda;
	}
	
	public Venda getVenda() {
		return venda;
	}
	
}