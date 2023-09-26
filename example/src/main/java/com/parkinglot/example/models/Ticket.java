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
public class Ticket {

    private String id;
    private String regNum;
    private Long timeIn;
    private String parkingLotId;
    private Integer floorId;
    private Integer slotId;
    private VehicleType type;
    private String colour;
}
