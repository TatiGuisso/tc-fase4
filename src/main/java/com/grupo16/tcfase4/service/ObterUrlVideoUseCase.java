package com.grupo16.tcfase4.service;

import org.springframework.stereotype.Service;

import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.FileRepositoryGateway;
import com.grupo16.tcfase4.gateway.VideoRepositoryGateway;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ObterUrlVideoUseCase {
	
	private ObterVideoUseCase obterVideoUseCase;
	
	private VideoRepositoryGateway videoRepositoryGateway;
	
	private FileRepositoryGateway fileRepositoryGateway;
	
	public String obterUrl(String videoId) {
		
		Video video = obterVideoUseCase.obterPorId(videoId);
		
		Video videoVisualizado = Video.builder()
				.id(video.getId())
				.titulo(video.getTitulo())
				.descricao(video.getDescricao())
				.dataPublicacao(video.getDataPublicacao())
				.quantidadeVisualizacao(video.getQuantidadeVisualizacao() + 1L)
				.categoria(video.getCategoria())
				.build();
		
		videoRepositoryGateway.salvar(videoVisualizado);
				
		return fileRepositoryGateway.obterUrl(videoId);
	}

}
