package com.grupo16.tcfase4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.controller.VideoRepositoryGateway;

@Service
public class CriarAlterarVideoUseCase {
	
	@Autowired
	private VideoRepositoryGateway videoRepositoryGateway;

	public Video salvar(Video video) {
		
		//TODO: verificar se vamos implementar alguma regra antes de salvar;
		return videoRepositoryGateway.salvar(video);
	}

}
