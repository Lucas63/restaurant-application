package me.billsays.restaurant.client.converter;

import me.billsays.restaurant.client.controller.dto.RoleDTO;
import me.billsays.restaurant.client.model.Role;
import org.springframework.stereotype.Component;

/**
 * RoleDTOConverter.java 9/1/16, 2016
 *
 * @author mkvitko
 */
@Component
public class RoleDTOConverter {
    public Role convertFrom(RoleDTO roleDTO) {
        return Role.builder()
                .idRole(roleDTO.getIdRole())
                .roleName(roleDTO.getRoleName())
                .build();
    }

    public RoleDTO convertTo(Role role) {
        return RoleDTO.builder()
                .idRole(role.getIdRole())
                .roleName(role.getRoleName())
                .build();
    }
}
