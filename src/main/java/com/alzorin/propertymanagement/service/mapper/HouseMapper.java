package com.alzorin.propertymanagement.service.mapper;

import com.alzorin.propertymanagement.model.HouseDto;
import com.alzorin.propertymanagement.model.HouseEntity;

import java.util.List;
import java.util.stream.StreamSupport;

public interface HouseMapper {
    HouseEntity mapDtoToEntity(HouseDto houseDto);

    HouseDto mapEntityToDto(HouseEntity houseEntity);

    List<HouseDto> mapEntitiesToDtos(Iterable<HouseEntity> houseEntities);
}

