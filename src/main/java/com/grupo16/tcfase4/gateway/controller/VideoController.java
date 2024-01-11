package com.grupo16.tcfase4.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
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
	public VideoJson create(
			@Valid
			@RequestBody(required = true) VideoJson videoJson){
		log.trace("Start videoJson={}", videoJson);
		Video video = videoJson.mapperJsonToDomain();

		Video videoSalvo = criarAlterarVideoUseCase.salvar(video);
		
		VideoJson result = new VideoJson(videoSalvo);
		
		log.trace("End result={}", result);
		return result;
	}

}
