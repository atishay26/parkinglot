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
public class Slot {

    private Integer slotId;
    private Integer floorId;
    private int slotNum;
    private VehicleType type;
    private boolean isAvailable;
}
