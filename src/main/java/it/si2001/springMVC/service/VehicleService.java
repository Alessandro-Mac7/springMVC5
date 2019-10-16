package it.si2001.springMVC.service;

import it.si2001.springMVC.model.Vehicle;
import it.si2001.springMVC.repository.VehicleRepository;
import it.si2001.springMVC.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Transactional
    public List<Vehicle> getVehicles(){
        return vehicleRepository.findAll();
    }

    @Transactional
    public Vehicle getVehicle(int id) throws ResourceNotFoundException {
        return vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    public void deleteVehicle(int id){
        vehicleRepository.deleteById(id);
    }

    @Transactional
    public void saveVehicle(Vehicle vehicle){
        vehicleRepository.save(vehicle);
    }

}
