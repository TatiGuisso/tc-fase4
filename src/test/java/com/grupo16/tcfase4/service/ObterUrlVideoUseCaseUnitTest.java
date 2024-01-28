package com.grupo16.tcfase4.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.FileRepositoryGateway;
import com.grupo16.tcfase4.gateway.VideoRepositoryGateway;

@ExtendWith(MockitoExtension.class)
class ObterUrlVideoUseCaseUnitTest {
	
	@InjectMocks
	private ObterUrlVideoUseCase obterUrlVideoUseCase;
	
	@Mock
	private ObterVideoUseCase obterVideoUseCase;
	
	@Mock
	private VideoRepositoryGateway videoRepositoryGateway;
	
	@Mock
	private FileRepositoryGateway fileRepositoryGateway;
	
	@Test
	void deveObterUrl() {
		String videoId = UUID.randomUUID().toString();
		Video video = Video.builder().id(videoId).quantidadeVisualizacao(0L).build();
		String url = "www.video.com.br";
		
		when(obterVideoUseCase.obterPorId(videoId)).thenReturn(video);
		
		ArgumentCaptor<Video> videoCaptor = ArgumentCaptor.forClass(Video.class);
		
		when(fileRepositoryGateway.obterUrl(videoId)).thenReturn(url);
		
		String result = obterUrlVideoUseCase.obterUrl(videoId);
		
		verify(obterVideoUseCase).obterPorId(videoId);

		verify(videoRepositoryGateway).salvar(videoCaptor.capture());
		Video videoSalvo = videoCaptor.getValue();
		
		verify(fileRepositoryGateway).obterUrl(videoId);
		
		assertEquals(url, result);
		assertEquals(1L, videoSalvo.getQuantidadeVisualizacao());
		assertEquals(videoId, videoSalvo.getId());
	}

}
