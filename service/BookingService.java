package com.example.demo1.service;
import com.example.demo1.entity.Booking;
import com.example.demo1.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    public String createBooking(Booking booking) {
        if(booking.getTotalPrice()<1)
        {
            return "Price can't be less than 100";
        }
        bookingRepository.save(booking);
        return "Booking succeeded";
    }
    public Page<Booking> getAllBookings(int page, int size, String[] sort) {
        Sort sorting = Sort.by(Sort.Order.by(sort[0]).with(Sort.Direction.fromString(sort[1])));
        Pageable pageable = PageRequest.of(page, size, sorting);
        return bookingRepository.findAll(pageable);
    }
    public Booking getBookingById(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        return booking.orElse(null);
    }
    public Booking getBookingByReference(String reference) {
        return bookingRepository.findByBookingReference(reference);
    }
    public Booking getBookingByGuests(String Guests) {
        return bookingRepository.findyByNumberOfGuests(Guests);
    }
      public List<Booking> getBookingsWithinDateRange(LocalDate startDate, LocalDate endDate) {
        return bookingRepository.findBookingsWithinDateRange(startDate, endDate);
    }
    public Booking updateBooking(Long id, Booking updatedBooking) {
        Optional<Booking> existingBooking = bookingRepository.findById(id);
        if (existingBooking.isPresent()) {
            Booking booking = existingBooking.get();
     
            booking.setBookingReference(updatedBooking.getBookingReference());
            booking.setBookingDate(updatedBooking.getBookingDate());
            booking.setNumberOfGuests(updatedBooking.getNumberOfGuests());
            booking.setTotalPrice(updatedBooking.getTotalPrice());
            return bookingRepository.save(booking);
        }
        return null; 
    }
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
