package com.grupo16.tcfase4.service.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class VideoNaoEncontradoExceptionUnitTest {
	
	@Test
	void exceptionVideoNaoEncontrado() {
		VideoNaoEncontradoException exception = new VideoNaoEncontradoException();
		assertEquals("tc.videoNaoEncontrado", exception.getCode());
		assertEquals("Video não encontrado.", exception.getMessage());
		assertEquals(404, exception.getHttpStatus());
	}

}
