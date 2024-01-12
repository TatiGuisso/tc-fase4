package com.grupo16.tcfase4.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.controller.VideoRepositoryGateway;

@ExtendWith(MockitoExtension.class)
class CriarAlterarVideoUseCaseUnitTest {
	
	@InjectMocks
	private CriarAlterarVideoUseCase criarAlterarVideoUseCase;
	
	@Mock
	private VideoRepositoryGateway videoRepositoryGateway;
	
	@Test
	void deveSalvar() {
		String id = UUID.randomUUID().toString();
		LocalDate data = LocalDate.of(2022, 10, 8);
		Video video = Video.builder()
				.id(id)
				.titulo("TESTEAAA")
				.descricao("BlaBla")
				.dataPublicacao(data)
				.build();
		
		doReturn(video.getId()).when(videoRepositoryGateway).salvar(any(Video.class));
		
		String result = criarAlterarVideoUseCase.salvar(video);
		
		verify(videoRepositoryGateway, times(1)).salvar(any(Video.class));
		
		assertEquals(video.getId(), result);
		
	}

}
