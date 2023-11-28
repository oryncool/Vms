package kz.nu.edu.vms.services;

import jakarta.persistence.EntityNotFoundException;
import kz.nu.edu.vms.DTO.TaskDTO;
import kz.nu.edu.vms.DTO.UserDTO;
import kz.nu.edu.vms.DTO.VehicleDTO;
import kz.nu.edu.vms.models.*;
import kz.nu.edu.vms.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Date;

@Service
public class AdminService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private FuelingPersonRepository fuelingPersonRepository;
    @Autowired
    private MaintenancePersonRepository maintenancePersonRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private TaskRepository taskRepository;

}
