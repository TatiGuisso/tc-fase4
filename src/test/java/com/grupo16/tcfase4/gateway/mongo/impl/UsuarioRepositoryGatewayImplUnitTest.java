package com.grupo16.tcfase4.gateway.mongo.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.grupo16.tcfase4.domain.Usuario;
import com.grupo16.tcfase4.gateway.mongo.document.UsuarioDocument;
import com.grupo16.tcfase4.gateway.mongo.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class UsuarioRepositoryGatewayImplUnitTest {
	
	@InjectMocks
	private UsuarioRepositoryGatewayImpl usuarioRepositoryGatewayImpl;
	
	@Mock
	private UsuarioRepository usuarioRepository;
	
	@Test
	void deveObterUsuarioPorId() {
		Usuario usuario = Usuario.builder()
				.id(UUID.randomUUID().toString())
				.nome("User Teste")
				.build();
		
		UsuarioDocument usuarioDocMock = mock(UsuarioDocument.class);
		
		when(usuarioDocMock.mapperDocumentToDomain()).thenReturn(usuario);
		when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuarioDocMock));
		
		Optional<Usuario> usuarioOptional = usuarioRepositoryGatewayImpl.obterPorId(usuario.getId());
		
		Usuario result = usuarioOptional.get();
		
		assertEquals(usuario, result);
		assertEquals(usuario.getId(), result.getId());
		assertEquals(usuario.getNome(), result.getNome());

	}

}
