package kz.nu.edu.vms.repositories;

import kz.nu.edu.vms.models.Fueling;
import kz.nu.edu.vms.models.FuelingPerson;
import org.springframework.data.repository.CrudRepository;

public interface FuelingRepository extends CrudRepository<Fueling, Integer> {
    Iterable<Fueling> findAllByFuelingPerson(FuelingPerson fuelingPerson);
}
