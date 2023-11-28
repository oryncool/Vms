package kz.nu.edu.vms.services;

import kz.nu.edu.vms.DTO.VehicleDTO;
import kz.nu.edu.vms.models.Vehicle;
import kz.nu.edu.vms.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Iterator;
import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
    public Vehicle addVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = Vehicle.builder()
                .licensePlate(vehicleDTO.getLicensePlate())
                .make(vehicleDTO.getMake())
                .model(vehicleDTO.getModel())
                .year(vehicleDTO.getYear())
                .type(vehicleDTO.getType())
                .build();
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(@RequestBody int id) {
        vehicleRepository.deleteById(id);
    }

    public Iterable<Vehicle> getVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicle(int id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    public Vehicle updateVehicle(int id, VehicleDTO vehicleDTO) {
        Vehicle vehicle = getVehicle(id);
        if (vehicle == null) return null;
        vehicle.setLicensePlate(vehicleDTO.getLicensePlate());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setMake(vehicleDTO.getMake());
        vehicle.setYear(vehicleDTO.getYear());
        vehicle.setType(vehicleDTO.getType());
        return vehicleRepository.save(vehicle);
    }
}
