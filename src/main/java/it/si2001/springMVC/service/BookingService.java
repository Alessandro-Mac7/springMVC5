package it.si2001.springMVC.service;

import it.si2001.springMVC.model.Booking;
import it.si2001.springMVC.repository.BookingRepository;
import it.si2001.springMVC.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Transactional
    public List<Booking> getBookings(){
        return bookingRepository.findAll();
    }

    @Transactional
    public List<Booking> getBookingsUser(String email){
        return bookingRepository.findBookingByUserEquals(email);
    }

    @Transactional
    public List<Booking> getBookingsUserId(int id){
        return bookingRepository.findBookingByUserId(id);
    }

    @Transactional
    public Booking getBooking(int id) throws ResourceNotFoundException {
        return bookingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    public void deleteBooking(int id){
        bookingRepository.deleteById(id);
    }

    @Transactional
    public void saveBooking(Booking booking){
        bookingRepository.save(booking);
    }
}
