package com.alzorin.propertymanagement.controller;

import com.alzorin.propertymanagement.model.HouseDto;
import com.alzorin.propertymanagement.model.HouseEntity;
import com.alzorin.propertymanagement.repository.PropertyRepository;
import com.alzorin.propertymanagement.service.mapper.HouseMapper;
import com.alzorin.propertymanagement.service.mapper.HouseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class PropertyControllerTest {

    @Mock
    private HouseService service;

    @Mock
    private HouseMapper mapper;

    @Mock
    private PropertyRepository propertyRepository;

    @InjectMocks
    private PropertyController propertyController;

    private HouseDto houseDto;
    private HouseEntity houseEntity;

    @BeforeEach
    void setUp() {
        houseDto = HouseDto.builder()
            .id(1)
            .name("Testhaus")
            .build();


        houseEntity = HouseEntity.builder()
                .id(1)
                .name("Testhaus")
                .build();
    }

    @Test
    void testGetHouseWithValidId() {
        //ASSEMBLE
        when(mapper.mapEntityToDto(houseEntity)).thenReturn(houseDto);
        when(service.getHouse(1)).thenReturn(Optional.of(houseEntity));

        //ACT
        ResponseEntity<HouseDto> response = propertyController.findHouseById(1);

        //ASSERT
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(houseDto, response.getBody());
    }
    @Test
    void testGetHouseWithInvalidId() {
        //ASSEMBLE
        when(service.getHouse(0)).thenReturn(Optional.empty());

        //ACT
        ResponseEntity<HouseDto> response = propertyController.findHouseById(0);

        //ASSERT
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
    @Test
    void testPostHouseWithValidArguments() {

        //ASSEMBLE
        when(service.createHouse(houseEntity)).thenReturn(houseEntity);
        when(mapper.mapDtoToEntity(houseDto)).thenReturn(houseEntity);
        when(mapper.mapEntityToDto(houseEntity)).thenReturn(houseDto);

        //ACT
        ResponseEntity<HouseDto> response = propertyController.createHouse(houseDto);

        //ASSERT
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(houseDto, response.getBody());
    }
    @Test
    void testDeleteHouseWithValidArguments() {
        //ASSEMBLE
        when(service.getHouse(1)).thenReturn(Optional.of(houseEntity));

        //ACT
        ResponseEntity<HouseDto> response = propertyController.deleteHouse(1);

        //ASSERT
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}