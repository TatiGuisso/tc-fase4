package com.grupo16.tcfase4.gateway.controller;

import com.grupo16.tcfase4.service.RemoverVideoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.controller.json.VideoJson;
import com.grupo16.tcfase4.service.CriarAlterarVideoUseCase;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("videos")
@RestController
@AllArgsConstructor
public class VideoController {
	
	private CriarAlterarVideoUseCase criarAlterarVideoUseCase;

	private RemoverVideoUseCase removerVideoUseCase;


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

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public void remover(
			@PathVariable(required = true, name = "id") String id) {
		log.trace("Start id={}", id);

		removerVideoUseCase.remover(id);
		log.trace("End");
	}
}
