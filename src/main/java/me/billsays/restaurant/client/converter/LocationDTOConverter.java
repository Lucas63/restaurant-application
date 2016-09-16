package me.billsays.restaurant.client.converter;

import me.billsays.restaurant.client.controller.dto.LocationDTO;
import me.billsays.restaurant.client.model.Location;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * LocationDTOConverter.java 9/2/16, 2016
 *
 * @author mkvitko
 */
@Component
public class LocationDTOConverter {
    public Location convertFrom(LocationDTO locationDTO) {
        return Location.builder()
                .address(locationDTO.getAddress())
                .idLocation(locationDTO.getIdLocation())
                .isRegistred(locationDTO.getIsRegistred())
                .name(locationDTO.getName())
                .build();
    }
}
