package com.grupo16.tcfase4.gateway.mongo.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.exception.ErroAoAcessarBancoDadosException;
import com.grupo16.tcfase4.gateway.VideoRepositoryGateway;
import com.grupo16.tcfase4.gateway.mongo.document.VideoDocument;
import com.grupo16.tcfase4.gateway.mongo.repository.VideoRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class VideoRepositoryGatewayImpl implements VideoRepositoryGateway {
	
	private VideoRepository videoRepository;

	@Override
	public Page<Video> listarTodos(Pageable pageable) {
		try {
			return videoRepository.findAllByOrderByDataPublicacaoDesc(pageable).map(VideoDocument::mapperDocumentToDomain);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ErroAoAcessarBancoDadosException();
		}
	}

	@Override
	public String salvar(Video video) {
		try {
			VideoDocument videoDocument = new VideoDocument(video);
			
			return videoRepository.save(videoDocument).getId(); 
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ErroAoAcessarBancoDadosException();
		}
	}

	@Override
	public Optional<Video> obterPorId(String id) {
		try {
			Optional<Video> videoOptional = Optional.empty(); 
			Optional<VideoDocument> videoDocumentOp = videoRepository.findById(id);
			
			if(videoDocumentOp.isPresent()) {
				VideoDocument videoDocument = videoDocumentOp.get();
				Video video = videoDocument.mapperDocumentToDomain();
				videoOptional = Optional.of(video);
			}
			
			return videoOptional;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ErroAoAcessarBancoDadosException();
		}
	}

	@Override
	public void remover(String id) {
		try {
			videoRepository.deleteById(id);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ErroAoAcessarBancoDadosException();
		}
	}
}
