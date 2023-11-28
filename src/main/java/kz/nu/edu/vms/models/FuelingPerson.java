package kz.nu.edu.vms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "fueling_person")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FuelingPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fueling_person_id")
    private int fuelingPersonId;
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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fuelingPerson")
    private List<Fueling> fuelingList;

    public FuelingPerson(String email, String password, String firstName, String lastName, String contactInfo) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactInfo = contactInfo;
    }
}
