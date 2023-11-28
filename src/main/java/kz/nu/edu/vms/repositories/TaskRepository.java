package kz.nu.edu.vms.repositories;

import kz.nu.edu.vms.models.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Integer> {
}
