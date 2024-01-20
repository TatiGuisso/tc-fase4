package com.grupo16.tcfase4.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.grupo16.tcfase4.domain.Usuario;
import com.grupo16.tcfase4.gateway.UsuarioRepositoryGateway;
import com.grupo16.tcfase4.service.exception.UsuarioNaoEncontradoException;

@ExtendWith(MockitoExtension.class)
class ObterUsuarioUseCaseUnitTest {
	
	@InjectMocks
	private ObterUsuarioUseCase obterUsuarioUseCase;
	
	@Mock
	private UsuarioRepositoryGateway usuarioRepositoryGateway;
	
	@Test
	void deveObterPorId() {
		Usuario usuario = Usuario.builder().id(UUID.randomUUID().toString()).build();
		
		when(usuarioRepositoryGateway.obterPorId(usuario.getId())).thenReturn(Optional.of(usuario));
		
		Usuario result = obterUsuarioUseCase.obterPorId(usuario.getId());
		
		verify(usuarioRepositoryGateway).obterPorId(usuario.getId());
		
		assertEquals(usuario, result);
	}
	
	@Test
	void deveFalharAoObterPorId() {
		String id = UUID.randomUUID().toString();
		
		when(usuarioRepositoryGateway.obterPorId(id)).thenReturn(Optional.empty());
		
		assertThrows(UsuarioNaoEncontradoException.class, 
				() -> obterUsuarioUseCase.obterPorId(id));
		
		verify(usuarioRepositoryGateway).obterPorId(id);
		
	}

}
