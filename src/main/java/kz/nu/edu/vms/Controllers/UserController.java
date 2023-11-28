package kz.nu.edu.vms.Controllers;

import kz.nu.edu.vms.DTO.UserDTO;
import kz.nu.edu.vms.models.*;
import kz.nu.edu.vms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping()
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) {
        int status = userService.addUser(userDTO);
        if (status == 0)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@RequestBody String role, @PathVariable int id) {
        int status = userService.deleteUser(id, role);
        if (status == 0) return ResponseEntity.badRequest().build();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO, @PathVariable int id) {
        int status = userService.updateUser(id, userDTO);
        if (status == 0) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("User was updated");
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserTemplate> getUser(@RequestBody String role, @PathVariable int id) {
        UserTemplate userTemplate = userService.getUser(id, role);
        if (userTemplate == null) return  ResponseEntity.notFound().build();
        return ResponseEntity.ok(userTemplate);
    }
}
