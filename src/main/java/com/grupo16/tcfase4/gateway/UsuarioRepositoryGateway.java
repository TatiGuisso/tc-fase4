package com.grupo16.tcfase4.gateway;

import java.util.Optional;

import com.grupo16.tcfase4.domain.Usuario;

public interface UsuarioRepositoryGateway {
	
	Optional<Usuario> obterPorId(String id);

}
