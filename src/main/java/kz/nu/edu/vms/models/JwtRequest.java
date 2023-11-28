package kz.nu.edu.vms.models;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
    private String role;
}
