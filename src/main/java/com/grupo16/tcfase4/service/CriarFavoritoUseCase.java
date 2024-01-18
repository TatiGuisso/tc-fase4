package com.grupo16.tcfase4.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.grupo16.tcfase4.domain.Favorito;
import com.grupo16.tcfase4.domain.Usuario;
import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.FavoritoRepositoryGateway;
import com.grupo16.tcfase4.service.exception.VideoFavoritadoMaisDeUmaVezPeloUsuarioException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class CriarFavoritoUseCase {
	
	private ObterVideoUseCase obterVideoUseCase;
	
	private ObterUsuarioUseCase obterUsuarioUseCase;

	private FavoritoRepositoryGateway favoritoRepositoryGateway;

	public String salvar(String videoId, String usuarioId) {
		Usuario usuario = obterUsuarioUseCase.obterPorId(usuarioId);
		Video video = obterVideoUseCase.obterPorId(videoId);
		
		Optional<Favorito> favoritoOptional = favoritoRepositoryGateway.obterPorUsuarioIdEVideoId(usuarioId,videoId);
		if(favoritoOptional.isPresent()) {
			log.warn("Video não pode ser favoritado mais de uma vez pelo mesmo usuário. usuarioId={}, videoId={}",
					usuarioId,videoId);
			throw new VideoFavoritadoMaisDeUmaVezPeloUsuarioException();
		}
		
		Favorito favorito = Favorito.builder()
				.video(video)
				.usuario(usuario) 
				.build();
		
		return favoritoRepositoryGateway.salvar(favorito);
	}

}
