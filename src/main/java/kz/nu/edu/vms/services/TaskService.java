package kz.nu.edu.vms.services;

import jakarta.persistence.EntityNotFoundException;
import kz.nu.edu.vms.DTO.TaskDTO;
import kz.nu.edu.vms.models.Admin;
import kz.nu.edu.vms.models.Driver;
import kz.nu.edu.vms.models.Task;
import kz.nu.edu.vms.models.Vehicle;
import kz.nu.edu.vms.repositories.AdminRepository;
import kz.nu.edu.vms.repositories.DriverRepository;
import kz.nu.edu.vms.repositories.TaskRepository;
import kz.nu.edu.vms.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private DrivingHistoryService drivingHistoryService;

    public Task addTask(TaskDTO taskDTO) {
        Admin admin = adminRepository.findById(taskDTO.getAdminId()).orElseThrow(() -> new EntityNotFoundException("Admin not found"));
        Driver driver = driverRepository.findById(taskDTO.getDriverId()).orElseThrow(() -> new EntityNotFoundException("Driver not found"));
        Vehicle vehicle = vehicleRepository.findById(taskDTO.getVehicleId()).orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));

        // Create a new Task object
        Task newTask = Task.builder()
                .admin(admin)
                .driver(driver)
                .vehicle(vehicle)
                .datePublished(new Date(System.currentTimeMillis()))
                .deadline(Date.valueOf(taskDTO.getDeadline().toString()))
                .description(taskDTO.getDescription())
                .status(taskDTO.getStatus())
                .from(taskDTO.getFrom())
                .to(taskDTO.getTo())
                .build();

        return taskRepository.save(newTask);
    }
    public void deleteTask(int taskId) {
        taskRepository.deleteById(taskId);
    }

    public Task getTask(int id) {
        return taskRepository.findById(id).orElse(null);
    }

    public Iterable<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task updateTask(TaskDTO taskDTO, int id) {
        Admin admin = adminRepository.findById(taskDTO.getAdminId()).orElseThrow(() -> new EntityNotFoundException("Admin not found"));
        Driver driver = driverRepository.findById(taskDTO.getDriverId()).orElseThrow(() -> new EntityNotFoundException("Driver not found"));
        Vehicle vehicle = vehicleRepository.findById(taskDTO.getVehicleId()).orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));
        Task task = getTask(id);
        if (task == null || task.getStatus().equals("COMPLETED")) return null;
        task.setAdmin(admin);
        task.setDriver(driver);
        task.setVehicle(vehicle);
        task.setDeadline(taskDTO.getDeadline());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        task.setFrom(taskDTO.getFrom());
        task.setTo(taskDTO.getTo());
        return taskRepository.save(task);
    }

    public Task completeStatus(int id) {
        Task task = getTask(id);
        if (task == null) return null;
        task.setStatus("COMPLETED");
        drivingHistoryService.addHistory(task);
        return task;
    }
}
