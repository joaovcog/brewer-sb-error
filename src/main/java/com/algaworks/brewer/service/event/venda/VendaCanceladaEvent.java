package com.algaworks.brewer.service.event.venda;

import com.algaworks.brewer.model.Venda;

public class VendaCanceladaEvent {
	
	private Venda venda;
	
	public VendaCanceladaEvent(Venda venda) {
		this.venda = venda;
	}
	
	public Venda getVenda() {
		return venda;
	}
	
}