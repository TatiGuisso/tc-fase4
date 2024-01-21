package com.grupo16.tcfase4.gateway.mongo.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.grupo16.tcfase4.service.exception.VideoNaoEncontradoException;

class FileRepositoryGatewayImplIntTest {
	
	@Test
	void deveFazerUpload() {
		FileRepositoryGatewayImpl fileRepository = new FileRepositoryGatewayImpl();
		
		fileRepository.upload("fdsfsdffdf.avi", "adgagagads".getBytes());
	}

	@Test
	void deveFalharAoFazerUpload() {
		FileRepositoryGatewayImpl fileRepository = new FileRepositoryGatewayImpl();
		
		assertThrows(VideoNaoEncontradoException.class, 
				() -> fileRepository.upload("fdsfsdffdf.avi", null));
	}

}
