package com.grupo16.tcfase4.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.controller.json.VideoJson;
import com.grupo16.tcfase4.service.CriarAlterarVideoUseCase;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("videos")
@RestController
public class VideoController {
	
	@Autowired
	private CriarAlterarVideoUseCase criarAlterarVideoUseCase;
	
//	@ResponseStatus(HttpStatus.CREATED)
//	@PostMapping
//	public Mono<VideoJson> create(
//			@Valid
//			@RequestBody(required = true) VideoJson videoJson){
//		log.trace("Start videoJson={}", videoJson);
//		
//		Video video = videoJson.mapperJsonToDomain();
//		Mono<Video> videoSalvo = criarAlterarVideoUseCase.salvar(video);
//		Mono<VideoJson> result = videoSalvo.map(VideoJson::new);
//		
//		log.trace("End result={}", result);
//		return result;
//	}
	
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

}
