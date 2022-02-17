package com.alzorin.propertymanagement.service.mapper;

import com.alzorin.propertymanagement.model.HouseDto;
import com.alzorin.propertymanagement.model.HouseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class HouseMapperImpl implements HouseMapper {

    private final ModelMapper modelMapper;

    @Override
    public HouseEntity mapDtoToEntity(final HouseDto houseDto) {
        return modelMapper.map(houseDto, HouseEntity.class);
    }

    @Override
    public HouseDto mapEntityToDto(HouseEntity houseEntity) {
        return this.modelMapper.map(houseEntity, HouseDto.class);
    }

    public List<HouseDto> mapEntitiesToDtos (Iterable<HouseEntity> houseEntities)
    {
        return  StreamSupport.stream(houseEntities.spliterator(), false)
                .map(entity -> mapEntityToDto(entity))
                .collect(Collectors.toList());
    }
}


