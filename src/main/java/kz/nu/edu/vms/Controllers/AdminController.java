package kz.nu.edu.vms.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import kz.nu.edu.vms.DTO.TaskDTO;
import kz.nu.edu.vms.DTO.UserDTO;
import kz.nu.edu.vms.DTO.VehicleDTO;
import kz.nu.edu.vms.models.*;
import kz.nu.edu.vms.security.JwtUtil;
import kz.nu.edu.vms.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {
    private final JwtUtil jwtUtil;
    private final AdminService adminService;

    @Autowired
    public AdminController(JwtUtil jwtUtil, AdminService adminService) {
        this.jwtUtil = jwtUtil;
        this.adminService = adminService;
    }

    private String getUsername(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null) {
            return null;
        }
        String jwtToken = token.substring(7);
        String username = jwtUtil.getUsernameFromToken(jwtToken);
        return username;
    }

}
