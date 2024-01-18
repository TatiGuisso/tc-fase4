package com.grupo16.tcfase4.gateway.mongo.impl;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.grupo16.tcfase4.domain.Usuario;
import com.grupo16.tcfase4.exception.ErroAoAcessarBancoDadosException;
import com.grupo16.tcfase4.gateway.UsuarioRepositoryGateway;
import com.grupo16.tcfase4.gateway.mongo.document.UsuarioDocument;
import com.grupo16.tcfase4.gateway.mongo.repository.UsuarioRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class UsuarioRepositoryGatewayImpl implements UsuarioRepositoryGateway {

	private UsuarioRepository usuarioRepository;
	
	@Override
	public Optional<Usuario> obterPorId(String id) {
		try {
			Optional<Usuario> usuarioOptional = Optional.empty();
			Optional<UsuarioDocument> usuarioDocOp = usuarioRepository.findById(id);
			
			if(usuarioDocOp.isPresent()) {
				UsuarioDocument usuarioDocument = usuarioDocOp.get();
				Usuario usuario = usuarioDocument.mapperDocumentToDomain();
				usuarioOptional = Optional.of(usuario);
			}
			return usuarioOptional;
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ErroAoAcessarBancoDadosException();
		}
		
	}
	
	

}
