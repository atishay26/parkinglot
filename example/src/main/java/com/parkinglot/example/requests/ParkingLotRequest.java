package com.parkinglot.example.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLotRequest {

    private Integer numFloors;
    private Integer slotsPerFloor;
}
