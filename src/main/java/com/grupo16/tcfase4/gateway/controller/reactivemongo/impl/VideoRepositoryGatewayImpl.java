package com.grupo16.tcfase4.gateway.controller.reactivemongo.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.grupo16.tcfase4.domain.Categoria;
import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.exception.ErroAoAcessarBancoDadosException;
import com.grupo16.tcfase4.gateway.controller.VideoRepositoryGateway;

import reactor.core.publisher.Mono;

@Component
public class VideoRepositoryGatewayImpl implements VideoRepositoryGateway {

	@Override
	public Mono<Video> salvar(Video video) {
		
		try {
			
			int a = 1/0;
			
			Video result = Video.builder()
					.titulo("Tests")
					.descricao("teste")
					.url("www.tests")
					.categoria(Categoria.valueOf("TERROR"))
					.dataPublicacao(LocalDate.now())
					.build(); 
			
			return Mono.just(result);
		} catch (Exception e) {
			throw new ErroAoAcessarBancoDadosException();
			//throw e;
		}
		
	}

}
