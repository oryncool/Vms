package kz.nu.edu.vms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "driving_history")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrivingHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driving_history_id")
    private int drivingHistoryId;
    @Column(name = "from_location")
    private String from;
    @Column(name = "to_location")
    private String to;
    @Column(name = "date_completed")
    private Date dateCompleted;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;
}
