package com.grupo16.tcfase4.service.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class VideoFavoritadoMaisDeUmaVezPeloUsuarioExceptionUnitTest {
	
	@Test
	void exceptionVideoFavoritadoMaisDe1Vez() {
		VideoFavoritadoMaisDeUmaVezPeloUsuarioException exception = new VideoFavoritadoMaisDeUmaVezPeloUsuarioException();
		assertEquals("tc.videoFavoritadoMaisDeUmaVezPeloUsuario", exception.getCode());
		assertEquals("Video não pode ser favoritado mais de uma vez pelo mesmo usuário.", exception.getMessage());
		assertEquals(422, exception.getHttpStatus());
	}

}
