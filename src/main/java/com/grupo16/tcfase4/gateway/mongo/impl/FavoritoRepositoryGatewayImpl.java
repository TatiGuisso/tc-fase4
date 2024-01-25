package com.grupo16.tcfase4.gateway.mongo.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
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

	private final MongoTemplate mongoTemplate;


	@Override
	public String salvar(Favorito favorito) {
		try {
			FavoritoDocument favoritoDocument = new FavoritoDocument(favorito);
			return favoritoRepository.save(favoritoDocument).getId();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
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
			log.error(e.getMessage(),e);
			throw new ErroAoAcessarBancoDadosException();
		}
		
	}

	@Override
	public List<Favorito> obterPorUsuarioId(String usuarioId) {
		try {
			List<FavoritoDocument> favoritosDoc = favoritoRepository.findByUsuarioId(usuarioId);
			return favoritosDoc.stream().map(FavoritoDocument::mapperDocumentToDomain).toList(); 
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ErroAoAcessarBancoDadosException();
		}
	}

	@Override
	public List<Favorito> obterTodosReferenciandoVideoId() {
		try {
			TypedAggregation<FavoritoDocument> aggregation = Aggregation.newAggregation(
					FavoritoDocument.class,
					Aggregation.group("videoId"));

			AggregationResults<FavoritoDocument> results = mongoTemplate.aggregate(aggregation, FavoritoDocument.class);

			return results.getMappedResults().stream().map(FavoritoDocument::mapperDocumentToDomain).toList();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new ErroAoAcessarBancoDadosException();
		}
	}
}
