package kz.nu.edu.vms.Controllers;

import kz.nu.edu.vms.DTO.VehicleDTO;
import kz.nu.edu.vms.models.Vehicle;
import kz.nu.edu.vms.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;
    @PostMapping()
    public ResponseEntity<Vehicle> addVehicle(@RequestBody VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleService.addVehicle(vehicleDTO);
        if (vehicle == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicle);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteVehicle(@RequestBody int id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Vehicle was deleted");
    }

    @GetMapping()
    public ResponseEntity<Iterable<Vehicle>> getVehicles() {
        Iterable<Vehicle> vehicles = vehicleService.getVehicles();
        if (vehicles != null) return ResponseEntity.ok(vehicles);
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable int id) {
        Vehicle vehicle = vehicleService.getVehicle(id);
        if (vehicle != null) return ResponseEntity.ok(vehicle);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable int id, @RequestBody VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleService.updateVehicle(id, vehicleDTO);
        if (vehicle == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(vehicle);
    }
}
