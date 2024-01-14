package com.grupo16.tcfase4.gateway.mongo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.exception.ErroAoAcessarBancoDadosException;
import com.grupo16.tcfase4.gateway.controller.VideoRepositoryGateway;
import com.grupo16.tcfase4.gateway.mongo.document.VideoDocument;
import com.grupo16.tcfase4.gateway.mongo.repository.VideoRepository;

@Component
public class VideoRepositoryGatewayImpl implements VideoRepositoryGateway {
	
	@Autowired
	private VideoRepository videoRepository;

	@Override
	public String salvar(Video video) {
		try {
			VideoDocument videoDocument = new VideoDocument(video);
			
			return videoRepository.save(videoDocument).getId(); 
			
		} catch (Exception e) {
			throw new ErroAoAcessarBancoDadosException();
		}
	}

}
