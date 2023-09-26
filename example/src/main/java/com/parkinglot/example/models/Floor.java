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
public class Floor {

    private int floorId;
    private String parkingLotId;
    private int floorNum;
    private int totalSlots;
    private int truckAvailability;
    private int bikeAvailability;
    private int carAvailability;
}
