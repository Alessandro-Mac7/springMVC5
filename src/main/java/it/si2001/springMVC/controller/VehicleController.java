package it.si2001.springMVC.controller;

import it.si2001.springMVC.dto.VehicleDTO;
import it.si2001.springMVC.model.Category;
import it.si2001.springMVC.model.User;
import it.si2001.springMVC.model.Vehicle;
import it.si2001.springMVC.service.CategoryService;
import it.si2001.springMVC.service.UserService;
import it.si2001.springMVC.service.VehicleService;
import it.si2001.springMVC.util.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public String index(ModelMap model, HttpSession session){
        String logged = (String) session.getAttribute("loggedUser");

        if(logged!=null){
            User user = userService.getUserByMail(logged);
            List<Vehicle> vehicles = vehicleService.getVehicles();
            if(user.getTypology().getType().equals("Admin")){
                List<Category> categories = categoryService.getAll();
                model.addAttribute("categories",categories);
                model.addAttribute("vehicles",vehicles);
                return "vehicles";
            } else {
                model.addAttribute("vehicles",vehicles);
                return "vehicles-customer";
            }
        }
        return "redirect:/";
    }

    @ResponseBody
    @PostMapping("/save")
    public String saveVehicle(@RequestBody VehicleDTO vehicleDTO) throws ResourceNotFoundException {
        Vehicle newVehicles = convertToEntity(vehicleDTO);
        vehicleService.saveVehicle(newVehicles);
        return "success";
    }

    @GetMapping("/edit/{id}")
    public @ResponseBody VehicleDTO editVehicle(@PathVariable int id) throws ResourceNotFoundException {
        Vehicle vehicle = vehicleService.getVehicle(id);
        VehicleDTO vehicleDTO = convertToDto(vehicle);
        return vehicleDTO;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable int id) {
        vehicleService.deleteVehicle(id);
        return "redirect:/vehicle";
    }

    private VehicleDTO convertToDto(Vehicle vehicle) {
        VehicleDTO vehicleDTO = modelMapper.map(vehicle, VehicleDTO.class);
        return vehicleDTO;
    }

    private Vehicle convertToEntity(VehicleDTO vehicleDTO) throws ResourceNotFoundException {
        Vehicle vehicle = modelMapper.map(vehicleDTO, Vehicle.class);
        Category category = categoryService.get(vehicleDTO.getCategoryId());
        vehicle.setCategory(category);

        return vehicle;
    }

}
