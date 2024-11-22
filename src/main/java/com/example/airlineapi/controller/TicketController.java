package com.example.airlineapi.controller;

import com.example.airlineapi.model.Ticket;
import com.example.airlineapi.payload.TicketDTO;
import com.example.airlineapi.service.serviceInterface.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/create-ticket")
    public ResponseEntity<Ticket> createTicket(@Valid @RequestBody TicketDTO ticketDTO) {
        Ticket ticket = ticketService.createTicket(ticketDTO);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Ticket>> getTicketsByUserId(@PathVariable Long userId) {
        List<Ticket> tickets = ticketService.getTicketsByUserId(userId);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/flight/{flightId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Ticket>> getTicketsByFlightId(@PathVariable Long flightId) {
        List<Ticket> tickets = ticketService.getTicketsByFlightId(flightId);
        return ResponseEntity.ok(tickets);
    }
}
