package kz.nu.edu.vms.DTO;

import lombok.Data;

import java.sql.Date;

@Data
public class TaskDTO {
    private int adminId;
    private int driverId;
    private int vehicleId;
    private Date datePublished;
    private Date deadline;
    private String description;
    private String status;
    private String from;
    private String to;
}
