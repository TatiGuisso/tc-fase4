package com.grupo16.tcfase4.gateway.controller.reactivemongo.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.grupo16.tcfase4.domain.Categoria;
import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.exception.ErroAoAcessarBancoDadosException;
import com.grupo16.tcfase4.gateway.controller.VideoRepositoryGateway;

@Component
public class VideoRepositoryGatewayImpl implements VideoRepositoryGateway {

	@Override
	public Video salvar(Video video) {
		
		try {
			
//			int a = 1/0;
			
			return Video.builder()
					.titulo("Tests")
					.descricao("teste")
					.url("www.tests")
					.categoria(Categoria.valueOf("TERROR"))
					.dataPublicacao(LocalDate.now())
					.build(); 
			
			
		} catch (Exception e) {
			throw new ErroAoAcessarBancoDadosException();
		}
	}

}
