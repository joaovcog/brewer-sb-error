package com.algaworks.brewer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.brewer.model.Usuario;
import com.algaworks.brewer.repository.helper.usuario.UsuarioRepositoryQueries;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQueries {

	public Optional<Usuario> findByEmailOrCodigo(String email, Long codigo);

	public List<Usuario> findByCodigoIn(Long[] codigos);
	
}
