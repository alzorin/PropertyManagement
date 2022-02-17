package com.alzorin.propertymanagement.controller;

import com.alzorin.propertymanagement.model.HouseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PropertyControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PropertyController propertyController;

    private HouseDto houseDto;

    @BeforeEach
    void tearUp() {

        houseDto = houseDto.builder()
                .id(10)
                .name("Testhaus")
                .build();
    }
    @Test
    public void testCreateHouseAndGetFromServer_thenOK() throws Exception {

        ResponseEntity<HouseDto> dto = this.propertyController.createHouse(houseDto);

        this.mockMvc
                .perform(get("/houses/" + Objects.requireNonNull(dto.getBody()).getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(houseDto.getId()))
                .andExpect(jsonPath("$.name").value(houseDto.getName()));
    }
}
