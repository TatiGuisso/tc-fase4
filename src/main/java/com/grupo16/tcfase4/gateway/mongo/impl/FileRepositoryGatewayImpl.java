package com.grupo16.tcfase4.gateway.mongo.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.grupo16.tcfase4.gateway.FileRepositoryGateway;
import com.grupo16.tcfase4.service.exception.VideoNaoEncontradoException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FileRepositoryGatewayImpl implements FileRepositoryGateway {

	@Value("${localPath}")
	private String localPath;
	
	@Override
	public void upload(String videoId, byte[] file) {
		
		try {
			Path path = Paths.get(localPath + videoId);
			
			Files.write(path, file);
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new VideoNaoEncontradoException();
		}
		
	}

}
