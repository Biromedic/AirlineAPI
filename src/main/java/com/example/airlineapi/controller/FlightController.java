package com.example.airlineapi.controller;

import com.example.airlineapi.model.Flight;
import com.example.airlineapi.payload.FlightDTO;
import com.example.airlineapi.service.serviceInterface.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
@Tag(name = "Flights", description = "Flight Management APIs")
public class FlightController {

    private final FlightService flightService;

    @Operation(summary = "Create a new flight", description = "This endpoint allows admins to create new flights")
    @PostMapping("/admin/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Flight> createFlight(@Valid @RequestBody FlightDTO flightDTO) {
        Flight createdFlight = flightService.createFlight(flightDTO);
        return ResponseEntity.ok(createdFlight);
    }

    @Operation(summary = "Query flights", description = "Admin endpoint to get available flights")
    @GetMapping("/admin/available")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Flight>> getAvailableFlights() {
        List<Flight> flights = flightService.getAvailableFlights();
        return ResponseEntity.ok(flights);
    }

    @Operation(summary = "Query flights", description = "Public endpoint to query flights based on filters")
    @GetMapping
    public ResponseEntity<Page<Flight>> queryFlights(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam String startDate,
            @RequestParam String endDate,
            Pageable pageable
    ) {
        Page<Flight> flights = flightService.queryFlights(from, to, LocalDate.parse(startDate),
                LocalDate.parse(endDate), pageable);
        return ResponseEntity.ok(flights);
    }

    @GetMapping("admin/{flightId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long flightId) {
        Flight flight = flightService.getFlightById(flightId);
        return ResponseEntity.ok(flight);
    }
}
