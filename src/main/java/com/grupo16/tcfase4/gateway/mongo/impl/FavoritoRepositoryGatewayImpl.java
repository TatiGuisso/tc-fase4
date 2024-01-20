package com.grupo16.tcfase4.gateway.mongo.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.grupo16.tcfase4.domain.Favorito;
import com.grupo16.tcfase4.exception.ErroAoAcessarBancoDadosException;
import com.grupo16.tcfase4.gateway.FavoritoRepositoryGateway;
import com.grupo16.tcfase4.gateway.mongo.document.FavoritoDocument;
import com.grupo16.tcfase4.gateway.mongo.repository.FavoritoRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class FavoritoRepositoryGatewayImpl implements FavoritoRepositoryGateway {

	private FavoritoRepository favoritoRepository;
	
	@Override
	public String salvar(Favorito favorito) {
		try {
			FavoritoDocument favoritoDocument = new FavoritoDocument(favorito);
			return favoritoRepository.save(favoritoDocument).getId();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ErroAoAcessarBancoDadosException();
		}
		
	}

	@Override
	public Optional<Favorito> obterPorUsuarioIdEVideoId(String usuarioId, String videoId) {
		try {
			Optional<Favorito> favoritoOp = Optional.empty();
			Optional<FavoritoDocument> favoritoDocOp = favoritoRepository.findByUsuarioIdAndVideoId(usuarioId,videoId);
			
			if(favoritoDocOp.isPresent()) {
				FavoritoDocument favoritoDocument = favoritoDocOp.get();
				Favorito favorito = favoritoDocument.mapperDocumentToDomain();
				favoritoOp = Optional.of(favorito);
			}
			
			return favoritoOp;
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ErroAoAcessarBancoDadosException();
		}
		
	}

	@Override
	public List<Favorito> obterPorUsuarioId(String usuarioId) {
		// TODO Auto-generated method stub
		return null;
	}

}
