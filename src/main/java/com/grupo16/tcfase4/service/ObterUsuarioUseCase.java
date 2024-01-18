package com.grupo16.tcfase4.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.grupo16.tcfase4.domain.Usuario;
import com.grupo16.tcfase4.gateway.UsuarioRepositoryGateway;
import com.grupo16.tcfase4.service.exception.UsuarioNaoEncontradoException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ObterUsuarioUseCase {
	
	private UsuarioRepositoryGateway usuarioRepositoryGateway;

	public Usuario obterPorId(String usuarioId) {
		
		Optional<Usuario> usuarioOptional = usuarioRepositoryGateway.obterPorId(usuarioId);
		
		if(usuarioOptional.isEmpty()) {
			log.warn("Usuario não encontrado, id={}", usuarioId);
			throw new UsuarioNaoEncontradoException();
		}
		
		return usuarioOptional.get();
	}

}
