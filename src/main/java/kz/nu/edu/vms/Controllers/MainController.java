package kz.nu.edu.vms.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import kz.nu.edu.vms.DTO.VehicleDTO;
import kz.nu.edu.vms.models.*;
import kz.nu.edu.vms.repositories.*;
import kz.nu.edu.vms.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MainController {

    @Autowired
    private JwtUtil jwtUtil;
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


    private String getUsername(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null) {
            return null;
        }
        String jwtToken = token.substring(7);
        String username = jwtUtil.getUsernameFromToken(jwtToken);
        return username;
    }

    private String getRole(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null) {
            return null;
        }
        String jwtToken = token.substring(7);
        String role = jwtUtil.getRoleFromToken(jwtToken);
        return role;
    }

    @GetMapping("/profile")
    public UserTemplate getInfo(HttpServletRequest request) {
        String username = getUsername(request);
        Optional<Admin> user = adminRepository.findAdminByEmail(username);
        if (!user.isEmpty())
            return new UserTemplate(user.get().getEmail(), user.get().getFirstName(), user.get().getLastName(), user.get().getContactInfo(), getRole(request));
        Optional<Driver> user2 = driverRepository.findDriverByEmail(username);
        if (!user2.isEmpty())
            return new UserTemplate(user2.get().getEmail(), user2.get().getFirstName(), user2.get().getLastName(), user2.get().getContactInfo(), getRole(request));
        Optional<FuelingPerson> user3 = fuelingPersonRepository.findFuelingPersonByEmail(username);
        if (!user3.isEmpty())
            return new UserTemplate(user3.get().getEmail(), user3.get().getFirstName(), user3.get().getLastName(), user3.get().getContactInfo(), getRole(request));
        Optional<MaintenancePerson> user4 = maintenancePersonRepository.findMaintenancePersonByEmail(username);
        if (!user4.isEmpty())
            return new UserTemplate(user4.get().getEmail(), user4.get().getFirstName(), user4.get().getLastName(), user4.get().getContactInfo(), getRole(request));
        throw new UsernameNotFoundException("Something went wrong!");
    }


}
