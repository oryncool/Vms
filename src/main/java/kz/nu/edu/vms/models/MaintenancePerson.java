package kz.nu.edu.vms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "maintenance_person")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaintenancePerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maintenance_person_id")
    private int maintenancePersonId;
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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "maintenancePerson")
    private List<Maintenance> maintenances;

    public MaintenancePerson(String email, String password, String firstName, String lastName, String contactInfo) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactInfo = contactInfo;
    }
}
