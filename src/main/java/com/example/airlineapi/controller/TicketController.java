package com.example.airlineapi.controller;

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
    public ResponseEntity<TicketDTO> createTicket(@Valid @RequestBody TicketDTO ticketDTO) {
        TicketDTO createdTicket = ticketService.createTicket(ticketDTO);
        return ResponseEntity.ok(createdTicket);
    }

    @PostMapping("/check-in/{ticketId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<TicketDTO> checkInTicket(@PathVariable Long ticketId) {
        TicketDTO updatedTicket = ticketService.checkInTicket(ticketId);
        return ResponseEntity.ok(updatedTicket);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<TicketDTO>> getTicketsByUserId(@PathVariable Long userId) {
        List<TicketDTO> tickets = ticketService.getTicketsByUserId(userId);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("admin/flight/{flightId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TicketDTO>> getTicketsByFlightId(@PathVariable Long flightId) {
        List<TicketDTO> tickets = ticketService.getTicketsByFlightId(flightId);
        return ResponseEntity.ok(tickets);
    }
}
