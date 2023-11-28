package kz.nu.edu.vms.repositories;

import kz.nu.edu.vms.models.Driver;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DriverRepository extends CrudRepository<Driver, Integer> {
    Optional<Driver> findDriverByEmail(String email);
}
