package com.parkinglot.example.util;

import com.parkinglot.example.enums.VehicleType;

public class SlotUtil {
    public static VehicleType getSlotType(int slotNum) {
        switch (slotNum) {
            case 1 :
                return VehicleType.TRUCK;

            case 2:
            case 3:
                return VehicleType.BIKE;

            default:
                return VehicleType.CAR;
        }
    }
}
