package com.grupo16.tcfase4.service.exception;

import com.grupo16.tcfase4.exception.SystemBaseException;

import lombok.Getter;

@Getter
public class UsuarioNaoEncontradoException extends SystemBaseException {
	private static final long serialVersionUID = -7441701365055042606L;

	private final String code = "tc.usuarioNaoEncontrado";
	private final String message = "Usuario não encontrado.";
	private final Integer httpStatus = 404;
}
