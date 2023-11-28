package kz.nu.edu.vms.repositories;

import kz.nu.edu.vms.models.Vehicle;
import org.springframework.data.repository.CrudRepository;

public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {
    Vehicle getVehicleByVehicleId(int id);
}
