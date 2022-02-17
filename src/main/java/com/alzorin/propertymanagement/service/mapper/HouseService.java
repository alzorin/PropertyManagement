package com.alzorin.propertymanagement.service.mapper;

import com.alzorin.propertymanagement.model.HouseEntity;
import com.alzorin.propertymanagement.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HouseService {
    private final PropertyRepository propertyRepository;

    public Iterable<HouseEntity> findAll() {
        return this.propertyRepository.findAll();
    }

    public HouseEntity createHouse(HouseEntity houseEntity) {
        return propertyRepository.save(houseEntity);
    }

    public Optional<HouseEntity> getHouse(int id) {
        return this.propertyRepository.findById(id);
    }
    public void deleteHouse(int id) {
        this.propertyRepository.deleteById(id);
    }
}
