package com.example.demo1.entity;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String bookingReference;
   @Column(name = "booking_date")
private LocalDate bookingDate;

    private int numberOfGuests;
    private double totalPrice;


    public Booking() {}

    public Booking(String bookingReference, LocalDate bookingDate, int numberOfGuests, double totalPrice) {
        this.bookingReference = bookingReference;
        this.bookingDate = bookingDate;
        this.numberOfGuests = numberOfGuests;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookingReference() {
        return bookingReference;
    }

    public void setBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
