package it.si2001.springMVC.controller;

import it.si2001.springMVC.dto.BookingDTO;
import it.si2001.springMVC.model.Booking;
import it.si2001.springMVC.model.User;
import it.si2001.springMVC.model.Vehicle;
import it.si2001.springMVC.service.BookingService;
import it.si2001.springMVC.service.UserService;
import it.si2001.springMVC.service.VehicleService;
import it.si2001.springMVC.util.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @Autowired
    UserService userService;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    ModelMapper modelMapper;

    @ResponseBody
    @GetMapping("/list/{id}")
    public
    List<Booking> getList(@PathVariable int id) {
        List<Booking> bookings =  bookingService.getBookingsUserId(id);
        return bookings;
    }

    @ResponseBody
    @PostMapping("/save")
    public String saveBooking(@RequestBody BookingDTO bookingDTO) throws ResourceNotFoundException {
        Booking newBooking = convertToEntity(bookingDTO);
        bookingService.saveBooking(newBooking);
        return "success";
    }

    @GetMapping("/edit/{id}")
    public  @ResponseBody BookingDTO editBooking(@PathVariable int id) throws ResourceNotFoundException {
        Booking booking =  bookingService.getBooking(id);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 2);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(booking.getStartDate());

        if(!cal.after(cal2)){
            BookingDTO bookingDTO = convertToDto(booking);
            return bookingDTO;
        } else
            throw new ResourceNotFoundException();
    }

    @ResponseBody
    @DeleteMapping("/delete/{id}")
    public String deleteBooking(@PathVariable int id) throws ResourceNotFoundException {
        Booking booking =  bookingService.getBooking(id);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 2);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(booking.getStartDate());

        if(!cal.after(cal2)){
            System.out.println("OK");
            bookingService.deleteBooking(id);
            return "success";
        } else
            throw new ResourceNotFoundException();
    }

    private BookingDTO convertToDto(Booking booking) {
        BookingDTO bookingDTO = modelMapper.map(booking, BookingDTO.class);
        bookingDTO.setUserEmail(booking.getUser().getEmail());
        bookingDTO.setVehicleId(booking.getVehicle().getId());
        return bookingDTO;
    }

    private Booking convertToEntity(BookingDTO bookingDTO) throws ResourceNotFoundException {
        Booking booking = modelMapper.map(bookingDTO, Booking.class);
        User user = userService.getUserByMail(bookingDTO.getUserEmail());
        Vehicle vehicle = vehicleService.getVehicle(bookingDTO.getVehicleId());
        booking.setUser(user);
        booking.setVehicle(vehicle);

        return booking;
    }

}
