package kz.nu.edu.vms.services;

import kz.nu.edu.vms.models.Driver;
import kz.nu.edu.vms.models.DrivingHistory;
import kz.nu.edu.vms.models.Task;
import kz.nu.edu.vms.repositories.DrivingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Date;

@Service
public class DrivingHistoryService {
    @Autowired
    private DrivingHistoryRepository drivingHistoryRepository;
    @Autowired
    private DriverService driverService;

    public DrivingHistory addHistory(Task task) {
        DrivingHistory drivingHistory = DrivingHistory.builder()
                .from(task.getFrom())
                .to(task.getTo())
                .dateCompleted(new Date(System.currentTimeMillis()))
                .task(task)
                .driver(task.getDriver())
                .build();
        return drivingHistoryRepository.save(drivingHistory);
    }

    public Iterable<DrivingHistory> getHistoryByDriver(int id) {
        Driver driver = driverService.getDriver(id);
        if (driver == null) return null;
        return drivingHistoryRepository.findAllByDriver(driver);
    }

    public void deleteHistory(int id) {
        drivingHistoryRepository.deleteById(id);
    }
}
