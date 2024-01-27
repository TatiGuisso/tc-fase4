package com.grupo16.tcfase4.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ErroAoAcessarBancoDadosExceptionUnitTest {
	
	@Test
	void exceptionErroAoAcessarBancoDeDados() {
		ErroAoAcessarBancoDadosException exception = new ErroAoAcessarBancoDadosException();
		assertEquals("tc.erroAoAcessarBancoDados", exception.getCode());
		assertEquals("Erro ao acessar o banco de dados", exception.getMessage());
		assertEquals(500, exception.getHttpStatus());
	}

}
