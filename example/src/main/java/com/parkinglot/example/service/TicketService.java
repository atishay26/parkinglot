package com.parkinglot.example.service;

import com.parkinglot.example.models.Floor;
import com.parkinglot.example.models.Slot;
import com.parkinglot.example.models.Ticket;
import com.parkinglot.example.models.Vehicle;
import com.parkinglot.example.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static com.parkinglot.example.util.TicketUtil.getTicketId;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public String createTicket(String parkingLotId, Floor floor, Slot slot, Vehicle vehicle) {
        Ticket ticket = Ticket.builder()
                .parkingLotId(parkingLotId)
                .floorId(floor.getFloorId())
                .slotId(slot.getSlotId())
                .regNum(vehicle.getRegNum())
                .timeIn(Instant.now().getEpochSecond())
                .id(getTicketId(parkingLotId, floor.getFloorNum(), slot.getSlotNum()))
                .type(vehicle.getType())
                .colour(vehicle.getColour())
                .build();
        ticketRepository.save(ticket);
        return ticket.getId();
    }


    public Ticket findById(String ticketId) {
        return ticketRepository.findbyid(ticketId);
    }
}
