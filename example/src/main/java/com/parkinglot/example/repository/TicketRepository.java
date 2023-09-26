package com.parkinglot.example.repository;

import com.parkinglot.example.models.Ticket;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TicketRepository {

    private static Map<String, Ticket> ticketMap = new HashMap<>();

    public void save(Ticket ticket) {
        ticketMap.put(ticket.getId(), ticket);
    }

    public Ticket findbyid(String ticketId) {
        return ticketMap.get(ticketId);
    }
}
