package com.parkinglot.example.service;

import com.parkinglot.example.enums.VehicleType;
import com.parkinglot.example.models.Floor;
import com.parkinglot.example.models.Vehicle;
import com.parkinglot.example.repository.FloorRepository;
import com.parkinglot.example.requests.ParkingLotRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.parkinglot.example.util.FloorUtil.getVehicleAvailability;
import static com.parkinglot.example.util.FloorUtil.printFreeCountForVehicle;

@Service
public class FloorService {

    @Autowired
    private FloorRepository floorRepository;

    public void printFreeCount(String parkingLotId, VehicleType vehicleType) {
        List<Floor> floors = floorRepository.findByParkingLotId(parkingLotId);
        floors.forEach(floor -> printFreeCountForVehicle(floor, vehicleType));
    }

    public List<Floor> findByParkingLotId(String parkingLotId) {
        return floorRepository.findByParkingLotId(parkingLotId);
    }

    public Set<Integer> addFloorsToParkingLot(String parkingLotId, ParkingLotRequest request) {
        return floorRepository.addFloorsToParkingLot(parkingLotId, request);
    }

    public Floor findAvailableByParkingLotIdAndVehicleType(String parkingLotId, VehicleType type) {
        return floorRepository.findByParkingLotId(parkingLotId)
                .stream()
                .filter(floor -> getVehicleAvailability(type,floor))
                .findFirst().get();
    }

    public void parkVehicle(Integer floorId, VehicleType type) {
        floorRepository.parkVehicle(floorId, type);
    }

    public void unParkVehicle(Integer floorId, VehicleType type) {
        floorRepository.unParkVehicle(floorId, type);
    }
}
