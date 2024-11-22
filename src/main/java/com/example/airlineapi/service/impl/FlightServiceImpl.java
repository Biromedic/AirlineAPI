package com.example.airlineapi.service.impl;

import com.example.airlineapi.exception.ErrorMessages;
import com.example.airlineapi.exception.ResourceNotFoundException;
import com.example.airlineapi.model.Flight;
import com.example.airlineapi.payload.FlightDTO;
import com.example.airlineapi.repository.FlightRepository;
import com.example.airlineapi.service.serviceInterface.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Override
    public Flight createFlight(@Valid FlightDTO flightDTO) {
        Flight flight = Flight.builder()
                .fromLocation(flightDTO.getFromLocation())
                .toLocation(flightDTO.getToLocation())
                .startDate(flightDTO.getStartDate())
                .endDate(flightDTO.getEndDate())
                .daysOfWeek(flightDTO.getDaysOfWeek())
                .capacity(flightDTO.getCapacity())
                .build();
        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> getAvailableFlights() {
        return flightRepository.findByCapacityGreaterThan(0);
    }

    @Override
    public Flight getFlightById(Long flightId) {
        return flightRepository.findById(flightId)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + flightId));
    }

    @Override
    public Page<Flight> queryFlights(String from, String to, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        if (from == null || to == null || startDate == null || endDate == null) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_QUERY_PARAMETERS);
        }

        return flightRepository.findByFromLocationAndToLocationAndStartDateGreaterThanEqualAndEndDateLessThanEqual(
                from, to, startDate, endDate, pageable
        );
    }

}
