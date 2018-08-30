package com.algaworks.brewer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.brewer.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
	
	public List<Estado> findAllByOrderByNomeAsc();
	
}