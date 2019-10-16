package it.si2001.springMVC.controller;

import it.si2001.springMVC.model.User;
import it.si2001.springMVC.model.Vehicle;
import it.si2001.springMVC.service.CategoryService;
import it.si2001.springMVC.service.UserService;
import it.si2001.springMVC.service.VehicleService;
import it.si2001.springMVC.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping
    public String index(ModelMap model, HttpSession session){
        String logged = (String) session.getAttribute("loggedUser");

        if(logged!=null){
            User user = userService.getUserByMail(logged);
            List<Vehicle> vehicles = vehicleService.getVehicles();
            if(user.getTypology().getType().equals("Admin")){
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
    public String saveVehicle(@RequestBody Vehicle newVehicles) {
        vehicleService.saveVehicle(newVehicles);
        return "success";
    }

    @GetMapping("/edit/{id}")
    public String editVehicle(@PathVariable int id, Model model) throws ResourceNotFoundException {
        Vehicle vehicle = vehicleService.getVehicle(id);
        model.addAttribute("editable", vehicle);
        return "redirect:/vehicle";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable int id) {
        vehicleService.deleteVehicle(id);
        return "redirect:/vehicle";
    }

}
