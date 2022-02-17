package com.alzorin.propertymanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class HouseEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
}
