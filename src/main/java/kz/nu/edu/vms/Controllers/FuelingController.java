package kz.nu.edu.vms.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import kz.nu.edu.vms.DTO.FuelingDTO;
import kz.nu.edu.vms.models.Fueling;
import kz.nu.edu.vms.security.JwtUtil;
import kz.nu.edu.vms.services.FuelingService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fueling")
public class FuelingController {
    @Autowired
    private FuelingService fuelingService;
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

    @PostMapping()
    public ResponseEntity<Fueling> addFueling(@RequestBody FuelingDTO fuelingDTO, HttpServletRequest request) {
        String role = getRole(request);
        if (!role.equals("FUELING")) return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        String email = getUsername(request);
        Fueling fueling = fuelingService.addFueling(fuelingDTO, email);
        if (fueling == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(fueling);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFueling(@PathVariable int id) {
        fuelingService.deleteFueling(id);
        return ResponseEntity.ok("Fueling was deleted!");
    }

    @PostMapping("/{id}")
    public ResponseEntity<Fueling> updateFueling(@PathVariable int id, @RequestBody FuelingDTO fuelingDTO, HttpServletRequest request) {
        String email = getUsername(request);
        Fueling fueling = fuelingService.updateFueling(id, email, fuelingDTO);
        if (fueling == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(fueling);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fueling> getFueling(@PathVariable int id) {
        Fueling fueling = fuelingService.getFueling(id);
        if (fueling == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(fueling);
    }

    @GetMapping()
    public ResponseEntity<Iterable<Fueling>> getFuelingsByEmail(HttpServletRequest request) {
        String email = getUsername(request);
        Iterable<Fueling> fuelings = fuelingService.getFuelingsByEmail(email);
        return ResponseEntity.ok(fuelings);
    }
}
