package me.billsays.restaurant.client.converter;

import me.billsays.restaurant.client.controller.dto.OwnerDTO;
import me.billsays.restaurant.client.controller.dto.UserDTO;
import me.billsays.restaurant.client.model.Owner;
import me.billsays.restaurant.client.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * UserDTOConverter.java 9/1/16, 2016
 *
 * @author mkvitko
 */
@Component
public class UserDTOConverter {
    @Autowired
    private RoleDTOConverter roleDTOConverter;

    @Autowired
    private PasswordEncoder encoder;

    public User convertFrom (UserDTO userDTO) {
        User user = Owner.builder()
                .dateregistration(userDTO.getDateregistration())
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .password(encoder.encode(userDTO.getPassword()))
                .confirmed(userDTO.getConfirmed())
                .idUser(userDTO.getIdUser())
                .roles(Optional.ofNullable(userDTO.getRoles())
                        .map(roles -> roles.stream().map(roleDTOConverter::convertFrom)
                                .collect(Collectors.toSet())).orElse(Collections.emptySet()))
                .build();
        user.getRoles().stream().forEach(role -> role.setUser(user));
        return user;
    }

    public UserDTO convertTo(User user) {
        return (UserDTO) OwnerDTO.builder()
                .dateregistration(user.getDateregistration())
                .email(user.getEmail())
                .name(user.getName())
                .confirmed(user.getConfirmed())
                .idUser(user.getIdUser())
                .roles(Optional.ofNullable(user.getRoles())
                        .map(roles -> roles.stream().map(roleDTOConverter::convertTo)
                                .collect(Collectors.toSet())).orElse(Collections.emptySet()))
                .build();
    }
}
