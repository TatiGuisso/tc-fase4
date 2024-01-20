package com.grupo16.tcfase4.service;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.grupo16.tcfase4.domain.Categoria;
import com.grupo16.tcfase4.domain.Favorito;
import com.grupo16.tcfase4.domain.Usuario;
import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.FavoritoRepositoryGateway;

@ExtendWith(MockitoExtension.class)
public class RecomendarVideoUseCaseUnitTest {
	
	@InjectMocks
	private RecomendarVideoUseCase recomendarVideoUseCase;
	
	@Mock
	private FavoritoRepositoryGateway favoritoRepositoryGateway;
	
	@Mock
	private ObterVideoUseCase obterVideoUseCase;
	
	@Test
	void deveRecomendarVideo() {
		String id = UUID.randomUUID().toString();
		Video video1 = Video.builder().id(UUID.randomUUID().toString()).categoria(Categoria.ACAO).build();
		Video video2 = Video.builder().id(UUID.randomUUID().toString()).categoria(Categoria.FICCAO).build();
		Video video3 = Video.builder().id(UUID.randomUUID().toString()).categoria(Categoria.COMEDIA).build();
		Video video4 = Video.builder().id(UUID.randomUUID().toString()).categoria(Categoria.ACAO).build();
		
		List<Favorito> favoritos = Arrays.asList(
				Favorito.builder().video(video1).build(),
				Favorito.builder().video(video2).build(),
				Favorito.builder().video(video3).build(),
				Favorito.builder().video(video4).build());
		
		when(favoritoRepositoryGateway.obterPorUsuarioId(id)).thenReturn(favoritos);
		for (Favorito favorito : favoritos) {
			when(obterVideoUseCase.obterPorId(favorito.getVideo().getId()))
				.thenReturn(favorito.getVideo());
		}
		
		recomendarVideoUseCase.recomendar(id);
	}

}
