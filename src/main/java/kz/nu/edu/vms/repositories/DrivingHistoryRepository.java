package kz.nu.edu.vms.repositories;

import kz.nu.edu.vms.models.Driver;
import kz.nu.edu.vms.models.DrivingHistory;
import org.springframework.data.repository.CrudRepository;

public interface DrivingHistoryRepository extends CrudRepository<DrivingHistory, Integer> {
    Iterable<DrivingHistory> findAllByDriver(Driver driver);
}
