package kz.nu.edu.vms.DTO;
import kz.nu.edu.vms.models.FuelingPerson;
import kz.nu.edu.vms.models.Vehicle;
import lombok.Data;

@Data
public class FuelingDTO {
    private int vehicleId;
    private String fuelType;
    private String amount;
    private double cost;
}
