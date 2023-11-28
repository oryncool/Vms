package kz.nu.edu.vms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Fueling")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Fueling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fueling_id")
    private int fuelingHistoryId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fueling_person_id")
    private FuelingPerson fuelingPerson;
    @Column(name = "fuel_type")
    private String fuelType;
    @Column(name = "amount")
    private String amount;
    @Column(name = "cost")
    private double cost;
}
