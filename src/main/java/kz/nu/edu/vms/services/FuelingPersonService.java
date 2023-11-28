package kz.nu.edu.vms.services;

import kz.nu.edu.vms.models.Driver;
import kz.nu.edu.vms.models.FuelingPerson;
import kz.nu.edu.vms.repositories.DriverRepository;
import kz.nu.edu.vms.repositories.FuelingPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuelingPersonService {
    @Autowired
    private FuelingPersonRepository fuelingPersonRepository;

    public Iterable<FuelingPerson> getFuelingPersons() {
        return fuelingPersonRepository.findAll();
    }

    public FuelingPerson getFuelingPerson(int id) {
        return fuelingPersonRepository.findById(id).orElse(null);
    }
    public FuelingPerson getFuelingPersonByEmail(String email) {return fuelingPersonRepository.findFuelingPersonByEmail(email).orElse(null);}
}
