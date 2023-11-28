package kz.nu.edu.vms.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleDTO {
    private String licensePlate;
    private String make;
    private String model;
    private int year;
    private String type;
}
