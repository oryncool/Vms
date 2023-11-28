package kz.nu.edu.vms.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String contactInfo;
    private String role;
}
