package com.algaworks.brewer.repository.helper.cerveja;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.brewer.dto.CervejaDTO;
import com.algaworks.brewer.dto.EstoqueDTO;
import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.repository.filter.CervejaFilter;

public interface CervejaRepositoryQueries {
	
	public Page<Cerveja> filtrar(CervejaFilter filtro, Pageable pageable);
	
	public List<CervejaDTO> porSkuNome(String skuNome);
	
	public EstoqueDTO valorQuantidadeEstoque();
	
}