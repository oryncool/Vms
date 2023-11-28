package kz.nu.edu.vms.models;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserTemplate {
    private String email;
    private String firstName;
    private String lastName;
    private String contactInfo;
    private String role;
}
