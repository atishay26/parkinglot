package com.parkinglot.example.service;

import com.parkinglot.example.models.Floor;
import com.parkinglot.example.models.Slot;
import com.parkinglot.example.models.Ticket;
import com.parkinglot.example.models.Vehicle;
import com.parkinglot.example.repository.ParkingLotRepository;
import com.parkinglot.example.requests.ParkingLotRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private FloorService floorService;

    @Autowired
    private SlotService slotService;

    @Autowired
    private TicketService ticketService;

    public String createParkingLot(ParkingLotRequest request) {
        String parkingLotId = parkingLotRepository.createParkingLot(request);
        Set<Integer> floorIds = floorService.addFloorsToParkingLot(parkingLotId, request);
        floorIds.forEach(floorId -> slotService.createSlots(floorId, request.getSlotsPerFloor()));

        return String.format("Created parking lot with %s floors and % slots per floor", request.getNumFloors(),
                request.getSlotsPerFloor());
    }

    public String parkVehicle(String parkingLotId, Vehicle vehicle) {
        Floor floor = floorService.findAvailableByParkingLotIdAndVehicleType(parkingLotId, vehicle.getType());
        Slot slot = slotService.findByFloorIdAndVehicleType(floor.getFloorId(), vehicle.getType());
        parkingLotRepository.parkVehicle(parkingLotId, vehicle.getType());
        floorService.parkVehicle(floor.getFloorId(), vehicle.getType());
        slotService.parkVehicle(slot.getSlotId());

        return ticketService.createTicket(parkingLotId,floor,slot,vehicle);
    }

    public String unParkVehicle(String ticketId) {
        Ticket ticket = ticketService.findById(ticketId);
        parkingLotRepository.unParkVehicle(ticket.getParkingLotId(), ticket.getType());
        floorService.unParkVehicle(ticket.getFloorId(), ticket.getType());
        slotService.unParkVehicle(ticket.getSlotId());

        return String.format("Unparked vehicle with Registration Number: %s and Color: %s", ticket.getRegNum(),
                ticket.getColour());
    }
}
