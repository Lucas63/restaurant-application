package me.billsays.restaurant.client.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.billsays.restaurant.client.model.Location;
import me.billsays.restaurant.client.model.Role;

import java.sql.Date;
import java.util.List;
import java.util.Set;

/**
 * Owner.java 8/30/16, 2016
 *
 * @author mkvitko
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class OwnerDTO extends UserDTO {
    @Builder(toBuilder = true)
    public OwnerDTO(Integer idUser, String name, String email, Date dateregistration, String password,
                    String passwordConfirmation, Boolean confirmed, Set<RoleDTO> roles, List<LocationDTO> restaurants) {
        super(idUser, name, email, dateregistration, confirmed, password, passwordConfirmation, roles);
        this.restaurants = restaurants;
    }
    private List<LocationDTO> restaurants;
}
