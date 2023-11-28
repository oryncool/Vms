package kz.nu.edu.vms.models;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class Role implements GrantedAuthority {

    private String role;

    @Override
    public String getAuthority() {
        return role;
    }
}
