package com.algaworks.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.brewer.model.Cidade;
import com.algaworks.brewer.repository.CidadeRepository;
import com.algaworks.brewer.service.exception.NomeCidadeJaCadastradoException;
import com.algaworks.brewer.service.exception.NomeEstiloJaCadastradoException;

@Service
public class CadastroCidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Transactional
	public Cidade salvar(Cidade cidade) throws NomeEstiloJaCadastradoException {
		Optional<Cidade> cidadeOptional = cidadeRepository.findByNomeIgnoreCaseAndEstado(cidade.getNome(), cidade.getEstado());
		
		if (cidadeOptional.isPresent()) {
			throw new NomeCidadeJaCadastradoException("Nome da cidade já cadastrado.");
		}
		
		return cidadeRepository.saveAndFlush(cidade);
	}

}
