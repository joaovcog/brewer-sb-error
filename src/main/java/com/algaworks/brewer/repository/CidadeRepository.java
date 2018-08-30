package com.algaworks.brewer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.brewer.model.Cidade;
import com.algaworks.brewer.model.Estado;
import com.algaworks.brewer.repository.helper.cidade.CidadeRepositoryQueries;

public interface CidadeRepository extends JpaRepository<Cidade, Long>, CidadeRepositoryQueries {
	
	public List<Cidade> findByEstadoCodigo(Long codigoEstado);
	
	public Optional<Cidade> findByNomeIgnoreCaseAndEstado(String nome, Estado estado);
	
}
