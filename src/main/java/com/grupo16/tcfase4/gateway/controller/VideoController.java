package com.grupo16.tcfase4.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.grupo16.tcfase4.gateway.controller.json.VideoJson;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RequestMapping("videos")
@RestController
public class VideoController {
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Mono<VideoJson> create(
			@Valid
			@RequestBody(required = true) VideoJson videoJson){
		log.trace("Start videoJson={}", videoJson);
		
		
		
		log.trace("End");
		return null;
	}

}
