package it.si2001.springMVC.controller;

import it.si2001.springMVC.model.Booking;
import it.si2001.springMVC.service.BookingService;
import it.si2001.springMVC.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping("/list")
    public String showBookings(Model model) {
        List<Booking> bookings = bookingService.getBookings();
        model.addAttribute("bookings", bookings);
        return "list-bookings";
    }

    @PostMapping("/save")
    public String saveBooking(@RequestBody Booking newBooking) {
        bookingService.saveBooking(newBooking);
        return "redirect:/booking/list";
    }

    @GetMapping("/edit/{id}")
    public String editBooking(@PathVariable int id, Model model) throws ResourceNotFoundException {
        Booking booking = bookingService.getBooking(id);
        model.addAttribute("booking", booking);
        return "booking-form";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBooking(@PathVariable int id) {
        bookingService.deleteBooking(id);
        return "redirect:/booking/list";
    }
}
