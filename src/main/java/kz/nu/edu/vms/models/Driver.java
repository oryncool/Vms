package kz.nu.edu.vms.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Driver")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private int driverId;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "contact_info")
    private String contactInfo;
    @Column(name = "assigned_vehicle")
    private String assignedVehicle;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "driver")
    private List<Task> tasks;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "driver")
    private List<DrivingHistory> drivingHistories;

    public Driver(String email, String password, String firstName, String lastName, String contactInfo) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactInfo = contactInfo;
    }
}
