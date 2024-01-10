package com.grupo16.tcfase4.gateway.controller.reactivemongo.impl;

import org.springframework.stereotype.Component;

import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.controller.VideoRepositoryGateway;

import reactor.core.publisher.Mono;

@Component
public class VideoRepositoryGatewayImpl implements VideoRepositoryGateway {

	@Override
	public Mono<Video> salvar(Video video) {
		
		try {
			
			return null;
		} catch (Exception e) {
			throw e;
		}
		
	}

}
