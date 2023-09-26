package com.parkinglot.example.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public enum VehicleType {
    CAR("CAR"),
    BIKE("BIKE"),
    TRUCK("TRUCK");

    public String name;
}
