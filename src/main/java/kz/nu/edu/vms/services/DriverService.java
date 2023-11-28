package kz.nu.edu.vms.services;

import kz.nu.edu.vms.models.Driver;
import kz.nu.edu.vms.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;

    public Iterable<Driver> getDrivers() {
        return driverRepository.findAll();
    }

    public Driver getDriver(int id) {
        return driverRepository.findById(id).orElse(null);
    }
}
