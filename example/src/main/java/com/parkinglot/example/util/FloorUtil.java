package com.parkinglot.example.util;

import com.parkinglot.example.enums.VehicleType;
import com.parkinglot.example.models.Floor;

public class FloorUtil {

    public static void printFreeCountForVehicle(Floor floor, VehicleType type) {
        switch (type)
        {
            case BIKE:
                System.out.println(String.format("No. of free slots for BIKE on Floor %s: %s",
                        floor.getFloorNum(),floor.getBikeAvailability()));
                return;
            case CAR:
                System.out.println(String.format("No. of free slots for CAR on Floor %s: %s",
                        floor.getFloorNum(),floor.getCarAvailability()));
                return;
            case TRUCK:
                System.out.println(String.format("No. of free slots for TRUCK on Floor %s: %s",
                        floor.getFloorNum(),floor.getTruckAvailability()));
                return;
        }
    }

    public static boolean getVehicleAvailability(VehicleType type, Floor floor) {
        switch (type) {
            case BIKE:
                return floor.getBikeAvailability() > 0;
            case TRUCK:
                return floor.getTruckAvailability() > 0;
            case CAR:
                return floor.getCarAvailability() > 0;
        }

        return false;
    }
}
