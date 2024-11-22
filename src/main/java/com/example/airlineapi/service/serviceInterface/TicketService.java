package com.example.airlineapi.service.serviceInterface;

import com.example.airlineapi.model.Ticket;
import com.example.airlineapi.payload.TicketDTO;

import java.util.List;

public interface TicketService {

    Ticket createTicket(TicketDTO ticketDTO);

    List<Ticket> getTicketsByUserId(Long userId);

    List<Ticket> getTicketsByFlightId(Long flightId);
}