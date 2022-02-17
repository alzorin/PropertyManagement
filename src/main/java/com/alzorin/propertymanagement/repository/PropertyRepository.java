package com.alzorin.propertymanagement.repository;

import com.alzorin.propertymanagement.model.HouseEntity;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends CrudRepository<HouseEntity, Integer> {
}
