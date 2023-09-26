package com.parkinglot.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLot {

    private String parkingLotId;
    private int numFloors;
    private int totalCapacity;
    private int truckAvailability;
    private int bikeAvailability;
    private int carAvailability;
}
