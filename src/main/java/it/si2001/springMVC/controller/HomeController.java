package it.si2001.springMVC.controller;

import it.si2001.springMVC.model.Booking;
import it.si2001.springMVC.model.Category;
import it.si2001.springMVC.model.User;
import it.si2001.springMVC.model.Vehicle;
import it.si2001.springMVC.service.BookingService;
import it.si2001.springMVC.service.CategoryService;
import it.si2001.springMVC.service.UserService;
import it.si2001.springMVC.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private BookingService bookingService;



    @GetMapping
    public String index(ModelMap model, HttpSession session){
        String email = (String) session.getAttribute("loggedUser");
        if(email!=null){
            String typology =userService.getUserByMail(email).getTypology().getType();
            if(typology.equals("Admin")){
                List<User> customers = userService.getCustomers();
                List<Category> categories = categoryService.getAll();

                model.addAttribute("customers",customers);
                model.addAttribute("categories",categories);
                return "admin-home";
            } else {
                List<Vehicle> vehicles = vehicleService.getVehicles();
                List<Booking> bookings = bookingService.getBookings();

                model.addAttribute("bookings",bookings);
                model.addAttribute("vehicles",vehicles);
                return "customer-home";
            }

        } else
            return "redirect:/";
    }

}
