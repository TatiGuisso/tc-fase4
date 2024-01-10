package com.grupo16.tcfase4.gateway.controller;

import com.grupo16.tcfase4.domain.Video;

import reactor.core.publisher.Mono;

public interface VideoRepositoryGateway {

	Mono<Video> salvar(Video video);

}
