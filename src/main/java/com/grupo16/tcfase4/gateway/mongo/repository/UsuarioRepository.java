package com.grupo16.tcfase4.gateway.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.grupo16.tcfase4.gateway.mongo.document.UsuarioDocument;

public interface UsuarioRepository extends MongoRepository<UsuarioDocument, String> {

}
