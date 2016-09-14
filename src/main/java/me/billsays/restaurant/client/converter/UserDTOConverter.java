package me.billsays.restaurant.client.converter;

import me.billsays.restaurant.client.controller.dto.UserDTO;
import me.billsays.restaurant.client.model.Owner;
import me.billsays.restaurant.client.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
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
                .idUser(userDTO.getIdUser())
                .roles(Optional.ofNullable(userDTO.getRoles())
                        .map(roles -> roles.stream().map(roleDTOConverter::convertFrom)
                                .collect(Collectors.toSet())).orElse(Collections.emptySet()))
                .build();
        user.getRoles().stream().forEach(role -> role.setUser(user));
        return user;
    }
}
