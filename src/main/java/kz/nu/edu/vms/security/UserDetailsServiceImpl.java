package kz.nu.edu.vms.security;
import kz.nu.edu.vms.models.*;
import kz.nu.edu.vms.repositories.AdminRepository;
import kz.nu.edu.vms.repositories.DriverRepository;
import kz.nu.edu.vms.repositories.FuelingPersonRepository;
import kz.nu.edu.vms.repositories.MaintenancePersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private FuelingPersonRepository fuelingPersonRepository;
    @Autowired
    private MaintenancePersonRepository maintenancePersonRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> user = this.adminRepository.findAdminByEmail(username);
        if (!user.isEmpty())
            return new UserDetailsImpl(user.get().getEmail(), user.get().getPassword(), new Role("ADMIN"));
        Optional<Driver> user2 = this.driverRepository.findDriverByEmail(username);
        if (!user2.isEmpty())
            return new UserDetailsImpl(user2.get().getEmail(), user2.get().getPassword(), new Role(("DRIVER")));
        Optional<FuelingPerson> user3 = this.fuelingPersonRepository.findFuelingPersonByEmail(username);
        if (!user3.isEmpty())
            return new UserDetailsImpl(user3.get().getEmail(), user3.get().getPassword(), new Role(("FUELING")));
        Optional<MaintenancePerson> user4 = this.maintenancePersonRepository.findMaintenancePersonByEmail(username);
        if (!user4.isEmpty())
            return new UserDetailsImpl(user4.get().getEmail(), user4.get().getPassword(), new Role(("MAINTENANCE")));
        throw new UsernameNotFoundException("user with email " + username + " is not found!");
    }
}
