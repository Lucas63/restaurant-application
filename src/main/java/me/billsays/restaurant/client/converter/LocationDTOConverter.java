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
                .dateCreation(locationDTO.getDateCreation())
                .build();
    }
    public LocationDTO convertTo(Location location) {
        return LocationDTO.builder()
                .address(location.getAddress())
                .idLocation(location.getIdLocation())
                .isRegistred(location.getIsRegistred())
                .name(location.getName())
                .dateCreation(location.getDateCreation())
                .build();
    }
}
