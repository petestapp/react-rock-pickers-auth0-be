package com.example.reactrockpickersauth0be.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityConfigTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void corsConfigurationOnGetRequest() throws Exception {
        // arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .options("/getAllRocks")
                .header("Origin", "http://localhost:3000");

        // act
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        // assert
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.header().string("Access-Control-Allow-Origin", "http://localhost:3000"))
                .andExpect(MockMvcResultMatchers.header().string("Allow", "GET,HEAD,OPTIONS"));
        // need to add a line that tests whether "corsConfiguration.addAllowedHeader("*");" works but it seems inaccessible at this point from postman testing
//                .andExpect(MockMvcResultMatchers.header().string("Access-Control-Allow-Header", "*"));
    }
}