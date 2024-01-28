package com.grupo16.tcfase4.service.exception;

import com.grupo16.tcfase4.exception.SystemBaseException;

import lombok.Getter;

@Getter
public class VideoNaoEncontradoException extends SystemBaseException {
	private static final long serialVersionUID = 5199898008763121907L;

	private final String code = "tc.videoNaoEncontrado";
	private final String message = "Video não encontrado.";
	private final Integer httpStatus = 404;
}
