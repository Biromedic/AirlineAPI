package com.example.airlineapi.repository;

import com.example.airlineapi.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByFromLocationAndToLocationAndStartDateAndEndDate(
            String fromLocation, String toLocation, LocalDate startDate, LocalDate endDate
    );

    Page<Flight> findByFromLocationAndToLocationAndStartDateGreaterThanEqualAndEndDateLessThanEqual(
            String fromLocation, String toLocation, LocalDate startDate, LocalDate endDate, Pageable pageable
    );

    List<Flight> findByCapacityGreaterThan(int capacity);
}
