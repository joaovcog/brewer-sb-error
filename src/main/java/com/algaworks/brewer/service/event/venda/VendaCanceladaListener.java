package com.algaworks.brewer.service.event.venda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.model.ItemVenda;
import com.algaworks.brewer.model.StatusVenda;
import com.algaworks.brewer.repository.CervejaRepository;

@Component
public class VendaCanceladaListener {

	@Autowired
	private CervejaRepository cervejaRepository;

	@EventListener
	public void vendaCancelada(VendaCanceladaEvent vendaCanceladaEvent) {
		if (vendaCanceladaEvent.getVenda().getStatus().equals(StatusVenda.EMITIDA)) {
			for (ItemVenda item : vendaCanceladaEvent.getVenda().getItens()) {
				Cerveja cerveja = cervejaRepository.findOne(item.getCerveja().getCodigo());
				cerveja.setQuantidadeEstoque(cerveja.getQuantidadeEstoque() + item.getQuantidade());
				cervejaRepository.save(cerveja);
			}
		}
	}

}
