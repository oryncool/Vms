package kz.nu.edu.vms.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import kz.nu.edu.vms.models.DrivingHistory;
import kz.nu.edu.vms.security.JwtUtil;
import kz.nu.edu.vms.services.DrivingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drivingHistory")
public class DrivingHistoryController {
    @Autowired
    private DrivingHistoryService drivingHistoryService;
    @Autowired
    private JwtUtil jwtUtil;

    private String getUsername(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null) {
            return null;
        }
        String jwtToken = token.substring(7);
        String username = jwtUtil.getUsernameFromToken(jwtToken);
        return username;
    }

    private String getRole(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null) {
            return null;
        }
        String jwtToken = token.substring(7);
        String role = jwtUtil.getRoleFromToken(jwtToken);
        return role;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteHistory(@PathVariable int id, HttpServletRequest request) {
        drivingHistoryService.deleteHistory(id);
        return ResponseEntity.ok("History was deleted!");
    }

    @GetMapping("{id}")
    public ResponseEntity<Iterable<DrivingHistory>> getHistoryByDriver(@PathVariable int id) {
        Iterable<DrivingHistory> drivingHistories = drivingHistoryService.getHistoryByDriver(id);
        if (drivingHistories == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(drivingHistories);
    }
}
