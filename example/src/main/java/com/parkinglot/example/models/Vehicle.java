package com.parkinglot.example.models;

import com.parkinglot.example.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    private String regNum;
    private VehicleType type;
    private String colour;
}
