package kz.nu.edu.vms.services;

import kz.nu.edu.vms.DTO.UserDTO;
import kz.nu.edu.vms.models.*;
import kz.nu.edu.vms.repositories.AdminRepository;
import kz.nu.edu.vms.repositories.DriverRepository;
import kz.nu.edu.vms.repositories.FuelingPersonRepository;
import kz.nu.edu.vms.repositories.MaintenancePersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

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


    public int addUser(UserDTO userDTO) {
        String password = userDTO.getPassword();
        password = passwordEncoder.encode(password);
        if (userDTO.getRole().equals("ADMIN")) {
            Admin admin = new Admin(userDTO.getEmail(), password, userDTO.getFirstName(), userDTO.getLastName(), userDTO.getContactInfo());
            adminRepository.save(admin);
            return 1;
        }
        if (userDTO.getRole().equals("DRIVER")) {
            Driver driver = new Driver(userDTO.getEmail(), password, userDTO.getFirstName(), userDTO.getLastName(), userDTO.getContactInfo());
            driverRepository.save(driver);
            return 1;
        }
        if (userDTO.getRole().equals("FUELING")) {
            FuelingPerson fuelingPerson = new FuelingPerson(userDTO.getEmail(), password, userDTO.getFirstName(), userDTO.getLastName(), userDTO.getContactInfo());
            fuelingPersonRepository.save(fuelingPerson);
            return 1;
        }
        if (userDTO.getRole().equals("MAINTENANCE")) {
            MaintenancePerson maintenancePerson = new MaintenancePerson(userDTO.getEmail(), password, userDTO.getFirstName(), userDTO.getLastName(), userDTO.getContactInfo());
            maintenancePersonRepository.save(maintenancePerson);
            return 1;
        }
        return 0;
    }
    public int deleteUser(int id, String role) {
        switch (role) {
            case "ADMIN":
                adminRepository.deleteById(id);
                break;
            case "DRIVER":
                driverRepository.deleteById(id);
                break;
            case "FUELING":
                fuelingPersonRepository.deleteById(id);
                break;
            case "MAINTENANCE":
                maintenancePersonRepository.deleteById(id);
            default:
                return 0;
        }
        return 1;
    }

    public int updateUser(int id, UserDTO userDTO) {
        switch (userDTO.getRole()) {
            case "ADMIN":
                Optional<Admin> admin1 = adminRepository.findById(id);
                if (admin1.isEmpty()) return 0;
                Admin admin = admin1.get();
                admin.setEmail(userDTO.getEmail());
                admin.setFirstName(userDTO.getFirstName());
                admin.setLastName(userDTO.getLastName());
                admin.setContactInfo(userDTO.getContactInfo());
                admin.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                adminRepository.save(admin);
                break;
            case "DRIVER":
                Optional<Driver> driver1 = driverRepository.findById(id);
                if (driver1.isEmpty()) return 0;
                Driver driver = driver1.get();
                driver.setEmail(userDTO.getEmail());
                driver.setFirstName(userDTO.getFirstName());
                driver.setLastName(userDTO.getLastName());
                driver.setContactInfo(userDTO.getContactInfo());
                driver.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                driverRepository.save(driver);
                break;
            case "FUELING":
                Optional<FuelingPerson> fuelingPerson1 = fuelingPersonRepository.findById(id);
                if (fuelingPerson1.isEmpty()) return 0;
                FuelingPerson fuelingPerson = fuelingPerson1.get();
                fuelingPerson.setEmail(userDTO.getEmail());
                fuelingPerson.setFirstName(userDTO.getFirstName());
                fuelingPerson.setLastName(userDTO.getLastName());
                fuelingPerson.setContactInfo(userDTO.getContactInfo());
                fuelingPerson.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                fuelingPersonRepository.save(fuelingPerson);
                break;
            case "MAINTENANCE":
                Optional<MaintenancePerson> maintenancePerson1 = maintenancePersonRepository.findById(id);
                if (maintenancePerson1.isEmpty()) return 0;
                MaintenancePerson maintenancePerson = maintenancePerson1.get();
                maintenancePerson.setEmail(userDTO.getEmail());
                maintenancePerson.setFirstName(userDTO.getFirstName());
                maintenancePerson.setLastName(userDTO.getLastName());
                maintenancePerson.setContactInfo(userDTO.getContactInfo());
                maintenancePerson.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                maintenancePersonRepository.save(maintenancePerson);
            default:
                return 0;
        }
        return 1;
    }

    public Iterable<Admin> getAdmins() {
        return adminRepository.findAll();
    }

    public Iterable<Driver> getDriver() {
        return driverRepository.findAll();
    }

    public Iterable<FuelingPerson> getFueling() {
        return fuelingPersonRepository.findAll();
    }

    public Iterable<MaintenancePerson> getMaintenance() {
        return maintenancePersonRepository.findAll();
    }

    public UserTemplate getUser(int id, String role) {
        switch (role) {
            case "ADMIN":
                Optional<Admin> admin = adminRepository.findById(id);
                if (admin.isEmpty()) return null;
                UserTemplate user = UserTemplate.builder()
                        .email(admin.get().getEmail())
                        .firstName(admin.get().getFirstName())
                        .lastName(admin.get().getLastName())
                        .contactInfo(admin.get().getContactInfo())
                        .role("ADMIN")
                        .build();
                return user;
            case "DRIVER":
                Optional<Driver> driver = driverRepository.findById(id);
                if (driver.isEmpty()) return null;
                UserTemplate user1 = UserTemplate.builder()
                        .email(driver.get().getEmail())
                        .firstName(driver.get().getFirstName())
                        .lastName(driver.get().getLastName())
                        .contactInfo(driver.get().getContactInfo())
                        .role("DRIVER")
                        .build();
                return user1;
            case "FUELING":
                Optional<FuelingPerson> fuelingPerson = fuelingPersonRepository.findById(id);
                if (fuelingPerson.isEmpty()) return null;
                UserTemplate user2 = UserTemplate.builder()
                        .email(fuelingPerson.get().getEmail())
                        .firstName(fuelingPerson.get().getFirstName())
                        .lastName(fuelingPerson.get().getLastName())
                        .contactInfo(fuelingPerson.get().getContactInfo())
                        .role("FUELING")
                        .build();
                return user2;
            case "MAINTENANCE":
                Optional<MaintenancePerson> maintenancePerson = maintenancePersonRepository.findById(id);
                if (maintenancePerson.isEmpty()) return null;
                UserTemplate user3 = UserTemplate.builder()
                        .email(maintenancePerson.get().getEmail())
                        .firstName(maintenancePerson.get().getFirstName())
                        .lastName(maintenancePerson.get().getLastName())
                        .contactInfo(maintenancePerson.get().getContactInfo())
                        .role("MAINTENANCE")
                        .build();
                return user3;
            default:
                return null;
        }
    }
}
