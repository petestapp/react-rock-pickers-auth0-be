package com.example.reactrockpickersauth0be.rocks;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes= {RockService.class, RockController.class})
@AutoConfigureMockMvc
@EnableWebMvc
class RockControllerTest {
    private RockController rockController;
    @MockBean
    RockService rockService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        rockController = new RockController(rockService);
    }

    @Test
    public void rockControllerConstructor_ShouldCreateRockController() {
        // arrange
        RockController rockController = new RockController(rockService);

        // act and assert
        assertNotNull(rockController);
    }

    @Test
    public void getAllRocks_ShouldReturnAllRocks() throws Exception {
        // arrange
        String type1 = "Obsidian";
        Rock rock1 = new Rock(type1);

        String type2 = "Basalt";
        Rock rock2 = new Rock(type2);

        String type3 = "Quartz";
        Rock rock3 = new Rock(type3);

        List<Rock> rocks = new ArrayList<>();
        rocks.add(rock1);
        rocks.add(rock2);
        rocks.add(rock3);

        when(rockService.getAllRocks()).thenReturn(rocks);

        // act and assert
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/getAllRocks"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        String rocksAsString = mapper.writeValueAsString(rocks);
        String resultAsString = result.getResponse().getContentAsString();

        assertEquals(rocksAsString, resultAsString);
        verify(rockService, times(1)).getAllRocks();
    }
}