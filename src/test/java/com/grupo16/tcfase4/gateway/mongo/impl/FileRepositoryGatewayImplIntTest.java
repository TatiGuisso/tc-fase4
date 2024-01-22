package com.grupo16.tcfase4.gateway.mongo.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.grupo16.tcfase4.service.exception.VideoNaoEncontradoException;
import static org.springframework.test.util.ReflectionTestUtils.setField;

class FileRepositoryGatewayImplIntTest {
	
	@Test
	void deveFazerUpload() {

		FileRepositoryGatewayImpl fileRepository = new FileRepositoryGatewayImpl();
		
		setField(fileRepository, "localPath", "/home/tati/Documentos/develop/curso/FIAP/tc-4/videos/");
		
		fileRepository.upload("testsese.avi", "adgagagads".getBytes());
	}

	@Test
	void deveFalharAoFazerUpload() {
		FileRepositoryGatewayImpl fileRepository = new FileRepositoryGatewayImpl();
		
		assertThrows(VideoNaoEncontradoException.class, 
				() -> fileRepository.upload("fdsfsdffdf.avi", null));
	}

}
