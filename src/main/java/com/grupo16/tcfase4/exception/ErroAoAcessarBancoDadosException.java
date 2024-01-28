package com.grupo16.tcfase4.exception;

import lombok.Getter;

@Getter
public class ErroAoAcessarBancoDadosException extends SystemBaseException {
	private static final long serialVersionUID = -415384055723475621L;
	
	private final String code = "tc.erroAoAcessarBancoDados";
	private final String message = "Erro ao acessar o banco de dados";
	private final Integer httpStatus = 500;
}
