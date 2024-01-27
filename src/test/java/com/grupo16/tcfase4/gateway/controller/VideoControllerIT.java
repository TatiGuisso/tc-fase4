package com.grupo16.tcfase4.gateway.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo16.tcfase4.domain.Video;
import com.grupo16.tcfase4.gateway.controller.json.VideoJson;
import com.grupo16.tcfase4.service.CriarAlterarVideoUseCase;
import com.grupo16.tcfase4.service.CriarFavoritoUseCase;
import com.grupo16.tcfase4.service.ObterEstatisticaVideoUseCase;
import com.grupo16.tcfase4.service.ObterUrlVideoUseCase;
import com.grupo16.tcfase4.service.ObterVideoUseCase;
import com.grupo16.tcfase4.service.RecomendarVideoUseCase;
import com.grupo16.tcfase4.service.RemoverVideoUseCase;

@WebMvcTest(VideoController.class)
class VideoControllerIT {

    @Autowired
    private MockMvc mockMvc;
    
	private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private CriarAlterarVideoUseCase criarAlterarVideoUseCase; 
    
    @MockBean   
    private CriarFavoritoUseCase favoritoUseCase;

    @MockBean
	private RemoverVideoUseCase removerVideoUseCase;

    @MockBean
	private ObterVideoUseCase obterVideoUseCase;

    @MockBean
	private ObterUrlVideoUseCase obterUrlVideoUseCase;
	
    @MockBean
	private RecomendarVideoUseCase recomendarVideoUseCase;

    @MockBean
	private ObterEstatisticaVideoUseCase obterEstatisticaVideoUseCase;
    
	private VideoJson videoJson = VideoJson.builder()
	    		.id(UUID.randomUUID().toString())
	    		.titulo("Teste")
	    		.categoria("ACAO")
	    		.build();
    
    @Test
    void deveSalvar()throws Exception{
    	when(criarAlterarVideoUseCase.salvar(any(Video.class))).thenReturn(videoJson.getId());
    	
    	mockMvc.perform(MockMvcRequestBuilders.post("/videos")
				.content(objectMapper.writeValueAsString(videoJson))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());
    	
    }

    @Test
    void deveObterUrl()throws Exception{
    	String videoId = videoJson.getId(); 
    	when(obterUrlVideoUseCase.obterUrl(anyString())).thenReturn("www.urlvideo.com");
    	
    	mockMvc.perform(MockMvcRequestBuilders.get("/videos/{id}/url", videoId))
    			.andExpect(status().isOk());
    	
    }


//    @Test
//    void deveListar() throws Exception {
//        mockMvc.perform(get("/videos")
//                        .param("pagina", "0")
//                        .param("tamanho", "10"))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
}