package kz.nu.edu.vms.repositories;

import kz.nu.edu.vms.models.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdminRepository extends CrudRepository<Admin, Integer> {
    Optional<Admin> findAdminByEmail(String email);
}
