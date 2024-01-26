package com.grupo16.tcfase4.gateway.mongo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.grupo16.tcfase4.gateway.mongo.document.FavoritoDocument;

@Repository
public interface FavoritoRepository extends MongoRepository<FavoritoDocument, String> {

	Optional<FavoritoDocument> findByUsuarioIdAndVideoId(String usuarioId, String videoId);

	List<FavoritoDocument> findByUsuarioId(String usuarioId);

	void deleteByVideoId(String videoId);

}
