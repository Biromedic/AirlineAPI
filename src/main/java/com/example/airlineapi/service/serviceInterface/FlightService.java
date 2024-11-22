package com.example.airlineapi.service.serviceInterface;

import com.example.airlineapi.payload.FlightDTO;
import com.example.airlineapi.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {
    Flight createFlight(FlightDTO flightDTO);

    List<Flight> getAvailableFlights();

    Flight getFlightById(Long flightId);

    Page<Flight> queryFlights(String from, String to, LocalDate startDate, LocalDate endDate, Pageable pageable);
}
