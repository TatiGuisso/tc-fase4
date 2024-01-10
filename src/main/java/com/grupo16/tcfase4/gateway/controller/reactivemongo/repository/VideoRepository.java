package com.grupo16.tcfase4.gateway.controller.reactivemongo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.grupo16.tcfase4.gateway.controller.reactivemongo.document.VideoDocument;

@Repository
public interface VideoRepository extends ReactiveMongoRepository<VideoDocument, String>{

}
