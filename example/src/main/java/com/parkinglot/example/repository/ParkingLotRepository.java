package com.parkinglot.example.repository;

import com.parkinglot.example.enums.VehicleType;
import com.parkinglot.example.models.ParkingLot;
import com.parkinglot.example.requests.ParkingLotRequest;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ParkingLotRepository {
    private static final String PARKING_LOT_ID = "PR123";
    private static final int NUM_TRUCK_SLOTS = 1;
    private static final int NUM_BIKE_SLOTS = 2;
    private static Map<String, ParkingLot> parkingLotMap = new HashMap<>();

    public String createParkingLot(ParkingLotRequest request) {
        int slotsPerFloor = request.getSlotsPerFloor();
        ParkingLot parkingLot = ParkingLot.builder()
                .parkingLotId(PARKING_LOT_ID)
                .numFloors(request.getNumFloors())
                .truckAvailability(NUM_TRUCK_SLOTS)
                .bikeAvailability(Math.max(slotsPerFloor - NUM_TRUCK_SLOTS, 0))
                .carAvailability(Math.max(slotsPerFloor - (NUM_TRUCK_SLOTS + NUM_BIKE_SLOTS), 0))
                .totalCapacity(request.getNumFloors() * request.getSlotsPerFloor())
                .build();
        parkingLotMap.put(parkingLot.getParkingLotId(), parkingLot);
        return parkingLot.getParkingLotId();
    }

    public void parkVehicle(String parkingLotId, VehicleType type) {
        ParkingLot parkingLot = parkingLotMap.get(parkingLotId);
        switch (type)
        {
            case CAR:
                parkingLot.setCarAvailability(parkingLot.getCarAvailability()-1);
                break;
            case BIKE:
                parkingLot.setBikeAvailability(parkingLot.getBikeAvailability()-1);
                break;
            case TRUCK:
                parkingLot.setTruckAvailability(parkingLot.getTruckAvailability()-1);
                break;
        }
        parkingLotMap.put(parkingLotId,parkingLot);
    }

    public void unParkVehicle(String parkingLotId, VehicleType type) {
        ParkingLot parkingLot = parkingLotMap.get(parkingLotId);
        switch (type)
        {
            case CAR:
                parkingLot.setCarAvailability(parkingLot.getCarAvailability()+1);
                break;
            case BIKE:
                parkingLot.setBikeAvailability(parkingLot.getBikeAvailability()+1);
                break;
            case TRUCK:
                parkingLot.setTruckAvailability(parkingLot.getTruckAvailability()+1);
                break;
        }
        parkingLotMap.put(parkingLotId,parkingLot);
    }
}
