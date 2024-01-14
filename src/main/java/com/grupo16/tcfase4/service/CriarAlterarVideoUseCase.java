package com.grupo16.tcfase4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.controller.VideoRepositoryGateway;

@Service
public class CriarAlterarVideoUseCase {
	
	@Autowired
	private VideoRepositoryGateway videoRepositoryGateway;

	public String salvar(Video video) {
		
		/*
		 * TODO: implementar alguma regra antes de salvar????
		 * talvez verificar se o video ja existe para nao duplicar
		 * 
		 */
		
		return videoRepositoryGateway.salvar(video);
	}

	public void alterar(Video video) {
		// TODO Auto-generated method stub
		
	}

}
