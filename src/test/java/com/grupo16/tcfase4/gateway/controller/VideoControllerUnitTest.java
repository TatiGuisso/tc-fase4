package com.grupo16.tcfase4.gateway.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
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
		String id = UUID.randomUUID().toString();
		Video video = Video.builder().id(id).build();
		
		VideoJson videoJsonMock = mock(VideoJson.class); 		
		when(videoJsonMock.mapperJsonToDomain(null)).thenReturn(video);

		when(criarAlterarVideoUseCase.salvar(any(Video.class))).thenReturn(video.getId());

		String result = videoController.salvar(videoJsonMock);
		
		verify(criarAlterarVideoUseCase, times(1)).salvar(any(Video.class));
		
		assertEquals(video.getId(), result);
		
	}
	
	@Test
	void deveAlterar() {
		String id = UUID.randomUUID().toString();
		Video video = Video.builder().id(id).build();
		
		VideoJson videoJsonMock = mock(VideoJson.class);
		when(videoJsonMock.mapperJsonToDomain(id)).thenReturn(video);
		
		videoController.alterar(id, videoJsonMock);
		
		verify(criarAlterarVideoUseCase, times(1)).alterar(video);
		
	}

}
