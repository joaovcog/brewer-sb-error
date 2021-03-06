package com.algaworks.brewer.storage;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage {
	
	public static final String THUMBNAIL_PREFIX = "thumbnail.";
	public final String URL = "http://localhost:8080/fotos/";
	
	public String salvar(MultipartFile[] files);

	public byte[] recuperarFoto(String nome);

	public byte[] recuperarThumbnail(String fotoCerveja);

	public void excluir(String foto);

	public String getUrl(String foto);
	
	public default String renomearArquivo(String nomeOriginal) {
		return UUID.randomUUID().toString() + "_" + nomeOriginal;
	}

}