package me.billsays.restaurant.client.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Set;

/**
 * User.java 8/30/16, 2016
 *
 * @author mkvitko
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
//@Builder
public class UserDTO {
    protected Integer idUser;
    protected String name;
    protected String email;
    protected Date dateregistration;
    protected String password;
    protected String passwordConfirmation;
    protected Set<RoleDTO> roles;
}
