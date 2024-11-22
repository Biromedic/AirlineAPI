package com.example.airlineapi.service.impl;

import com.example.airlineapi.exception.ErrorMessages;
import com.example.airlineapi.model.enums.TicketStatus;
import com.example.airlineapi.payload.TicketDTO;
import com.example.airlineapi.exception.ResourceNotFoundException;
import com.example.airlineapi.model.Flight;
import com.example.airlineapi.model.Ticket;
import com.example.airlineapi.model.User;
import com.example.airlineapi.repository.FlightRepository;
import com.example.airlineapi.repository.TicketRepository;
import com.example.airlineapi.service.serviceInterface.TicketService;
import com.example.airlineapi.service.serviceInterface.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final FlightRepository flightRepository;
    private final UserService userService;

    @Override
    public Ticket createTicket(@Valid TicketDTO ticketDTO) {
        Flight flight = flightRepository.findById(ticketDTO.getFlightId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(ErrorMessages.FLIGHT_NOT_FOUND, ticketDTO.getFlightId())
                ));

        if (flight.getCapacity() <= 0) {
            throw new IllegalStateException(
                    String.format(ErrorMessages.NO_SEATS_AVAILABLE, ticketDTO.getFlightId())
            );
        }

        User user = userService.getUserById(ticketDTO.getUserId());
        flight.setCapacity(flight.getCapacity() - 1);

        Ticket ticket = Ticket.builder()
                .user(user)
                .flight(flight)
                .passengerFullname(ticketDTO.getPassengerFullname())
                .status(TicketStatus.BOOKED)
                .bookingDate(LocalDate.now())
                .build();

        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getTicketsByUserId(Long userId) {
        return ticketRepository.findByUser_UserId(userId);
    }

    @Override
    public List<Ticket> getTicketsByFlightId(Long flightId) {
        return ticketRepository.findByFlight_FlightId(flightId);
    }
}
