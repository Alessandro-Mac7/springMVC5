package it.si2001.springMVC.controller;

import it.si2001.springMVC.model.Vehicle;
import it.si2001.springMVC.service.VehicleService;
import it.si2001.springMVC.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/list")
    public String showVehicle(Model model) {
        List<Vehicle> vehicles = vehicleService.getVehicles();
        model.addAttribute("vehicles", vehicles);
        return "list-vehicle";
    }

    @PostMapping("/save")
    public String saveVehicle(@RequestBody Vehicle newVehicles) {
        vehicleService.saveVehicle(newVehicles);
        return "redirect:/vehicle/list";
    }

    @GetMapping("/edit/{id}")
    public String editVehicle(@PathVariable int id, Model model) throws ResourceNotFoundException {
        Vehicle vehicle = vehicleService.getVehicle(id);
        model.addAttribute("vehicle", vehicle);
        return "vehicle-form";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable int id) {
        vehicleService.deleteVehicle(id);
        return "redirect:/vehicle/list";
    }
}
