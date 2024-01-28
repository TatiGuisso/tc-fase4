package com.grupo16.tcfase4.gateway.mongo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.grupo16.tcfase4.domain.Categoria;
import com.grupo16.tcfase4.gateway.mongo.document.VideoDocument;

@Repository
public interface VideoRepository extends MongoRepository<VideoDocument, String>{
    Page<VideoDocument> findAllByOrderByDataPublicacaoDesc(Pageable pageable);

	List<VideoDocument> findTop3ByCategoria(Categoria categoriaMaisFavorita);
}
