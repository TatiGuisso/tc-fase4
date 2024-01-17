package com.grupo16.tcfase4.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.grupo16.tcfase4.domain.Favorito;
import com.grupo16.tcfase4.domain.Usuario;
import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.controller.json.VideoJson;
import com.grupo16.tcfase4.service.CriarAlterarVideoUseCase;
import com.grupo16.tcfase4.service.FavoritoUseCase;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("videos")
@RestController
@AllArgsConstructor
public class VideoController {
	
	private CriarAlterarVideoUseCase criarAlterarVideoUseCase;
	
	private FavoritoUseCase favoritoUseCase;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public String salvar(
			@Valid
			@RequestBody(required = true) VideoJson videoJson){
		log.trace("Start videoJson={}", videoJson);
		Video video = videoJson.mapperJsonToDomain(null);

		String videoId = criarAlterarVideoUseCase.salvar(video);
		
		log.trace("End videoId={}", videoId);
		return videoId;
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("{id}")
	public void alterar(
			@PathVariable(required = true, name = "id") String videoId,
			@RequestBody(required = true) VideoJson videoJson) {
		log.trace("Start videoId={}, videoJson={}", videoId, videoJson);
		
		Video video = videoJson.mapperJsonToDomain(videoId);
		
		criarAlterarVideoUseCase.alterar(video);
		log.trace("End");
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("{id}/{usuarioId}")
	public String favoritar(
			@PathVariable(required = true, name = "id") String videoId,
			@PathVariable(required = true, name = "usuarioId") String usuarioId) {
		log.trace("Start videoId={}, usuarioId={}", videoId, usuarioId);
		
//		Favorito favorito = Favorito.builder()
//				.video(Video.builder().id(videoId).build())
//				.usuario(Usuario.builder().id(usuarioId).build())
//				.build();
		
		String idFavorito = favoritoUseCase.salvar(videoId, usuarioId);
		
		log.trace("End idFavorito={}", idFavorito);
		return idFavorito;
	}

}
