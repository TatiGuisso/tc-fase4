package com.grupo16.tcfase4.service;

import org.springframework.stereotype.Service;

import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.FileRepositoryGateway;
import com.grupo16.tcfase4.gateway.VideoRepositoryGateway;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CriarAlterarVideoUseCase {
	
	private VideoRepositoryGateway videoRepositoryGateway;
	
	private ObterVideoUseCase obterVideoUseCase;
	
	private FileRepositoryGateway fileRepositoryGateway;
	
	public String salvar(Video video) {
		return videoRepositoryGateway.salvar(video);
	}

	public void alterar(Video video) {
		Video videoAntigo = obterVideoUseCase.obterPorId(video.getId());
		
		Video videoNovo = Video.builder()
				.id(videoAntigo.getId())
				.titulo(video.getTitulo())
				.descricao(video.getDescricao())
				.categoria(video.getCategoria())
				.dataPublicacao(videoAntigo.getDataPublicacao())
				.build();
		
		videoRepositoryGateway.salvar(videoNovo);
	}

	public void upload(String videoId, byte[] file) {
		fileRepositoryGateway.upload(videoId, file);
	}
	
}
