package com.grupo16.tcfase4.gateway.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.grupo16.tcfase4.gateway.mongo.document.VideoDocument;

@Repository
public interface VideoRepository extends MongoRepository<VideoDocument, String>{

}
