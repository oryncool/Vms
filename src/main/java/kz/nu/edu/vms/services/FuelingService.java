package kz.nu.edu.vms.services;

import kz.nu.edu.vms.DTO.FuelingDTO;
import kz.nu.edu.vms.models.Driver;
import kz.nu.edu.vms.models.Fueling;
import kz.nu.edu.vms.models.FuelingPerson;
import kz.nu.edu.vms.models.Vehicle;
import kz.nu.edu.vms.repositories.FuelingRepository;
import kz.nu.edu.vms.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuelingService {
    @Autowired
    private FuelingRepository fuelingRepository;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private FuelingPersonService fuelingPersonService;

    public Fueling addFueling(FuelingDTO fuelingDTO, String email) {
        Vehicle vehicle = vehicleService.getVehicle(fuelingDTO.getVehicleId());
        FuelingPerson fuelingPerson = fuelingPersonService.getFuelingPersonByEmail(email);
        if (vehicle == null || fuelingPerson == null) return null;
        Fueling fueling = Fueling.builder()
                .vehicle(vehicle)
                .fuelingPerson(fuelingPerson)
                .fuelType(fuelingDTO.getFuelType())
                .amount(fuelingDTO.getAmount())
                .cost(fuelingDTO.getCost())
                .build();
        return fuelingRepository.save(fueling);
    }

    public void deleteFueling(int id) {
        fuelingRepository.deleteById(id);
    }

    public Fueling updateFueling(int id, String email, FuelingDTO fuelingDTO) {
        Fueling fueling = getFueling(id);
        Vehicle vehicle = vehicleService.getVehicle(fuelingDTO.getVehicleId());
        if (fueling == null) return null;
        if (vehicle == null) return null;
        if (fueling.getFuelingPerson().getEmail() != email) return null;
        fueling.setVehicle(vehicle);
        fueling.setFuelType(fuelingDTO.getFuelType());
        fueling.setAmount(fuelingDTO.getAmount());
        fueling.setCost(fuelingDTO.getCost());
        return  fuelingRepository.save(fueling);
    }

    public Fueling getFueling(int id) {
        return fuelingRepository.findById(id).orElse(null);
    }

    public Iterable<Fueling> getFuelingsByEmail(String email) {
        FuelingPerson fuelingPerson = fuelingPersonService.getFuelingPersonByEmail(email);
        return fuelingRepository.findAllByFuelingPerson(fuelingPerson);
    }
}
