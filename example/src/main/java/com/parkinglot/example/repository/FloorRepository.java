package com.parkinglot.example.repository;

import com.parkinglot.example.enums.VehicleType;
import com.parkinglot.example.models.Floor;
import com.parkinglot.example.requests.ParkingLotRequest;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class FloorRepository {

    private static final int NUM_TRUCK_SLOTS = 1;
    private static final int NUM_BIKE_SLOTS = 2;
    private static Map<Integer, Floor> floorMap = new HashMap<>();

    public Set<Integer> addFloorsToParkingLot(String parkingLotId, ParkingLotRequest request) {
        Set<Integer> floorIds = new HashSet<>();
        int numFloors = request.getNumFloors();
        int slotsPerFloor = request.getSlotsPerFloor();
        for (int floorNum = 1; floorNum<=numFloors; floorNum++)
        {
            Floor floor = Floor.builder()
                    .floorId(floorMap.size()+1)
                    .parkingLotId(parkingLotId)
                    .floorNum(floorNum)
                    .totalSlots(request.getSlotsPerFloor())
                    .truckAvailability(NUM_TRUCK_SLOTS)
                    .bikeAvailability(Math.max(slotsPerFloor - NUM_TRUCK_SLOTS, 0))
                    .carAvailability(Math.max(slotsPerFloor - (NUM_TRUCK_SLOTS + NUM_BIKE_SLOTS), 0))
                    .build();
            floorMap.put(floor.getFloorId(), floor);
            floorIds.add(floor.getFloorId());
        }

        return floorIds;
    }

    public List<Floor> findAll() {
        return floorMap.keySet()
                .stream()
                .map(key -> floorMap.get(key))
                .collect(Collectors.toList());
    }

    public List<Floor> findByParkingLotId(String parkingLotId) {
        return floorMap.keySet()
                .stream()
                .map(key -> floorMap.get(key))
                .filter(floor -> parkingLotId.equalsIgnoreCase(floor.getParkingLotId()))
                .collect(Collectors.toList());

    }

    public void parkVehicle(Integer floorId, VehicleType type) {
        Floor floor = floorMap.get(floorId);
        switch (type)
        {
            case CAR:
                floor.setCarAvailability(floor.getCarAvailability()-1);
                break;
            case BIKE:
                floor.setBikeAvailability(floor.getBikeAvailability()-1);
                break;
            case TRUCK:
                floor.setTruckAvailability(floor.getTruckAvailability()-1);
                break;
        }
        floorMap.put(floorId, floor);
    }

    public void unParkVehicle(Integer floorId, VehicleType type) {
        Floor floor = floorMap.get(floorId);
        switch (type)
        {
            case CAR:
                floor.setCarAvailability(floor.getCarAvailability()+1);
                break;
            case BIKE:
                floor.setBikeAvailability(floor.getBikeAvailability()+1);
                break;
            case TRUCK:
                floor.setTruckAvailability(floor.getTruckAvailability()+1);
                break;
        }
        floorMap.put(floorId, floor);
    }
}
