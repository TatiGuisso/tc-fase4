package com.grupo16.tcfase4.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.controller.VideoRepositoryGateway;
import com.grupo16.tcfase4.service.exception.VideoNaoEncontradoException;

@ExtendWith(MockitoExtension.class)
class ObterVideoUseCaseUniTest {
	
	@InjectMocks
	private ObterVideoUseCase obterVideoUseCase;
	
	@Mock
	private VideoRepositoryGateway videoRepositoryGateway;
	
	@Test
	void deveObterVideoPorId() {
		String id = UUID.randomUUID().toString();
		Video video = Video.builder().build();
		
		when(videoRepositoryGateway.obterPorId(id)).thenReturn(Optional.of(video));
		
		Video videoObtido = obterVideoUseCase.obterPorId(id);
		
		assertEquals(video, videoObtido);
		
	}
	
	@Test
	void deveRetornarExceptionAoObterVideoPorID() {
		String id = UUID.randomUUID().toString();
		
		Optional<Video> videoOp = Optional.empty();
		
		when(videoRepositoryGateway.obterPorId(id)).thenReturn(videoOp);
		
		assertThrows(VideoNaoEncontradoException.class, 
				() -> obterVideoUseCase.obterPorId(id));
		
		verify(videoRepositoryGateway).obterPorId(id);
	}

}
