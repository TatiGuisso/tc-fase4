package com.grupo16.tcfase4.service.exception;

import com.grupo16.tcfase4.exception.SystemBaseException;

import lombok.Getter;

@Getter
public class VideoFavoritadoMaisDeUmaVezPeloUsuarioException extends SystemBaseException {
	private static final long serialVersionUID = -7776090352956516273L;
	
	private final String code = "tc.videoFavoritadoMaisDeUmaVezPeloUsuario";
	private final String message = "Video não pode ser favoritado mais de uma vez pelo mesmo usuário.";
	private final Integer httpStatus = 422;
}
