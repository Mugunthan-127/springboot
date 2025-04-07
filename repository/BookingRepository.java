package com.example.demo1.repository;

import com.example.demo1.entity.Booking;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    
       @Query("SELECT b FROM Booking b WHERE b.bookingReference = :reference")
    Booking findByBookingReference(@Param("reference") String reference);
    @Query("SELECT b FROM Booking b WHERE b.numberOfGuests = :Guests")
    Booking findyByNumberOfGuests(@Param("Guests") String Guests);
    @Query("SELECT b FROM Booking b WHERE b.bookingDate BETWEEN :startDate AND :endDate")
    List<Booking> findBookingsWithinDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    
}
