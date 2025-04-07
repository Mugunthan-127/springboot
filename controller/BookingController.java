package com.example.demo1.controller;
import com.example.demo1.entity.Booking;
import com.example.demo1.service.BookingService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public String createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @GetMapping
    public Page<Booking> getAllBookings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "bookingDate,asc") String[] sort) {
        return bookingService.getAllBookings(page, size, sort);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id);
        return booking != null ? ResponseEntity.ok(booking) : ResponseEntity.notFound().build();
    }

    @GetMapping("/reference/{reference}")
    public ResponseEntity<Booking> getBookingByReference(@PathVariable String reference) {
        Booking booking = bookingService.getBookingByReference(reference);
        return booking != null ? ResponseEntity.ok(booking) : ResponseEntity.notFound().build();
    }
        @GetMapping("/Guests/{Guests}")
            public ResponseEntity<Booking> getBookingByGuests(@PathVariable String Guests) {
                Booking booking = bookingService.getBookingByGuests(Guests);
                return booking != null ? ResponseEntity.ok(booking) : ResponseEntity.notFound().build();
            }
        
    

     @GetMapping("/date-range")
public List<Booking> getBookingsWithinDateRange(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
    return bookingService.getBookingsWithinDateRange(startDate, endDate);
}

    

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking updatedBooking) {
        Booking booking = bookingService.updateBooking(id, updatedBooking);
        return booking != null ? ResponseEntity.ok(booking) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}