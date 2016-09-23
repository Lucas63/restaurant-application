package me.billsays.restaurant.client.converter;

import me.billsays.restaurant.client.controller.dto.OwnerDTO;
import me.billsays.restaurant.client.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * OwnerDTOConverter.java 9/2/16, 2016
 *
 * @author mkvitko
 */
@Component
public class OwnerDTOConverter {
    @Autowired
    private LocationDTOConverter locationDTOConverter;
    @Autowired
    private RoleDTOConverter roleDTOConverter;
    @Autowired
    private PasswordEncoder encoder;

    public Owner convertFrom(OwnerDTO ownerDTO) {
        Owner owner = Owner.builder()
                .dateregistration(ownerDTO.getDateregistration())
                .email(ownerDTO.getEmail())
                .name(ownerDTO.getName())
                .password(encoder.encode(ownerDTO.getPassword()))
                .confirmed(ownerDTO.getConfirmed())
                .idUser(ownerDTO.getIdUser())
                .roles(Optional.ofNullable(ownerDTO.getRoles())
                        .map(roles -> roles.stream().map(roleDTOConverter::convertFrom)
                                .collect(Collectors.toSet())).orElse(new HashSet<>()))
                .restaurants(Optional.ofNullable(ownerDTO.getRestaurants())
                        .map(rests -> rests.stream().map(locationDTOConverter::convertFrom)
                                .collect(Collectors.toSet()))
                        .orElse(new HashSet<>()))
                .build();
        owner.getRoles().stream().forEach(role -> role.setUser(owner));
        return owner;
    }
    public OwnerDTO convertTo(Owner owner) {
        OwnerDTO ownerDTO = OwnerDTO.builder()
                .dateregistration(owner.getDateregistration())
                .email(owner.getEmail())
                .name(owner.getName())
                .confirmed(owner.getConfirmed())
                .idUser(owner.getIdUser())
                .roles(Optional.ofNullable(owner.getRoles())
                        .map(roles -> roles.stream().map(roleDTOConverter::convertTo)
                                .collect(Collectors.toSet())).orElse(new HashSet<>()))
                .restaurants(Optional.ofNullable(owner.getRestaurants())
                        .map(rests -> rests.stream().map(locationDTOConverter::convertTo)
                                .collect(Collectors.toList()))
                        .orElse(new ArrayList<>()))
                .build();
        owner.getRoles().stream().forEach(role -> role.setUser(owner));
        return ownerDTO;
    }
}
