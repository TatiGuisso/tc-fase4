package com.grupo16.tcfase4.gateway.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.controller.json.VideoJson;
import com.grupo16.tcfase4.service.CriarAlterarVideoUseCase;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class VideoControllerUnitTest {
	
	@InjectMocks
	private VideoController videoController;
	
	@Mock
	private CriarAlterarVideoUseCase criarAlterarVideoUseCase;
	
	@Test
	void deveSalvar() {
		LocalDate data = LocalDate.of(2022, 10, 8);
		Video video = Video.builder()
				.titulo("TESTEAAA")
				.descricao("BlaBla")
				.dataPublicacao(data)
				.build();
		
		VideoJson videoJsonMock = Mockito.mock(VideoJson.class); 
		
		when(videoJsonMock.mapperJsonToDomain()).thenReturn(video);

		when(criarAlterarVideoUseCase.salvar(any(Video.class))).thenReturn(Mono.just(video));

		
		StepVerifier.create(videoController.create(videoJsonMock))
			.expectSubscription()
			.expectNextMatches(vj -> {
				assertEquals(video.getTitulo(), vj.getTitulo());
				assertEquals(video.getDescricao(), vj.getDescricao());
				assertEquals(video.getDataPublicacao(), vj.getDataPublicacao());
				return true;
			})
			.verifyComplete();
		
					
	}

}
