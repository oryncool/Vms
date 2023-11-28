package kz.nu.edu.vms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Vehicle")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private int vehicleId;
    @Column(name = "license_plate")
    private String licensePlate;
    @Column(name = "make")
    private String make;
    @Column(name = "model")
    private String model;
    @Column(name = "year")
    private int year;
    @Column(name = "type")
    private String type;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vehicle")
    private List<Fueling> fuelingList;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vehicle")
    private List<Task> tasks;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vehicle")
    private List<Maintenance> maintenances;
}
