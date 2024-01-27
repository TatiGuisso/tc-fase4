package com.grupo16.tcfase4.gateway.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest()
class VideoControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideoController videoController;


    @Test
    void deveListar() throws Exception {
        mockMvc.perform(get("/videos")
                        .param("pagina", "0")
                        .param("tamanho", "10"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}