package kz.nu.edu.vms.repositories;

import kz.nu.edu.vms.models.FuelingPerson;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FuelingPersonRepository extends CrudRepository<FuelingPerson, Integer> {
    Optional<FuelingPerson> findFuelingPersonByEmail(String email);
}
