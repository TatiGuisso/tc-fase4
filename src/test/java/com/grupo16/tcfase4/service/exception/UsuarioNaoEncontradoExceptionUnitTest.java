package com.grupo16.tcfase4.service.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UsuarioNaoEncontradoExceptionUnitTest {
	
	@Test
	void exceptionUsuarioNaoEncontrado() {
		UsuarioNaoEncontradoException exception = new UsuarioNaoEncontradoException();
		assertEquals("tc.usuarioNaoEncontrado", exception.getCode());
		assertEquals("Usuario não encontrado.", exception.getMessage());
		assertEquals(404, exception.getHttpStatus());
		
	}

}
