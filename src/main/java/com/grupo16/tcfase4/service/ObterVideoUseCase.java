package com.grupo16.tcfase4.service;

import java.util.Optional;

import com.grupo16.tcfase4.gateway.controller.json.VideoJson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.VideoRepositoryGateway;
import com.grupo16.tcfase4.service.exception.VideoNaoEncontradoException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ObterVideoUseCase {

	private VideoRepositoryGateway videoRepositoryGateway;
	

	public Page<VideoJson> listarTodos(PageRequest pagina, Boolean dataPublicacao) {
		return videoRepositoryGateway.listarTodos(pagina, dataPublicacao).map(VideoJson::new);
	}

	public Video obterPorId(String id) {
		Optional<Video> videoOp = videoRepositoryGateway.obterPorId(id);

		if(videoOp.isEmpty()) {
			log.warn("Video não encontrado: id={}", id);
			throw new VideoNaoEncontradoException();
		}
		
		return videoOp.get();
	}
}
