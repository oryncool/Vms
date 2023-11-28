package kz.nu.edu.vms.repositories;

import kz.nu.edu.vms.models.MaintenancePerson;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MaintenancePersonRepository extends CrudRepository<MaintenancePerson, Integer> {
    Optional<MaintenancePerson> findMaintenancePersonByEmail(String email);
}
