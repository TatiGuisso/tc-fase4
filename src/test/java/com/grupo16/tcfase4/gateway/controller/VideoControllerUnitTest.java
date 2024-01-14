package com.grupo16.tcfase4.gateway.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.controller.json.VideoJson;
import com.grupo16.tcfase4.service.CriarAlterarVideoUseCase;

@ExtendWith(MockitoExtension.class)
class VideoControllerUnitTest {
	
	@InjectMocks
	private VideoController videoController;
	
	@Mock
	private CriarAlterarVideoUseCase criarAlterarVideoUseCase;
	
	@Test
	void deveSalvar() {
		Video video = Video.builder().id(UUID.randomUUID().toString()).build();
		
		VideoJson videoJsonMock = mock(VideoJson.class); 		
		when(videoJsonMock.mapperJsonToDomain(null)).thenReturn(video);

		when(criarAlterarVideoUseCase.salvar(video)).thenReturn(video.getId());

		String result = videoController.salvar(videoJsonMock);
		
		verify(criarAlterarVideoUseCase).salvar(video);
		assertEquals(video.getId(), result);
		
	}
	
	@Test
	void deveAlterar() {
		Video video = Video.builder().id(UUID.randomUUID().toString()).build();
		
		VideoJson videoJsonMock = mock(VideoJson.class);
		when(videoJsonMock.mapperJsonToDomain(video.getId())).thenReturn(video);
		
		videoController.alterar(video.getId(), videoJsonMock);
		
		verify(criarAlterarVideoUseCase).alterar(video);
		
	}

}
