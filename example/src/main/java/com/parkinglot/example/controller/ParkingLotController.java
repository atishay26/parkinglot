package com.parkinglot.example.controller;

import com.parkinglot.example.enums.VehicleType;
import com.parkinglot.example.exceptions.ValidationException;
import com.parkinglot.example.models.Floor;
import com.parkinglot.example.models.Vehicle;
import com.parkinglot.example.requests.ParkingLotRequest;
import com.parkinglot.example.service.FloorService;
import com.parkinglot.example.service.ParkingLotService;
import com.parkinglot.example.service.SlotService;
import com.parkinglot.example.validation.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;

    @Autowired
    private FloorService floorService;

    @Autowired
    private SlotService slotService;

    public String createParkingLot(ParkingLotRequest request) {
        try {
            RequestValidator.validateParkingLot(request);
            return parkingLotService.createParkingLot(request);
        } catch (ValidationException e) {
            return "Invalid Request";
        } catch (Exception e) {
            return "Internal error while creating Parking Lot";
        }
    }

    public void displayFreeCount(String parkingLotId, VehicleType vehicleType) {
        try {
            floorService.printFreeCount(parkingLotId, vehicleType);
        } catch (Exception e) {
            System.out.println("Internal error while fetching free count.");
        }
    }

    public void displayFreeSlots(String parkingLotId, VehicleType vehicleType) {
        try {
            List<Floor> floors = floorService.findByParkingLotId(parkingLotId);
            slotService.printFreeSlotsByVehicleType(floors, vehicleType);
        } catch (Exception e) {
            System.out.println("Internal error while fetching free slots.");
        }
    }

    public void parkVehicle(String parkingLotId, Vehicle vehicle) {
        System.out.println(parkingLotService.parkVehicle(parkingLotId, vehicle));
    }

    public void unParkVehicle(String ticketId) {
        System.out.println(parkingLotService.unParkVehicle(ticketId));
    }
}
