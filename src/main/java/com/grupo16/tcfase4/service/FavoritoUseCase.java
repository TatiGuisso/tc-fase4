package com.grupo16.tcfase4.service;

import org.springframework.stereotype.Service;

import com.grupo16.tcfase4.domain.Favorito;
import com.grupo16.tcfase4.domain.Usuario;
import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.FavoritoRepositoryGateway;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FavoritoUseCase {
	
	private ObterVideoUseCase obterVideoUseCase;

	private FavoritoRepositoryGateway favoritoRepositoryGateway;

	public String salvar(String videoId, String usuarioId) {
		
		//TODO implementar buscar usuario
		
		Video video = obterVideoUseCase.obterPorId(videoId);
		
		Favorito favorito = Favorito.builder()
				.video(video)
				.usuario(Usuario.builder().id(usuarioId).nome("Fulano").build())//FIXME trocar pelo usuario que será buscado no metodo anterior. 
				.build();
		
		return favoritoRepositoryGateway.salvar(favorito);
	}

}
