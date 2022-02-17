package com.alzorin.propertymanagement.controller;

import com.alzorin.propertymanagement.model.HouseDto;
import com.alzorin.propertymanagement.model.HouseEntity;
import com.alzorin.propertymanagement.repository.PropertyRepository;
import com.alzorin.propertymanagement.service.mapper.HouseMapper;
import com.alzorin.propertymanagement.service.mapper.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/houses")
public class PropertyController {

    private final HouseService houseService;
    private final HouseMapper houseMapper;

    public PropertyController(HouseService houseService, HouseMapper houseMapper) {
        this.houseService = houseService;
        this.houseMapper = houseMapper;
    }

    @GetMapping
    public ResponseEntity<List<HouseDto>> findAll()
    {
        return new ResponseEntity<List<HouseDto>>(this.houseMapper.mapEntitiesToDtos(this.houseService.findAll()), HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<HouseDto> findHouseById(@PathVariable("id") int id) {

        Optional<HouseEntity> entityOptional = this.houseService.getHouse(id);

        if (entityOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(this.houseMapper.mapEntityToDto(entityOptional.get()), HttpStatus.OK);
    }
    @GetMapping("/{house1}/{house2}")
    public ResponseEntity<HouseDto> compareHousesById(@PathVariable("house1") int house1, @PathVariable("house2") int house2 ) {

        Optional<HouseEntity> house1EntityOptional = this.houseService.getHouse(house1);
        Optional<HouseEntity> house2EntityOptional = this.houseService.getHouse(house2);
        HouseEntity responseEntity = new HouseEntity();


        if (house1EntityOptional.isEmpty() && house2EntityOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!house1EntityOptional.isEmpty() && !house2EntityOptional.isEmpty()) {
            HouseEntity house1Entity = house1EntityOptional.get();
            HouseEntity house2Entity = house2EntityOptional.get();
            if (house1Entity.getId().compareTo(house2Entity.getId()) < 1) {
                responseEntity = house1Entity;
            } else {
                responseEntity = house2Entity;
            }
        }
//            if (house1Entity.getId() <= house2Entity.getId()) {
//                responseEntity = house2Entity;
//            } else {
//                responseEntity = house1Entity;
//            }
//        }
            if(house1EntityOptional.isEmpty())
            {
                responseEntity = house2EntityOptional.get();
            }
            if(house2EntityOptional.isEmpty())
            {
                responseEntity = house1EntityOptional.get();
            }
        return new ResponseEntity<>(this.houseMapper.mapEntityToDto(responseEntity), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<HouseDto> createHouse(@Valid @RequestBody HouseDto houseDto) {
        HouseEntity houseEntity = this.houseService.createHouse(this.houseMapper.mapDtoToEntity(houseDto));
        return new ResponseEntity<>(this.houseMapper.mapEntityToDto(houseEntity), HttpStatus.OK);
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity<HouseDto> deleteHouse(@PathVariable("id") int id) {

        Optional<HouseEntity> entityOptional = this.houseService.getHouse(id);

        if (entityOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            this.houseService.deleteHouse(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

}
