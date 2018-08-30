package com.algaworks.brewer.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.brewer.model.Estilo;
import com.algaworks.brewer.repository.EstiloRepository;
import com.algaworks.brewer.service.exception.ImpossivelExcluirEntidadeException;
import com.algaworks.brewer.service.exception.NomeEstiloJaCadastradoException;

@Service
public class CadastroEstiloService {

	@Autowired
	private EstiloRepository estiloRepository;

	@Transactional
	public Estilo salvar(Estilo estilo) throws NomeEstiloJaCadastradoException {
		if (estilo.getCodigo() == null || nomeDoEstiloMudou(estilo)) {
			verificarSeNomeJaExiste(estilo.getNome());
		}

		return estiloRepository.saveAndFlush(estilo);
	}

	@Transactional
	public void excluir(Estilo estilo) {
		try {
			estiloRepository.delete(estilo);
			estiloRepository.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar o estilo, pois foi vinculado a alguma cerveja.");
		}
	}
	
	private void verificarSeNomeJaExiste(String nome) {
		if (estiloRepository.findByNomeIgnoreCase(nome).isPresent()) {
			throw new NomeEstiloJaCadastradoException("Nome do estilo já cadastrado.");
		}
	}
	
	private boolean nomeDoEstiloMudou(Estilo estilo) {
		String novoNome = estilo.getNome();
		String nomeAnterior = estiloRepository.findOne(estilo.getCodigo()).getNome();
		
		return !nomeAnterior.equalsIgnoreCase(novoNome);
	}

}
