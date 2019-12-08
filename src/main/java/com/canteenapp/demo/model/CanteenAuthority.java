package com.canteenapp.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
public class CanteenAuthority implements GrantedAuthority {
    UserRoleName userRoleName;
    @Override
    public String getAuthority() {
        return userRoleName.name();
    }
}
